package gg.scala.parties.model

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.*

/**
 * @author GrowlyX
 * @since 12/2/2021
 */
class PartyMember(
    val uniqueId: UUID,
    val role: PartyRole
)
{
    fun sendMessage(message: String)
    {
        this.getPlayer()?.sendMessage(message)
    }

    fun getPlayer(): Player?
    {
        return Bukkit.getPlayer(uniqueId)
    }

    fun isOnline(): Boolean = Bukkit.getPlayer(uniqueId) != null
}
