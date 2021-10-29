package xyz.fragbots.sandboxcore.items

import org.bukkit.Bukkit
import xyz.fragbots.sandboxcore.SandboxCore
import xyz.fragbots.sandboxcore.items.weapons.Aote
import xyz.fragbots.sandboxcore.items.weapons.Giant_Sword
import xyz.fragbots.sandboxcore.items.weapons.Hyperion
import xyz.fragbots.sandboxcore.items.weapons.Juju

/*
 * Registers and manages all the skyblock item instances
 * https://github.com/KingRainbow44/SkyblockSandbox/blob/main/src/main/java/tk/skyblocksandbox/skyblocksandbox/item/SkyblockItemFactory.java
 */
class SkyblockItemFactory {
    val registeredItems = HashMap<String,SkyblockItem>()

    init {
        //Weapons

        registerItem(Hyperion())
        registerItem(Aote())
        registerItem(Juju())
        registerItem(Giant_Sword())
    }

    fun registerItem(skyblockItem: SkyblockItem) {
        registeredItems[skyblockItem.id] = skyblockItem
        Bukkit.getPluginManager().registerEvents(skyblockItem, SandboxCore.instance)
    }

    fun getItem(id:String) : SkyblockItem? {
        return registeredItems[id]
    }
}
