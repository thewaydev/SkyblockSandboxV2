package xyz.fragbots.sandboxcore.listeners

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityPortalEvent
import org.bukkit.event.player.PlayerPortalEvent

class TeleportListener : Listener {
    @EventHandler
    fun entityPortalEvent(e: EntityPortalEvent){
        if(e.entity !is Player){
            e.isCancelled = true
        }
    }
    @EventHandler
    fun playerPortalEvent(e: PlayerPortalEvent){
        e.isCancelled = true
    }
}