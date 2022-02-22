package gg.scala.cgs.common.util

import gg.scala.cgs.common.CgsGameEngine
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

object EasyRunnable
{
    var javaPlugin: JavaPlugin? = null

    fun runTaskAsync(
        delay: Long,
        tickDelay: Long,
        plugin: JavaPlugin? = javaPlugin,
        body: () -> Unit
    )
    {
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, body, delay, tickDelay)
    }

    fun delayed(
        delay: Long,
        plugin: JavaPlugin? = javaPlugin,
        body: () -> Unit
    )
    {
        Bukkit.getScheduler().runTaskLater(plugin, body, delay)
    }
}