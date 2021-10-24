package xyz.fragbots.sandboxcore.utils.player

import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import xyz.fragbots.sandboxcore.SandboxCore
import xyz.fragbots.sandboxcore.entitites.SkyblockEntity
import xyz.fragbots.sandboxcore.utils.Utils
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

    fun Player.getNearbySkyblockEntities(x:Double,y:Double,z:Double): ArrayList<SkyblockEntity> {
        val entities = getNearbyEntities(x,y,z).filter {SandboxCore.instance.entityManager.isSkyblockEntity(it as LivingEntity)}
        val sbEntities = ArrayList<SkyblockEntity>()
        entities.forEach {
            val sbEntity = SandboxCore.instance.entityManager.getSkyblockEntity(it as LivingEntity)
            if(sbEntity!=null){
                sbEntities.add(sbEntity)
            }
        }
        return sbEntities
    }

    fun Player.sendFormattedMessage(msg:String) {
        sendMessage(Utils.format(msg))
    }
}