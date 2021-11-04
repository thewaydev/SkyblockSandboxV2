package xyz.fragbots.sandboxcore.guis

import fr.minuskube.inv.ClickableItem
import fr.minuskube.inv.SmartInventory
import fr.minuskube.inv.content.InventoryContents
import fr.minuskube.inv.content.InventoryProvider
import fr.minuskube.inv.content.SlotIterator
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import xyz.fragbots.sandboxcore.SandboxCore
import xyz.fragbots.sandboxcore.items.SkyblockItemData
import xyz.fragbots.sandboxcore.utils.Utils

class EnchantsGUI(val item:ItemStack,val data:SkyblockItemData) : InventoryProvider {

    companion object {
        fun getInventory(item:ItemStack,data:SkyblockItemData): SmartInventory {
            return SmartInventory.builder()
                .id("sbEnchantsInventory")
                .provider(EnchantsGUI(item,data))
                .size(6,9)
                .title(Utils.format("&2Skyblock Enchants"))
                .manager(SandboxCore.instance.inventoryManager)
                .build()
        }
    }

    override fun init(player: Player, contents: InventoryContents) {
        val type = data.getItemType()
        val enchants = SandboxCore.instance.enchantManager.getEnchantsForItemType(type)

        val enchantItems = ArrayList<ClickableItem>()

        for(enchant in enchants) {
            val item = ItemStack(Material.ENCHANTED_BOOK)
            val itemMeta = item.itemMeta
            itemMeta.displayName = Utils.format("&9${enchant.name} Enchant")
            itemMeta.lore = listOf("",Utils.format("&eClick to &aapply&7/&cremove"),Utils.format("&ethis enchant!"))
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS)
            itemMeta.addEnchant(Enchantment.DURABILITY,1 , true)
            item.itemMeta = itemMeta
            enchantItems.add(ClickableItem.of(item) {_ ->
                EnchSelectorGUI.getInventory(this.item,this.data,enchant).open(player)
            })
        }

        contents.pagination().setItems(*enchantItems.toTypedArray())
        contents.pagination().setItemsPerPage(54)

        contents.pagination().addToIterator(contents.newIterator(SlotIterator.Type.HORIZONTAL,0,0))

    }

    override fun update(player: Player, contents: InventoryContents) {}
}