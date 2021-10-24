package xyz.fragbots.sandboxcore.utils.item

import de.tr7zw.nbtapi.NBTItem
import net.minecraft.server.v1_8_R3.Material
import org.bukkit.inventory.ItemStack
import xyz.fragbots.sandboxcore.SandboxCore
import xyz.fragbots.sandboxcore.items.SkyblockItem
import xyz.fragbots.sandboxcore.items.SkyblockItemData
import java.lang.NullPointerException

object ItemExtensions {
    fun ItemStack.isSkyblockItem(): Boolean {
        if(this.type== Material.AIR) return false
        return try {
            val nbtItem = NBTItem(this)
            nbtItem.hasKey("itemData")
        }catch (e:NullPointerException){
            false
        }
    }
    fun ItemStack.getSkyblockItem(): SkyblockItemData? {
        if(!isSkyblockItem()) return null
        val nbtItem = NBTItem(this)
        return SkyblockItemData.adapter.fromJson(nbtItem.getString("itemData"))
    }
    fun ItemStack.getSkyblockItemInstance(): SkyblockItem? {
        val item = getSkyblockItem() ?: return null
        return SandboxCore.instance.itemFactory.getItem(item.id)
    }
}