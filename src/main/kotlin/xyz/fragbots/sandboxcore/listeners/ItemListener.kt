package xyz.fragbots.sandboxcore.listeners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerItemHeldEvent
import xyz.fragbots.sandboxcore.utils.item.ItemExtensions.getSkyblockItem
import xyz.fragbots.sandboxcore.utils.item.ItemExtensions.getSkyblockItemInstance
import xyz.fragbots.sandboxcore.utils.item.ItemExtensions.isSkyblockItem
import xyz.fragbots.sandboxcore.utils.player.PlayerExtensions.getStats

class ItemListener : Listener {

    @EventHandler
    fun changeItemHeldEvent(e:PlayerItemHeldEvent) {
        val inventory = e.player.inventory
        val item = e.player.inventory.getItem(e.newSlot) ?: return
        if(item.isSkyblockItem()){
            val sbItem = item.getSkyblockItemInstance() ?: return
            sbItem.update(item, e.player.getStats())
            e.player.updateInventory()
        }
    }

}