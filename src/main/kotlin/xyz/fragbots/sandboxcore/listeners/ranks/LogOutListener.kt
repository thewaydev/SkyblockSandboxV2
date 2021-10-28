package xyz.fragbots.sandboxcore.listeners.ranks

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent
import xyz.fragbots.sandboxcore.backend.SandboxBackend

class LogOutListener : Listener {
    @EventHandler
    fun logOut(event: PlayerQuitEvent) {
        SandboxBackend.cache.remove(event.player.uniqueId.toString())
    }
}