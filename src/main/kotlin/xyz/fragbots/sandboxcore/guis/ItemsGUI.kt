package xyz.fragbots.sandboxcore.guis

import fr.minuskube.inv.ClickableItem
import fr.minuskube.inv.SmartInventory
import fr.minuskube.inv.content.InventoryContents
import fr.minuskube.inv.content.InventoryProvider
import fr.minuskube.inv.content.SlotIterator
import net.wesjd.anvilgui.AnvilGUI
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import xyz.fragbots.sandboxcore.SandboxCore
import xyz.fragbots.sandboxcore.utils.Utils
import xyz.fragbots.sandboxcore.utils.player.PlayerExtensions.getStats


class ItemsGUI(val search:String?=null) : InventoryProvider{
    companion object {
        fun getInventory(search:String?=null): SmartInventory{
            return SmartInventory.builder()
                .id("sbItemsInventory")
                .provider(ItemsGUI(search))
                .size(6,9)
                .title(Utils.format("&2Skyblock Items"))
                .manager(SandboxCore.instance.inventoryManager)
                .build()
        }
    }
    override fun init(player: Player, contents: InventoryContents) {
        val pagination = contents.pagination()

        var sbItems = SandboxCore.instance.itemFactory.getItems()

        if(search!=null) {
            sbItems = sbItems.filter { it.itemName.lowercase().contains(search.lowercase()) }
        }

        val items = ArrayList<ClickableItem>()

        for(sbItem in sbItems) {
            val item = sbItem.create(player.getStats())
            val meta = item.itemMeta
            val lore = meta.lore
            lore.add("")
            lore.add(Utils.format("&eClick to receive!"))
            meta.lore = lore
            item.itemMeta = meta
            items.add(ClickableItem.of(item) {e -> player.inventory.addItem(sbItem.create(player.getStats()))})
        }

        pagination.setItems(*items.toTypedArray())
        pagination.setItemsPerPage(45)

        pagination.addToIterator(contents.newIterator(SlotIterator.Type.HORIZONTAL,0,0))

        val glassFiller = ItemStack(Material.STAINED_GLASS_PANE, 1, 3.toShort())
        val glassMeta = glassFiller.itemMeta
        glassMeta.displayName = "§eClick on an item to get it!"
        glassMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS)
        glassMeta.addEnchant(Enchantment.DURABILITY, 1, true)
        glassFiller.itemMeta = glassMeta

        val searchItem = ItemStack(Material.PAPER)
        val searchMeta = searchItem.itemMeta
        searchMeta.displayName = "§aSearch"
        searchMeta.lore = listOf(Utils.format(""),Utils.format("&eClick to filter through"),Utils.format("&eall the items!"))
        searchMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS)
        searchMeta.addEnchant(Enchantment.DURABILITY, 1, true)
        searchItem.itemMeta = searchMeta

        val rightArrow = ItemStack(Material.SKULL_ITEM,1)
        rightArrow.durability = 3.toShort()
        val rightArrowMeta = rightArrow.itemMeta as SkullMeta
        rightArrowMeta.owner = "MHF_ArrowRight"
        rightArrowMeta.displayName = "§aNext Page"
        rightArrow.itemMeta = rightArrowMeta

        val leftArrow = ItemStack(Material.SKULL_ITEM,1)
        leftArrow.durability = 3.toShort()
        val leftArrowMeta = leftArrow.itemMeta as SkullMeta
        leftArrowMeta.owner = "MHF_ArrowLeft"
        leftArrowMeta.displayName = "§aPrev. Page"
        leftArrow.itemMeta = leftArrowMeta

        for(i in 0..8){
            contents.set(5,i, ClickableItem.empty(glassFiller))
        }
        contents.set(5,3, ClickableItem.of(leftArrow) { e -> getInventory().open(player, pagination.previous().page) })
        contents.set(5,4, ClickableItem.of(searchItem) {
            AnvilGUI.Builder()
                .onComplete { player: Player, text: String ->
                    getInventory(text).open(player)
                    return@onComplete AnvilGUI.Response.close()
                }
                .text(Utils.format("&aSearch"))
                .itemLeft(ItemStack(Material.NAME_TAG))
                .plugin(SandboxCore.instance)
                .open(player)
        })
        contents.set(5,5, ClickableItem.of(rightArrow) { e -> getInventory().open(player, pagination.next().page) })


    }

    override fun update(p0: Player?, p1: InventoryContents?) {}

}