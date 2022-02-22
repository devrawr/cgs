package gg.scala.parties.model

import org.bukkit.ChatColor

/**
 * @author GrowlyX
 * @since 12/2/2021
 */
enum class PartyRole(
    val formatted: String
)
{
    MEMBER("${ChatColor.DARK_GRAY}Member"),
    MODERATOR("${ChatColor.DARK_GREEN}Mod"),
    LEADER("${ChatColor.GOLD}Leader");

    infix fun PartyRole.over(role: PartyRole): Boolean
    {
        return role.ordinal >= this.ordinal
    }
}
