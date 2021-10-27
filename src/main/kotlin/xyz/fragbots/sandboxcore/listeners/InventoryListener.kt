package xyz.fragbots.sandboxcore.listeners

import de.thehellscode.core.util.ItemSerialization
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerLoginEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.inventory.ItemStack
import xyz.fragbots.sandboxcore.SandboxCore
import xyz.fragbots.sandboxcore.backend.SandboxBackend
import java.util.*

class InventoryListener : Listener {

    @EventHandler
    fun onJoin(event:PlayerLoginEvent) {
        Bukkit.getScheduler().runTaskAsynchronously(SandboxCore.instance) {
            val player = event.player
            val sbInv = SandboxBackend.getInvContents(player.uniqueId.toString())
            Bukkit.getScheduler().runTask(SandboxCore.instance) {
                handleInvContents(player, sbInv.invContents)
                handleArmorContents(player, sbInv.armorContents)
            }
        }
    }

    @EventHandler
    fun onLeave(event:PlayerQuitEvent) {
        val player = event.player
        val inventory = player.inventory
        Bukkit.getScheduler().runTaskAsynchronously(SandboxCore.instance) {
            val playerInvString = ItemSerialization.playerInventoryToBase64(inventory)
            val armorInvString = ItemSerialization.playerArmorToBase64(inventory)
            if(playerInvString!=null) SandboxBackend.setInvContents(player.uniqueId.toString(), playerInvString)
            if(armorInvString!=null) SandboxBackend.setArmorContents(player.uniqueId.toString(), armorInvString)
        }
    }

    private fun handleInvContents(player:Player, invContents: String?){
        if(invContents != null){
            val inv = ItemSerialization.itemStackArrayFromBase64(invContents)
            if(inv != null){
                player.inventory.contents = inv.toTypedArray()
                player.updateInventory()
                return
            }
        }
        player.inventory.clear()
        player.updateInventory()
    }
    private fun handleArmorContents(player:Player, armorContents: String?){
        if(armorContents != null){
            val inv = ItemSerialization.itemStackArrayFromBase64(armorContents)
            if(inv != null){
                player.inventory.armorContents = inv.toTypedArray()
                player.updateInventory()
                return
            }
        }
        player.inventory.helmet = ItemStack(Material.AIR)
        player.inventory.chestplate = ItemStack(Material.AIR)
        player.inventory.chestplate = ItemStack(Material.AIR)
        player.inventory.chestplate = ItemStack(Material.AIR)
        player.updateInventory()
    }
}