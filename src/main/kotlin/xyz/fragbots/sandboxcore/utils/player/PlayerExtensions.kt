package xyz.fragbots.sandboxcore.utils.player

import net.minecraft.server.v1_8_R3.IChatBaseComponent
import net.minecraft.server.v1_8_R3.PacketPlayOutChat
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import xyz.fragbots.sandboxcore.SandboxCore
import xyz.fragbots.sandboxcore.backend.SandboxBackend
import xyz.fragbots.sandboxcore.backend.data.Ranks
import xyz.fragbots.sandboxcore.backend.data.SandboxPlayer
import xyz.fragbots.sandboxcore.entitites.SkyblockEntity
import xyz.fragbots.sandboxcore.utils.Utils
import xyz.fragbots.sandboxcore.utils.item.ItemExtensions.getSkyblockItem


object PlayerExtensions {
    fun Player.getStats():PlayerStats {
        val stats = PlayerStats(this)

        if(itemInHand!=null) stats.addItemStats(itemInHand.getSkyblockItem())
        if(inventory.helmet!=null) stats.addItemStats(inventory.helmet.getSkyblockItem())
        if(inventory.chestplate!=null) stats.addItemStats(inventory.chestplate.getSkyblockItem())
        if(inventory.leggings!=null) stats.addItemStats(inventory.leggings.getSkyblockItem())
        if(inventory.boots!=null) stats.addItemStats(inventory.boots.getSkyblockItem())

        //TODO Talismans/Pets

        return stats
    }

    fun Player.getNearbySkyblockEntities(x:Double,y:Double,z:Double): ArrayList<SkyblockEntity> {
        val entities = getNearbyEntities(x,y,z).filter {SandboxCore.instance.entityManager.isSkyblockEntity(it as LivingEntity)}
        val sbEntities = ArrayList<SkyblockEntity>()
        entities.forEach {
            val sbEntity = SandboxCore.instance.entityManager.getSkyblockEntity(it as LivingEntity)
            if(sbEntity!=null){
                sbEntities.add(sbEntity)
            }
        }
        return sbEntities
    }

    fun Player.sendActionBarMessage(message: String) {
        val craftPlayer = (this as CraftPlayer)
        val cbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"${Utils.format(message)}\"}")
        val ppoc = PacketPlayOutChat(cbc, 2.toByte())
        craftPlayer.handle.playerConnection.sendPacket(ppoc)
    }

    fun Player.sendFormattedMessage(msg:String) {
        sendMessage(Utils.format(msg))
    }

    fun Player.getSbPlayer(): SandboxPlayer {
        return SandboxBackend.getPlayer(uniqueId.toString())
    }
    fun Player.isStaff():Boolean {
        return getStaffRank()!= Ranks.DEFAULT
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