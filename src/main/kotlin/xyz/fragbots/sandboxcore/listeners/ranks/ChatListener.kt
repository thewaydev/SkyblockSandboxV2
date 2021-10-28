package xyz.fragbots.sandboxcore.listeners.ranks

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import xyz.fragbots.sandboxcore.utils.Utils
import xyz.fragbots.sandboxcore.utils.player.PlayerExtensions.getHighestRank

class ChatListener : Listener {
    @EventHandler
    fun onAsyncChat(event: AsyncPlayerChatEvent){
        val player = event.player
        val pRank = player.getHighestRank()
        event.format = Utils.format("${pRank.prefix}${if(pRank.prefix.isNotEmpty()) " " else ""}${pRank.namecolor}${player.name}&f: ")+event.message
        println("aaa")
    }
}