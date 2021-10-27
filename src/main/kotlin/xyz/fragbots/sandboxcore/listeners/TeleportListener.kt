package xyz.fragbots.sandboxcore.listeners

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityPortalEvent

class TeleportListener : Listener {
    @EventHandler
    fun portalEvent(e: EntityPortalEvent){
        if(e.entity !is Player){
            e.isCancelled = true
        }
    }
}