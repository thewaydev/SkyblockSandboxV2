package xyz.fragbots.sandboxcore.backend

import xyz.fragbots.sandboxcore.backend.data.SandboxPlayer
import com.google.gson.Gson
import net.dongliu.requests.Requests
import org.bukkit.Bukkit
import xyz.fragbots.sandboxcore.SandboxCore
import xyz.fragbots.sandboxcore.backend.data.SandboxInventory

object SandboxBackend {
    val headers = hashMapOf<String,Any>("apiKey" to APIConfig.key)
    val jsonHeaders = hashMapOf("apiKey" to APIConfig.key,"Content-Type" to "application/json")
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
    fun setInvContents(uuid:String, invContents: String) {
        Requests.post(APIConfig.backend+"/players/setinvcontents/$uuid").headers(jsonHeaders).jsonBody(hashMapOf("invContents" to invContents)).send()
    }
    fun setArmorContents(uuid:String, armorContents: String) {
        Requests.post(APIConfig.backend+"/players/setarmorcontents/$uuid").headers(jsonHeaders).jsonBody(hashMapOf("armorContents" to armorContents)).send()
    }
    fun getInvContents(uuid:String) : SandboxInventory {
        return gson.fromJson(Requests.get(APIConfig.backend+"/players/inv/$uuid").headers(headers).send().readToText(),SandboxInventory::class.java)
    }
}