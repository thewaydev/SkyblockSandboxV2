package xyz.fragbots.sandboxcore.utils.player

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerLoginEvent
import org.bukkit.event.player.PlayerQuitEvent
import xyz.fragbots.sandboxcore.SandboxCore
import xyz.fragbots.sandboxcore.utils.player.PlayerExtensions.getStats
import java.math.BigInteger
import java.util.*
import kotlin.collections.HashMap
import kotlin.properties.Delegates

class DynamicStatManager : Listener {
    val players = HashMap<Player,DynamicStats>()
    var regenRunnable by Delegates.notNull<Int>()
    var rCounter = -1
    init {
        startRegenRunnable()
    }

    private fun startRegenRunnable() {
        regenRunnable = Bukkit.getScheduler().scheduleSyncRepeatingTask(SandboxCore.instance, {
            rCounter++
            for(set in players){
                val player = set.key
                val stats = set.value
                if(rCounter==1){
                    addHealth(player,2+(player.getStats().health.toDouble()*0.01).toLong())
                }
                addIntel(player,(player.getStats().intel.toDouble()*0.02).toLong())
            }
            if(rCounter==1){
                rCounter=-1
            }
        }, 0, 20)
    }

    fun getHealth(player:Player):Long {
        val stats = players[player] ?: return 0
        return stats.health
    }

    fun getIntel(player:Player):Long {
        val stats = players[player] ?: return 0
        return stats.intel
    }

    fun addHealth(player:Player,health: Long){
        val stats = players[player] ?: return
        stats.health = (stats.health + health).coerceAtMost(player.getStats().health)
    }

    fun addIntel(player:Player,mana: Long){
        val stats = players[player] ?: return
        stats.intel = (stats.intel + mana).coerceAtMost(player.getStats().intel)
    }

    fun removeIntel(player:Player, intel: Long) {
        val stats = players[player] ?: return
        stats.intel = (stats.intel - intel).coerceAtLeast(0)
    }

    @EventHandler
    fun playerJoin(event:PlayerLoginEvent) {
        //Doesn't count items in hand/armor if you don't do this
        Bukkit.getScheduler().scheduleSyncDelayedTask(SandboxCore.instance, {
            val player = event.player
            players[player] = DynamicStats(player.getStats().health,player.getStats().intel,0)
        }, 5)
    }

    @EventHandler
    fun playerQuit(event: PlayerQuitEvent) {
        players.remove(event.player)
    }

    data class DynamicStats(var health:Long, var intel: Long, var absorption: Long)
}