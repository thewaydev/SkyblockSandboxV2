package xyz.fragbots.sandboxcore.listeners

import org.bukkit.entity.Arrow
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

            /**
             * Scenario Breakdown:
             * - SBEntity damaged by Player (PvE)
             * - SBEntity damaged by Arrow shot by Player (PAvE)
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

            /**
             * 2nd Scenario SbEntity damaged by Arrow shot by Player
             */
            if(damager is Arrow && damagee.hasMetadata("sbEntityId")){
                event.damage = 0.0
                val shooter = damager.shooter
                if(shooter !is Player) return
                val canCrit = damager.isCritical
                val sbEntity = SandboxCore.instance.entityManager.getSkyblockEntity(damagee as LivingEntity) ?: return
                SandboxCore.instance.damageExecutor.executePveRanged(shooter,sbEntity,canCrit)
                return
            }



        }

        event.isCancelled = true
    }
}