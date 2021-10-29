package xyz.fragbots.sandboxcore.utils.player

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import xyz.fragbots.sandboxcore.SandboxCore
import xyz.fragbots.sandboxcore.utils.player.PlayerExtensions.getStats
import xyz.fragbots.sandboxcore.utils.player.PlayerExtensions.sendActionBarMessage

class ActionBarManager {
    init {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(SandboxCore.instance, {
            for(player in Bukkit.getOnlinePlayers()) {
                player.sendActionBarMessage(generateLore(player))
            }
        }, 0 ,5)
    }

    fun generateLore(player:Player): String{
        val stats = player.getStats()
        val dynStatManager = SandboxCore.instance.dynamicStatManager
        val healthString = "&c❤${dynStatManager.getHealth(player)}/${stats.health}"
        var midString = ""
        if(stats.defense!=0.toLong()){
            midString = "    &a❈${stats.defense}"
        }
        val intelString = "    &b✎${dynStatManager.getIntel(player)}/${stats.intel}"
        return healthString+midString+intelString
    }


}