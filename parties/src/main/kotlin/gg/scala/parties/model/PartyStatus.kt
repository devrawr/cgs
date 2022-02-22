package gg.scala.parties.model

import org.bukkit.ChatColor

/**
 * @author GrowlyX
 * @since 12/2/2021
 */
enum class PartyStatus(
    val formatted: String
)
{
    PUBLIC("${ChatColor.GREEN}Public"),
    PRIVATE("${ChatColor.RED}Private")
}
