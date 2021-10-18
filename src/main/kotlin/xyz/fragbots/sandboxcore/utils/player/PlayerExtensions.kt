package xyz.fragbots.sandboxcore.utils.player

import org.bukkit.entity.Player
import xyz.fragbots.sandboxcore.utils.item.ItemExtensions.getSkyblockItem

object PlayerExtensions {
    fun Player.getStats():PlayerStats {
        val stats = PlayerStats(this)

        if(itemInHand!=null) stats.addItemStats(itemInHand.getSkyblockItem())
        if(inventory.helmet!=null) stats.addItemStats(inventory.helmet.getSkyblockItem())
        if(inventory.chestplate!=null) stats.addItemStats(inventory.chestplate.getSkyblockItem())
        if(inventory.leggings!=null) stats.addItemStats(inventory.leggings.getSkyblockItem())
        if(inventory.boots!=null) stats.addItemStats(inventory.boots.getSkyblockItem())

        //TODO Talismans/Pets

        return stats
    }
}