package xyz.fragbots.sandboxcore.commands

import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import xyz.fragbots.sandboxcore.guis.EnchantsGUI
import xyz.fragbots.sandboxcore.utils.item.ItemExtensions.getSkyblockItem
import xyz.fragbots.sandboxcore.utils.item.ItemExtensions.getSkyblockItemInstance
import xyz.fragbots.sandboxcore.utils.item.ItemExtensions.setSkyblockData
import xyz.fragbots.sandboxcore.utils.player.PlayerExtensions.getStats
import xyz.fragbots.sandboxcore.utils.player.PlayerExtensions.sendFormattedMessage

class EnchantCommand : Command("enchant"){
    init {
        this.description = "Allows you to apply enchants"
        this.usageMessage = "/enchant"
        this.aliases = listOf("ench")
    }
    override fun execute(sender: CommandSender, label: String, args: Array<out String>): Boolean {
        if(sender !is Player) return false
        val itemInHand = sender.itemInHand
        if(itemInHand==null) {
            sender.sendFormattedMessage("&cPlease hold a valid SkyblockItem!")
            return true
        }
        if(itemInHand.type== Material.AIR){
            sender.sendFormattedMessage("&cPlease hold a valid SkyblockItem!")
            return true
        }
        val item = itemInHand.getSkyblockItem()
        if(item==null){
            sender.sendFormattedMessage("&cPlease hold a valid SkyblockItem!")
            return true
        }
        EnchantsGUI.getInventory(itemInHand,item).open(sender)
        sender.updateInventory()
        return true
    }
}