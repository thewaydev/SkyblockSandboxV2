package xyz.fragbots.sandboxcore.items

import xyz.fragbots.sandboxcore.items.weapons.Hyperion

/*
 * Registers and manages all the skyblock item instances
 * https://github.com/KingRainbow44/SkyblockSandbox/blob/main/src/main/java/tk/skyblocksandbox/skyblocksandbox/item/SkyblockItemFactory.java
 */
class SkyblockItemFactory {
    val registeredItems = HashMap<String,SkyblockItem>()

    init {

        //Weapons

        registerItem(Hyperion())
    }

    fun registerItem(skyblockItem: SkyblockItem) {
        registeredItems[skyblockItem.id] = skyblockItem
    }

    fun getItem(id:String) : SkyblockItem? {
        return registeredItems[id]
    }
}