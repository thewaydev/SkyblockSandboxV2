package xyz.fragbots.sandboxcore.commands

import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.Default
import de.thehellscode.core.util.ItemSerialization
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import xyz.fragbots.sandboxcore.SandboxCore
import xyz.fragbots.sandboxcore.guis.ItemsGUI
import xyz.fragbots.sandboxcore.items.SkyblockItemFactory
import xyz.fragbots.sandboxcore.items.SkyblockItemIDS
import xyz.fragbots.sandboxcore.utils.player.PlayerExtensions.getStats

class ItemCommand : Command("item"){
    init {
        this.description = "Displays list of all items"
        this.usageMessage = "/item"
        this.aliases = listOf("items","i")
    }
    override fun execute(sender: CommandSender, label: String, args: Array<out String>): Boolean {
        if(sender !is Player) return false
        ItemsGUI.getInventory().open(sender)
        return true
    }
}