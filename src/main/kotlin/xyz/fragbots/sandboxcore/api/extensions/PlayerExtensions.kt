package xyz.fragbots.sandboxcore.api.extensions

import org.bukkit.entity.Player
import xyz.fragbots.sandboxcore.api.SandboxBackend
import xyz.fragbots.sandboxcore.api.data.Ranks
import xyz.fragbots.sandboxcore.api.data.SandboxPlayer

object PlayerExtensions {
    fun Player.getSbPlayer(): SandboxPlayer {
        return SandboxBackend.getPlayer(uniqueId.toString())
    }
    fun Player.isStaff():Boolean {
        return getStaffRank()!=Ranks.DEFAULT
    }
    fun Player.getStaffRank(): Ranks {
        return getSbPlayer().staffRank
    }
    fun Player.getPlayerRank(): Ranks {
        return getSbPlayer().playerRank
    }
    fun Player.getHighestRank(): Ranks {
        if(!isStaff()){
            return getPlayerRank()
        }
        return getStaffRank()
    }
}