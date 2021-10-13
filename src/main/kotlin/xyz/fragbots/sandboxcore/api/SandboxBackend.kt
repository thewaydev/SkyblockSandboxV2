package xyz.fragbots.sandboxcore.api

import xyz.fragbots.sandboxcore.api.data.SandboxPlayer
import com.google.gson.Gson
import net.dongliu.requests.Requests
import org.bukkit.Bukkit
import xyz.fragbots.sandboxcore.SandboxCore

object SandboxBackend {
    val headers = hashMapOf<String,Any>("apiKey" to APIConfig.key)
    val gson = Gson()
    val cache = HashMap<String,SandboxPlayer>()
    fun getPlayer(uuid:String): SandboxPlayer {
        if(!cache.containsKey(uuid)){
            cache[uuid] = gson.fromJson(Requests.get(APIConfig.backend+"/players/$uuid").headers(headers).send().readToText(),
                SandboxPlayer::class.java)
            Bukkit.getScheduler().runTaskLater(SandboxCore.instance,{
                 cache.remove(uuid)
            },600)
        }
        return cache[uuid]!!
    }
}