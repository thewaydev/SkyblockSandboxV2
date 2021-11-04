package xyz.fragbots.sandboxcore.items.enchants

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import xyz.fragbots.sandboxcore.items.SkyblockConsts
import xyz.fragbots.sandboxcore.utils.damage.SkyblockMagicDamageEvent
import xyz.fragbots.sandboxcore.utils.damage.SkyblockMeleeDamageEvent
import xyz.fragbots.sandboxcore.utils.damage.SkyblockRangedDamageEvent
import xyz.fragbots.sandboxcore.utils.item.ItemExtensions.getSkyblockItem

abstract class Enchant : Listener {
    abstract val name:String
    abstract val id:String
    abstract val maxLevel:Int
    abstract val worksOn:List<Int>
    val canBypassMax:Boolean = false
    val isUltimate:Boolean = false
    open fun onMeleeDamageWithEnch(event: SkyblockMeleeDamageEvent, level: Int) {}
    open fun onBowDamageWithEnch(event: SkyblockRangedDamageEvent, level: Int) {}
    open fun onMagicDamageWithEnch(event: SkyblockMagicDamageEvent, level: Int) {}

    @EventHandler
    fun onMeleeDamage(event: SkyblockMeleeDamageEvent) {
        if(!worksOn.contains(SkyblockConsts.SWORD)) return
        val itemInHand = event.player.itemInHand ?: return
        val sbItem = itemInHand.getSkyblockItem() ?: return
        if(sbItem.getItemType() != SkyblockConsts.SWORD) return
        val enchants = sbItem.getEnchants()
        val enchantLevel = enchants[id] ?: return
        onMeleeDamageWithEnch(event, enchantLevel)
    }

    @EventHandler
    fun onBowDamage(event: SkyblockRangedDamageEvent) {
        if(!worksOn.contains(SkyblockConsts.BOW)) return
        val itemInHand = event.player.itemInHand ?: return
        val sbItem = itemInHand.getSkyblockItem() ?: return
        if(sbItem.getItemType() != SkyblockConsts.BOW) return
        val enchants = sbItem.getEnchants()
        val enchantLevel = enchants[id] ?: return
        onBowDamageWithEnch(event, enchantLevel)
    }

    @EventHandler
    fun onRangedDamage(event: SkyblockMagicDamageEvent) {
        val itemInHand = event.player.itemInHand ?: return
        val sbItem = itemInHand.getSkyblockItem() ?: return
        if(!worksOn.contains(sbItem.getItemType())) return
        val enchants = sbItem.getEnchants()
        val enchantLevel = enchants[id] ?: return
        onMagicDamageWithEnch(event, enchantLevel)
    }

}