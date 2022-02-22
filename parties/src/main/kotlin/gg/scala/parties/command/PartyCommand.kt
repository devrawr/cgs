package gg.scala.parties.command

import co.aikar.commands.BaseCommand
import co.aikar.commands.CommandHelp
import co.aikar.commands.annotation.Default
import co.aikar.commands.annotation.HelpCommand

/**
 * @author GrowlyX
 * @since 12/17/2021
 */
object PartyCommand : BaseCommand()
{
    @Default
    @HelpCommand
    fun onHelp(help: CommandHelp)
    {
        help.showHelp()
    }
}
