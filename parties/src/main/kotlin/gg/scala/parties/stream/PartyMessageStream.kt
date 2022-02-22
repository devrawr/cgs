package gg.scala.parties.stream

import gg.scala.parties.PartySpigotConstants
import gg.scala.parties.model.Party
import gg.scala.parties.service.PartyService
import io.github.nosequel.data.DataHandler
import io.github.nosequel.data.DataStoreType
import net.md_5.bungee.api.chat.BaseComponent
import java.util.*

/**
 * @author GrowlyX
 * @since 12/31/2021
 */
object PartyMessageStream
{
    private val publish = DataHandler.createPubSubType<PartyMessageStreamData>(DataStoreType.REDIS) { message ->
        val partyId = UUID.fromString(message.id)
        val content = PartySpigotConstants.GSON.fromJson(message.content, BaseComponent::class.java)

        PartyService
            .findPartyByUniqueId(partyId)
            ?.let {
                for (member in it.members)
                {
                    member.value.getPlayer()?.sendMessage(content.toLegacyText())
                }
            }
    }

    fun pushToStream(
        party: Party, message: BaseComponent
    )
    {
        this.publish.publish(
            PartyMessageStreamData(
                id = party.uniqueId.toString(),
                content = PartySpigotConstants.GSON.toJson(message)
            )
        )
    }
}

data class PartyMessageStreamData(
    val id: String,
    val content: String
)