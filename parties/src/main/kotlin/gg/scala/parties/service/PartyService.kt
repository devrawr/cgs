package gg.scala.parties.service

import gg.scala.parties.model.Party
import io.github.nosequel.data.DataHandler
import io.github.nosequel.data.DataStoreType
import org.bukkit.entity.Player
import java.util.*
import java.util.concurrent.CompletableFuture

/**
 * @author GrowlyX
 * @since 12/2/2021
 */
object PartyService
{
    val service = DataHandler.createStoreType<UUID, Party>(DataStoreType.REDIS)

    private val loadedParties = mutableMapOf<UUID, Party>()

    fun findPartyByLeader(uniqueId: UUID): Party?
    {
        return loadedParties.values.firstOrNull {
            it.leader.uniqueId == uniqueId
        }
    }

    fun findPartyByUniqueId(uniqueId: UUID): Party?
    {
        return loadedParties.values.firstOrNull {
            it.members.containsKey(uniqueId)
        }
    }

    fun findPartyByUniqueId(player: Player): Party?
    {
        return loadedParties.values.firstOrNull {
            it.members.containsKey(player.uniqueId)
        }
    }

    fun reloadPartyByUniqueId(uniqueId: UUID)
    {
        if (loadedParties[uniqueId] == null)
            return

        service.retrieveAllAsync {

            if (it.uniqueId == uniqueId)
            {
                kotlin.run {
                    loadedParties[it.uniqueId] = it; return@retrieveAllAsync
                }
            }
        }
    }

    fun loadPartyOfPlayerIfAbsent(player: Player): CompletableFuture<Party?>
    {
        val loadedParty = findPartyByUniqueId(player)

        return if (loadedParty != null)
        {
            return CompletableFuture<Party?>().apply {
                this.complete(loadedParty)
            }
        } else
        {
            CompletableFuture.supplyAsync {
                var party: Party? = null

                service.retrieveAllAsync {
                    if (it.members.containsKey(player.uniqueId))
                    {
                        loadedParties[it.uniqueId] = it
                    }

                    party = it
                }

                party
            }
        }
    }
}