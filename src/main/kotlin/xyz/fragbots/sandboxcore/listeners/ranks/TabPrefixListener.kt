package xyz.fragbots.sandboxcore.listeners.ranks

import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import xyz.fragbots.sandboxcore.SandboxCore
import xyz.fragbots.sandboxcore.backend.data.Ranks
import xyz.fragbots.sandboxcore.utils.Utils
import xyz.fragbots.sandboxcore.utils.player.PlayerExtensions.getHighestRank

class TabPrefixListener(): Listener {
    val scoreboardManager = Bukkit.getScoreboardManager().newScoreboard
    val adminTeam = scoreboardManager.registerNewTeam("a_admin")
    val modTeam = scoreboardManager.registerNewTeam("b_mod")
    val helperTeam = scoreboardManager.registerNewTeam("c_helper")
    val vipTeam = scoreboardManager.registerNewTeam("d_vip")
    val defaultTeam = scoreboardManager.registerNewTeam("e_default")

    init {
        adminTeam.prefix = Utils.format(Ranks.ADMIN.namecolor)
        modTeam.prefix = Utils.format(Ranks.MOD.namecolor)
        helperTeam.prefix = Utils.format(Ranks.HELPER.namecolor)
        vipTeam.prefix = Utils.format(Ranks.VIP.namecolor)
        defaultTeam.prefix = Utils.format(Ranks.DEFAULT.namecolor)
    }
    @EventHandler
    fun login(event:PlayerJoinEvent){
        Bukkit.getScheduler().runTaskAsynchronously(SandboxCore.instance) {
            val player = event.player
            val name = event.player.name
            val pRank = player.getHighestRank()
            Bukkit.getScheduler().runTask(SandboxCore.instance) {
                when(pRank) {
                    Ranks.ADMIN -> adminTeam.addEntry(name)
                    Ranks.MOD -> modTeam.addEntry(name)
                    Ranks.HELPER -> helperTeam.addEntry(name)
                    Ranks.VIP -> vipTeam.addEntry(name)
                    Ranks.DEFAULT -> defaultTeam.addEntry(name)
                }
                player.playerListName = Utils.format("${pRank.prefix}${if(pRank.prefix.isNotEmpty()) " " else ""}${pRank.namecolor}${player.name}")
            }
            player.scoreboard = scoreboardManager
        }

    }

    @EventHandler
    fun logout(event: PlayerQuitEvent){
        Bukkit.getScheduler().runTaskAsynchronously(SandboxCore.instance) {
            val player = event.player
            val name = event.player.name
            val pRank = player.getHighestRank()
            Bukkit.getScheduler().runTask(SandboxCore.instance){
                when (pRank) {
                    Ranks.ADMIN -> adminTeam.removeEntry(name)
                    Ranks.MOD -> modTeam.removeEntry(name)
                    Ranks.HELPER -> helperTeam.removeEntry(name)
                    Ranks.VIP -> vipTeam.removeEntry(name)
                    Ranks.DEFAULT -> defaultTeam.removeEntry(name)
                }
            }
        }
    }
}