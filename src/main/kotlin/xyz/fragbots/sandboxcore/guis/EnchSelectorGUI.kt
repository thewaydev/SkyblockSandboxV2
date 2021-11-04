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
import xyz.fragbots.sandboxcore.SandboxCore
import xyz.fragbots.sandboxcore.items.SkyblockItemData
import xyz.fragbots.sandboxcore.items.enchants.Enchant
import xyz.fragbots.sandboxcore.utils.Utils
import xyz.fragbots.sandboxcore.utils.item.ItemExtensions.getSkyblockItemInstance
import xyz.fragbots.sandboxcore.utils.item.ItemExtensions.setSkyblockData
import xyz.fragbots.sandboxcore.utils.player.PlayerExtensions.getStats
import xyz.fragbots.sandboxcore.utils.player.PlayerExtensions.sendFormattedMessage

class EnchSelectorGUI(val item:ItemStack,val data:SkyblockItemData, val enchant : Enchant) : InventoryProvider {

    companion object {
        fun getInventory(item:ItemStack,data:SkyblockItemData,enchant:Enchant): SmartInventory {
            return SmartInventory.builder()
                .id("sbEnchSelectorInventory")
                .provider(EnchSelectorGUI(item,data,enchant))
                .size(3,9)
                .title(Utils.format("&2Skyblock Enchants"))
                .manager(SandboxCore.instance.inventoryManager)
                .build()
        }
    }

    override fun init(player: Player, contents: InventoryContents) {
        val fillerGlass = ItemStack(Material.STAINED_GLASS_PANE,1,11.toShort())
        val fillerGlassMeta = fillerGlass.itemMeta
        fillerGlassMeta.displayName = ""
        fillerGlass.itemMeta = fillerGlassMeta

        val enchantItem = ItemStack(Material.ENCHANTED_BOOK)
        val enchantItemMeta = enchantItem.itemMeta
        enchantItemMeta.displayName = Utils.format("&9${enchant.name} Enchant")
        enchantItemMeta.lore = listOf("",Utils.format("&eChoose whether to &aapply&7/&cremove"))
        enchantItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS)
        enchantItemMeta.addEnchant(Enchantment.DURABILITY,1 , true)
        enchantItem.itemMeta = enchantItemMeta

        val removeEnchItem = ItemStack(Material.STAINED_CLAY,1,14.toShort())
        val removeEnchMeta = removeEnchItem.itemMeta
        removeEnchMeta.displayName = Utils.format("&cRemove")
        removeEnchMeta.lore = listOf("",Utils.format("&7Click to &cremove &7this enchant!"))
        removeEnchItem.itemMeta = removeEnchMeta

        val addEnchItem = ItemStack(Material.STAINED_CLAY,1,13.toShort())
        val addEnchMeta = addEnchItem.itemMeta
        addEnchMeta.displayName = Utils.format("&aApply")
        addEnchMeta.lore = listOf("",Utils.format("&7Click to &aapply &7this enchant!"))
        addEnchItem.itemMeta = addEnchMeta

        contents.fill(ClickableItem.empty(fillerGlass))
        contents.set(1,4,ClickableItem.empty(enchantItem))
        contents.set(1,2,ClickableItem.of(removeEnchItem) {
            data.removeEnchant(enchant.id)
            val instance = item.getSkyblockItemInstance() ?: return@of
            item.setSkyblockData(data)
            instance.update(item,player.getStats())
            player.updateInventory()
            player.closeInventory()
        })
        contents.set(1,6,ClickableItem.of(addEnchItem) {
            AnvilGUI.Builder()
                .onComplete { player: Player, text: String ->
                    val level = try {
                        Integer.parseInt(text)
                    }catch (e:NumberFormatException){
                        player.sendFormattedMessage("&cInvalid Level!")
                        return@onComplete AnvilGUI.Response.close()
                    }
                    if(level>enchant.maxLevel&&!enchant.canBypassMax){
                        player.sendFormattedMessage("&cLevel exceeds limit!")
                    }
                    data.addEnchant(enchant.id,level)
                    val instance = item.getSkyblockItemInstance() ?: return@onComplete AnvilGUI.Response.close()
                    item.setSkyblockData(data)
                    instance.update(item,player.getStats())
                    player.updateInventory()
                    return@onComplete AnvilGUI.Response.close()
                }
                .text(Utils.format("&aSearch"))
                .itemLeft(ItemStack(Material.NAME_TAG))
                .plugin(SandboxCore.instance)
                .open(player)
        })
    }

    override fun update(player: Player, contents: InventoryContents) {}
}