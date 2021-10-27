package xyz.fragbots.sandboxcore.listeners

import org.bukkit.entity.EntityType
import org.bukkit.entity.Zombie
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.CreatureSpawnEvent
import org.bukkit.event.entity.EntityCombustEvent
import xyz.fragbots.sandboxcore.SandboxCore

class EntityListener : Listener {
    @EventHandler
    fun onCombust(event:EntityCombustEvent){
        val entity = event.entity
        if(entity.hasMetadata("sbEntityId")){
            event.isCancelled = true
        }
    }

    /**
     * Stop those damn chicken jockeys >:(
     */
    @EventHandler
    fun onMobSpawn(event:CreatureSpawnEvent){
        val creature = event.entity
        SandboxCore.instance.entityManager.getSkyblockEntity(creature) ?: return
        if(creature is Zombie){
            if(creature.isInsideVehicle){
                creature.vehicle.remove()
            }
        }
    }
}