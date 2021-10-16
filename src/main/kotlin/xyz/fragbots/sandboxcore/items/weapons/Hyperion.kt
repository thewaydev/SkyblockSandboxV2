package xyz.fragbots.sandboxcore.items.weapons

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import xyz.fragbots.sandboxcore.items.SkyblockConsts
import xyz.fragbots.sandboxcore.items.SkyblockItem
import xyz.fragbots.sandboxcore.items.SkyblockItemData
import xyz.fragbots.sandboxcore.items.SkyblockItemIDS
import xyz.fragbots.sandboxcore.utils.ItemExtensions.getSkyblockItem
import xyz.fragbots.sandboxcore.utils.ItemExtensions.isSkyblockItem
import xyz.fragbots.sandboxcore.utils.LoreGenerator
import xyz.fragbots.sandboxcore.utils.Utils

class Hyperion : SkyblockItem(Material.IRON_SWORD,"Hyperion",SkyblockItemIDS.HYPERION){
    override fun getLore(): Collection<String> {
        return LoreGenerator(
            Utils.format("&7Deals &a+50% &7damage to"),
            Utils.format("&7Withers. Grants &c+1 ❁ Damage"),
            Utils.format("&7and &a+2&b ✎ Intelligence"),
            Utils.format("&7per &cCatacombs &7level."),
            " ",
            Utils.format("&7Your Catacombs level: &c0"))
            .generic(getItemData(true))
    }

    override fun getItemData(create:Boolean, item:ItemStack?): SkyblockItemData {
        if(create||item==null||!item.isSkyblockItem()) {
            return getDefaultData()
        }else {
            return item.getSkyblockItem() ?: return getDefaultData()
        }
    }
    private fun getDefaultData():SkyblockItemData {
        val data = SkyblockItemData(id)

        data.rarity = SkyblockConsts.LEGENDARY
        data.itemType = SkyblockConsts.SWORD
        data.reforgeable = true
        data.dungeonitem = true

        data.baseDamage = 260
        data.baseStrength = 150
        data.baseIntel = 350
        data.baseFerocity = 30

        return data
    }
}






