package xyz.fragbots.sandboxcore.items

import de.tr7zw.nbtapi.NBTItem
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import xyz.fragbots.sandboxcore.SandboxCore
import xyz.fragbots.sandboxcore.items.reforges.SkyblockReforge
import xyz.fragbots.sandboxcore.utils.Utils
import xyz.fragbots.sandboxcore.utils.item.ItemExtensions.getSkyblockItem
import xyz.fragbots.sandboxcore.utils.item.ItemExtensions.getSkyblockItemInstance
import xyz.fragbots.sandboxcore.utils.item.ItemExtensions.isSkyblockItem
import xyz.fragbots.sandboxcore.utils.player.PlayerExtensions.sendFormattedMessage
import xyz.fragbots.sandboxcore.utils.player.PlayerStats
import java.util.*
import kotlin.collections.ArrayList


/*
    * Blueprint class for all items in skyblock
    * Heavily inspired by: https://github.com/KingRainbow44/SkyblockSandbox/blob/main/src/main/java/tk/skyblocksandbox/skyblocksandbox/item/SandboxItem.java
 */
abstract class SkyblockItem(val baseMat:Material,val itemName:String,val id:String) : Listener{

    // Abilities
    open var ability1: SkyblockItemAbility? = null
    open var ability2: SkyblockItemAbility? = null
    open var ability3: SkyblockItemAbility? = null


    /*
        * Creates the item with all the default values
        * Code taken from KingRainbow44's repo
     */
    fun create(playerStats: PlayerStats):ItemStack{
        val item: ItemStack = ItemStack(baseMat)

        val meta = item.itemMeta
        if (meta != null) {
            meta.spigot().isUnbreakable = true
            meta.displayName = Utils.rarityToColor(getItemData(playerStats,true).getRarity()).toString() + itemName
            meta.lore = ArrayList(getLore(playerStats))
            meta.addItemFlags(
                ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_ENCHANTS,
                ItemFlag.HIDE_POTION_EFFECTS
            )
        }
        item.itemMeta = meta

        addNbt(playerStats,  item)

        return item
    }

    open fun update(item:ItemStack, playerStats: PlayerStats){
        val meta = item.itemMeta
        if(item.getSkyblockItem()!!.reforgeName != null) {
            /* if it works dont touch it */
            meta.displayName = "${Utils.rarityToColor(getItemData(playerStats, true).getRarity())}${item.getSkyblockItem()!!.reforgeName} ${this.itemName}"
        }
        meta.lore = ArrayList(getLore(playerStats,item))
        item.itemMeta = meta
    }


    /*
        * Adds nbt data to all skyblock items
     */
    fun addNbt(playerStats:PlayerStats, item:ItemStack){
        val itemNbt = NBTItem(item,true)
        itemNbt.setString("itemData",SkyblockItemData.adapter.toJson(getItemData(playerStats, true)))
    }

    val Player.holdingItem : Boolean
        get() {
            val item = itemInHand
            if(item==null||item.type==Material.AIR){
                return false
            }
            val sbItem = item.getSkyblockItemInstance() ?: return false
            return sbItem.id == id
        }

    @EventHandler
    fun abilityUserListener(event:PlayerInteractEvent){
        val player = event.player
        if(player.holdingItem){
            abilityUse(event)
        }
    }

    /*
        * Abstract Methods (All taken from KingRainbow44's Repository)
     */

    fun canUseAbility(player:Player, ability:SkyblockItemAbility) : Boolean {
        val dynStats = SandboxCore.instance.dynamicStatManager
        val currentIntel = dynStats.getIntel(player)
        return currentIntel>=ability.manaCost
    }

    fun sendManaMessage(player:Player) {
        player.sendFormattedMessage("&cYou do not have enough mana to do this!")
    }

    fun abilityUsed(usedBy:Player, ability: SkyblockItemAbility) {
        SandboxCore.instance.actionBarManager.setAbilityUsed(usedBy,ability)
        SandboxCore.instance.dynamicStatManager.removeIntel(usedBy,ability.manaCost.toLong())
    }

    open fun abilityUse(event:PlayerInteractEvent) {}

    open fun ability(action: Int, player: Player) {} // A 'null' method because not all items have an ability.

    open fun shouldCancel(): Boolean {
        return false
    }

    open fun armorAbility(player: Player) {} // A 'null' method because not all armor pieces have an ability.


    open fun onWear(player: Player) {} // Called when a player holds/equips the item.


    open fun onRemove(player: Player) {} // Called when a player removes/stops holding the item.


    abstract fun getLore(playerStats: PlayerStats, itemStack: ItemStack?=null):Collection<String>

    open fun getItemData(playerStats: PlayerStats,create:Boolean,item:ItemStack? = null):SkyblockItemData {
        if(create||item==null||!item.isSkyblockItem()) {
            return getDefaultData(playerStats)
        }else {
            return item.getSkyblockItem() ?: return getDefaultData(playerStats)
        }
    }


    abstract fun getDefaultData(playerStats: PlayerStats): SkyblockItemData

}
