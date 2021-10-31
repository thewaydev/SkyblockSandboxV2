package xyz.fragbots.sandboxcore.utils.player

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitTask
import xyz.fragbots.sandboxcore.SandboxCore
import xyz.fragbots.sandboxcore.items.SkyblockItemAbility
import xyz.fragbots.sandboxcore.utils.player.PlayerExtensions.getStats
import xyz.fragbots.sandboxcore.utils.player.PlayerExtensions.sendActionBarMessage

class ActionBarManager {
    val playerAbilityActionBars = HashMap<Player, ActionBarData>()
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
        val healthString = "&c${dynStatManager.getHealth(player)}/${stats.health}❤"
        var midString = ""
        if(stats.defense!=0.toLong()){
            midString = "    &a❈${stats.defense} Defense"
        }
        val playerAbility = playerAbilityActionBars[player]
        if(playerAbility!=null){
            midString = playerAbility.message
        }
        val intelString = "    &b${dynStatManager.getIntel(player)}/${stats.intel}✎"
        return healthString+midString+intelString
    }

    fun setAbilityUsed(player: Player, ability: SkyblockItemAbility) {
        val data = ActionBarData(
            "    &b-${ability.manaCost} Mana (&6${ability.name}&b)",
            Bukkit.getScheduler().runTaskLater(SandboxCore.instance, { playerAbilityActionBars.remove(player) }, 25)
        )
        val currData = playerAbilityActionBars[player]
        if(currData!=null){
            Bukkit.getScheduler().cancelTask(currData.runnable.taskId)
        }
        playerAbilityActionBars[player] = data

    }

    data class ActionBarData(val message:String,val runnable:BukkitTask)

}