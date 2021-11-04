package xyz.fragbots.sandboxcore.commands

import de.tr7zw.nbtapi.NBTItem
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import xyz.fragbots.sandboxcore.backend.data.Ranks
import xyz.fragbots.sandboxcore.utils.item.ItemExtensions.getSkyblockItem
import xyz.fragbots.sandboxcore.utils.item.ItemExtensions.getSkyblockItemInstance
import xyz.fragbots.sandboxcore.utils.item.ItemExtensions.setSkyblockData
import xyz.fragbots.sandboxcore.utils.player.PlayerExtensions.getHighestRank
import xyz.fragbots.sandboxcore.utils.player.PlayerExtensions.getStats
import xyz.fragbots.sandboxcore.utils.player.PlayerExtensions.sendFormattedMessage

class GetNBTCommand : Command("getnbt") {
    init {
        this.description = "Shows ADMINS nbt data for items"
        this.usageMessage = "/getnbt"
    }
    override fun execute(sender: CommandSender, label: String, args: Array<out String>): Boolean {
        if(sender !is Player) return false
        if(sender.getHighestRank()!=Ranks.ADMIN) {
            sender.sendFormattedMessage("&cYou do not have the required permissions to run this command!")
            return true
        }
        if(sender.itemInHand==null) {
            sender.sendFormattedMessage("&cPlease hold a valid SkyblockItem!")
            return true
        }
        if(sender.itemInHand.type== Material.AIR){
            sender.sendFormattedMessage("&cPlease hold a valid SkyblockItem!")
            return true
        }
        val item = NBTItem(sender.itemInHand)
        sender.sendMessage(item.getString("itemData"))
        return true
    }
}