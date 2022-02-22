package gg.scala.cgs.lobby

import gg.scala.cgs.lobby.gamemode.CgsGameLobby
import gg.scala.cgs.lobby.locator.CgsInstanceLocator
import me.lucko.helper.plugin.ap.Plugin
import me.lucko.helper.plugin.ap.PluginDependency
import org.bukkit.plugin.java.JavaPlugin
import kotlin.properties.Delegates

/**
 * @author GrowlyX
 * @since 11/30/2021
 */
@Plugin(
    name = "CGS-Lobby",
    depends = [
        PluginDependency("helper"),
    ]
)
class CgsLobbyPlugin : JavaPlugin()
{
    companion object
    {
        @JvmStatic
        var INSTANCE by Delegates.notNull<CgsLobbyPlugin>()
    }

    override fun onEnable()
    {
        INSTANCE = this

        CgsInstanceLocator.configure {
            CgsGameLobby.INSTANCE.initialResourceLoad()
        }
    }
}
