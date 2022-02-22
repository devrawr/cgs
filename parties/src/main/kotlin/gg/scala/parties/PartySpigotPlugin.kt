package gg.scala.parties

import gg.scala.parties.service.PartyService
import me.lucko.helper.plugin.ap.Plugin
import me.lucko.helper.plugin.ap.PluginDependency
import org.bukkit.plugin.java.JavaPlugin

/**
 * @author GrowlyX
 * @since 12/2/2021
 */
@Plugin(
    name = "Parties",
    depends = [
        PluginDependency("helper"),
    ]
)
class PartySpigotPlugin : JavaPlugin()
{
    override fun onEnable()
    {
        PartyService.configure()
    }
}
