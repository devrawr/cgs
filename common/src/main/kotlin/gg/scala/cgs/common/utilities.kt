package gg.scala.cgs.common

import gg.scala.cgs.common.util.EasyRunnable
import net.kyori.adventure.audience.Audience
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.ComponentBuilder
import net.md_5.bungee.api.chat.HoverEvent
import org.bukkit.ChatColor
import org.bukkit.GameMode
import org.bukkit.entity.Player
import org.bukkit.event.entity.PlayerDeathEvent

/**
 * @author GrowlyX
 * @since 12/2/2021
 */
val startMessage = ComponentBuilder("\n")
    .append("\n")
    .append(" ${ChatColor.AQUA}${CgsGameEngine.INSTANCE.gameInfo.fancyNameRender} is currently in BETA!\n")
    .append(" ${ChatColor.WHITE}Remember, there may be bugs/incomplete features!\n")
    .append(" \n")
    .append(" ${ChatColor.WHITE}If you think you have found a bug, report it at:")
    .append(" ${ChatColor.WHITE}https://discord.gg/yourmom")
    .event(
        ClickEvent(
            ClickEvent.Action.OPEN_URL,
            "https://discord.gg/yourmom"
        )
    )
    .event(
        HoverEvent(
            HoverEvent.Action.SHOW_TEXT,
            ComponentBuilder("${ChatColor.YELLOW}Click to join our discord server!").create()
        )
    )
    .create()

fun Exception.printStackTraceV2(
    rootedFrom: String = "N/A"
)
{
    CgsGameEngine.INSTANCE.plugin.logger.severe {
        """
            An exception was thrown from $rootedFrom!
              Compressed: $message
              Localized: $localizedMessage
              
            Complete stack trace:
            ${stackTraceToString()}
        """.trimIndent()
    }
}

infix fun Player.refresh(
    information: Pair<Boolean, GameMode>
)
{
    health = maxHealth
    foodLevel = 20
    saturation = 12.8f
    maximumNoDamageTicks = 20
    fireTicks = 0
    fallDistance = 0.0f
    level = 0
    exp = 0.0f
    walkSpeed = 0.2f
    inventory.heldItemSlot = 0

    if (information.first)
    {
        allowFlight = true
        isFlying = true
    } else
    {
        isFlying = false
        allowFlight = false
    }

    inventory.clear()
    inventory.armorContents = null

    closeInventory()
    updateInventory()

    gameMode = information.second

    for (potionEffect in activePotionEffects)
    {
        removePotionEffect(potionEffect.type)
    }
}

infix fun Player.adventure(lambda: (Audience) -> Unit)
{
    CgsGameEngine.INSTANCE.audience
        .player(this).apply(lambda)
}

fun respawnPlayer(event: PlayerDeathEvent)
{
    EasyRunnable.delayed(2L) {
        event.entity.spigot().respawn()
    }
}