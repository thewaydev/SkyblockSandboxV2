package xyz.fragbots.sandboxcore.listeners

import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import xyz.fragbots.sandboxcore.SandboxCore

/*
 * Manages all damage in skyblock.
 * https://github.com/KingRainbow44/SkyblockSandbox/blob/main/src/main/java/tk/skyblocksandbox/skyblocksandbox/listener/DamageListener.java
 */
class DamageListener : Listener{

    @EventHandler
    fun onDamage(event:EntityDamageEvent) {
        if(event is EntityDamageByEntityEvent) {
            val entityEvent = event
            val damager: Entity = entityEvent.damager
            val damagee: Entity = entityEvent.entity

            /*
             * Scenario Breakdown:
             * - SBEntity damaged by Player (PvE)
             * - Player damaged by SBEntity (PvE)
             */

            /*
             * 1st Scenario SBEntity damaged by Player
             */
            if(damager is Player && damagee.hasMetadata("sbEntityId")) {
                event.damage = 0.0
                val sbEntity = SandboxCore.instance.entityManager.getSkyblockEntity(damagee as LivingEntity) ?: return
                SandboxCore.instance.damageExecutor.executePVEMelee(damager,sbEntity)
                return
            }

        }
        event.isCancelled = true
    }
}