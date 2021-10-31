package xyz.fragbots.sandboxcore.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import xyz.fragbots.sandboxcore.items.reforges.weapons.SharpReforge
import xyz.fragbots.sandboxcore.items.reforges.weapons.SpicyReforge
import xyz.fragbots.sandboxcore.utils.item.ItemExtensions.getSkyblockItem
import xyz.fragbots.sandboxcore.utils.item.ItemExtensions.reforge
import xyz.fragbots.sandboxcore.utils.player.PlayerExtensions.getSbPlayer

class ReforgeCommand : Command("reforge") {
    init {
        this.description = "Add a reforge to the item you're currently holding.";
        this.usageMessage = "/reforge <reforge>"
    }
    override fun execute(sender: CommandSender?, label: String?, args: Array<out String>?): Boolean {
        if(sender !is Player) return true;
        val player = sender as Player;
        var item = player.itemInHand;
        item.reforge(SpicyReforge());
        return true;
    }
}