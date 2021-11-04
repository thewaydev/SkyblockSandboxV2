package xyz.fragbots.sandboxcore.listeners

import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerLoginEvent
import org.bukkit.event.player.PlayerQuitEvent
import xyz.fragbots.sandboxcore.SandboxCore
import xyz.fragbots.sandboxcore.backend.data.Ranks
import xyz.fragbots.sandboxcore.utils.player.PlayerExtensions.getStaffRank

class PlayerJoinListener : Listener{

    @EventHandler
    fun playerLoginEvent(event: PlayerLoginEvent) {
        val player = event.player
        Bukkit.getScheduler().runTask(SandboxCore.instance) {
            val rank = player.getStaffRank()
            if(rank==Ranks.ADMIN){
                Bukkit.getScheduler().runTask(SandboxCore.instance) {
                    player.isOp = true
                }
            }
        }
    }

    @EventHandler
    fun playerJoinEvent(event: PlayerJoinEvent) {
        event.joinMessage = ""
    }

    @EventHandler
    fun playerQuitEvent(event: PlayerQuitEvent) {
        event.quitMessage = ""
    }
}