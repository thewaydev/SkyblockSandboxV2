package xyz.fragbots.sandboxcore.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.reflections.Reflections
import xyz.fragbots.sandboxcore.SandboxCore
import xyz.fragbots.sandboxcore.items.reforges.SkyblockReforge
import xyz.fragbots.sandboxcore.utils.Utils
import xyz.fragbots.sandboxcore.utils.item.ItemExtensions.reforge

class ReforgeCommand : Command("reforge") {
    init {
        this.description = "Add a reforge to the item you're currently holding.";
        this.usageMessage = "/reforge <reforge>"

        registerReforges();
    }

    var reforges: HashMap<String, SkyblockReforge>? = null;

    private fun registerReforges() {
        if(reforges == null) reforges = HashMap();
        var reflections = Reflections("xyz.fragbots.sandboxcore");
        val subTypes: Set<Class<*>> = reflections.getSubTypesOf(SkyblockReforge::class.java)
        subTypes.forEach {
            var reforge: SkyblockReforge = it.getConstructor().newInstance() as SkyblockReforge;
            reforges!!.put(reforge.name.lowercase(), reforge);
        }
    }

    override fun execute(sender: CommandSender?, label: String?, args: Array<out String>?): Boolean {
        if (args == null) return true;
        if (args.isEmpty()) return true;
        if (sender !is Player) return true;

        val player: Player = sender;
        var item = player.itemInHand;
        if(!reforges!!.containsKey(args.get(0).lowercase())) {
            // send message
            sender.sendMessage(Utils.format("&cThat reforge was not found."))
            return true;
        }
        var reforge = reforges!!.get(args.get(0).lowercase());
        if(item.reforge(reforge!!, player)) {
           sender.sendMessage(Utils.format("&aReforge ${reforge.name} was successfully applied."))
            return true;
        }
        sender.sendMessage(Utils.format("&cSomething went wrong trying to reforge this item."))
        return true;
    }
}