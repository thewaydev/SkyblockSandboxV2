package xyz.fragbots.sandboxcore.items

import de.tr7zw.nbtapi.NBTItem
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import xyz.fragbots.sandboxcore.utils.Utils


/*
    * Blueprint class for all items in skyblock
    * Heavily inspired by: https://github.com/KingRainbow44/SkyblockSandbox/blob/main/src/main/java/tk/skyblocksandbox/skyblocksandbox/item/SandboxItem.java
 */
abstract class SkyblockItem(val baseMat:Material,val itemName:String,val id:String){

    /*
        * Creates the item with all the default values
        * Code taken from KingRainbow44's repo
     */
    fun create():ItemStack{
        val item: ItemStack = ItemStack(baseMat)

        val meta = item.itemMeta
        if (meta != null) {
            meta.spigot().isUnbreakable = true
            meta.displayName = Utils.rarityToColor(getItemData(true).getRarity()).toString() + itemName
            meta.lore = ArrayList(getLore())
            meta.addItemFlags(
                ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_ENCHANTS,
                ItemFlag.HIDE_POTION_EFFECTS
            )
        }
        item.itemMeta = meta

        addNbt(item)

        return item
    }

    /*
        * Adds nbt data to all skyblock items
     */
    fun addNbt(item:ItemStack){
        val itemNbt = NBTItem(item,true)
        itemNbt.setString("itemData",SkyblockItemData.adapter.toJson(getItemData(true)))
    }

    /*
        * Abstract Methods (All taken from KingRainbow44's Repository)
     */

    open fun ability(action: Int, player: Player) {} // A 'null' method because not all items have an ability.

    open fun shouldCancel(): Boolean {
        return false
    }

    open fun armorAbility(player: Player) {} // A 'null' method because not all armor pieces have an ability.


    open fun onWear(player: Player) {} // Called when a player holds/equips the item.


    open fun onRemove(player: Player) {} // Called when a player removes/stops holding the item.


    abstract fun getLore():Collection<String>

    abstract fun getItemData(create:Boolean,item:ItemStack? = null):SkyblockItemData
}