package xyz.fragbots.sandboxcore.utils.item

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import de.tr7zw.nbtapi.NBTItem
import net.minecraft.server.v1_8_R3.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import xyz.fragbots.sandboxcore.SandboxCore
import xyz.fragbots.sandboxcore.items.SkyblockConsts
import xyz.fragbots.sandboxcore.items.SkyblockItem
import xyz.fragbots.sandboxcore.items.SkyblockItemData
import xyz.fragbots.sandboxcore.items.reforges.SkyblockReforge
import xyz.fragbots.sandboxcore.items.reforges.SkyblockReforgeStats
import xyz.fragbots.sandboxcore.utils.LoreGenerator
import xyz.fragbots.sandboxcore.utils.item.ItemExtensions.getSkyblockItem
import xyz.fragbots.sandboxcore.utils.player.PlayerExtensions.getStats
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


    /**
     * Apply a reforge to an item.
     * @param reforge The reforge you want to apply.
     * @return true when reforge applying succeeded
     */
    fun ItemStack.reforge(reforge: SkyblockReforge, player: Player): Boolean {
        val nbtItem = NBTItem(this, true);
        var gson = Gson()
        var data: SkyblockItemData = gson.fromJson(nbtItem.getString("itemData"), SkyblockItemData::class.java);
        if(!data.canReforge()) return false;
        if(data.itemType != reforge.type) return false;
        var stats: SkyblockReforgeStats? = when(data.rarity) {
            SkyblockConsts.COMMON -> reforge.commonStats;
            SkyblockConsts.UNCOMMON -> reforge.uncommonStats;
            SkyblockConsts.RARE -> reforge.rareStats;
            SkyblockConsts.EPIC -> reforge.epicStats;
            SkyblockConsts.LEGENDARY -> reforge.legendaryStats;
            SkyblockConsts.MYTHIC -> reforge.mythicStats;
            SkyblockConsts.SUPREME -> reforge.divineStats;
            else -> null;
        }
        data.reforgeStats = stats;
        data.reforgeName = reforge.name;
        setSkyblockData(data);
        this.getSkyblockItemInstance()!!.update(this, player.getStats());
        return true;
    }

    fun ItemStack.setSkyblockData(itemData: SkyblockItemData) {
        if(!isSkyblockItem()) return
        val nbtItem = NBTItem(this,true)
        return nbtItem.setString("itemData",SkyblockItemData.adapter.toJson(itemData))
    }
}