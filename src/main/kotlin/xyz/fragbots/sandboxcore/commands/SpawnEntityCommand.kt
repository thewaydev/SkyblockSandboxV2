package xyz.fragbots.sandboxcore.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import xyz.fragbots.sandboxcore.SandboxCore
import xyz.fragbots.sandboxcore.api.data.Ranks
import xyz.fragbots.sandboxcore.api.extensions.PlayerExtensions.getStaffRank
import xyz.fragbots.sandboxcore.api.extensions.PlayerExtensions.isStaff
import xyz.fragbots.sandboxcore.entitites.mobs.SkyblockZombie
import xyz.fragbots.sandboxcore.items.SkyblockItemIDS
import xyz.fragbots.sandboxcore.utils.Utils

class SpawnEntityCommand : Command("spawnentity") {
    init {
        this.description = "Displays list of all items"
        this.usageMessage = "/item"
        this.aliases = listOf("items","i")
    }
    override fun execute(sender: CommandSender, label: String, args: Array<out String>): Boolean {
        if(sender !is Player) return false
        if(sender.getStaffRank()!=Ranks.ADMIN){
            sender.sendMessage(Utils.format("&cYou don't have permissions to run this command!"))
            return true
        }
        SkyblockZombie().spawn(sender.location)
        return true
    }
}