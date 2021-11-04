package xyz.fragbots.sandboxcore.items.enchants

import org.bukkit.Bukkit
import xyz.fragbots.sandboxcore.SandboxCore
import xyz.fragbots.sandboxcore.items.SkyblockItem
import xyz.fragbots.sandboxcore.items.enchants.weaponenchants.SharpnessEnchant

class EnchantManager {
    val registeredEnchants = HashMap<String, Enchant>()

    init {
        registerEnchant(SharpnessEnchant())
    }

    fun registerEnchant(enchant: Enchant) {
        registeredEnchants[enchant.id] = enchant
        Bukkit.getPluginManager().registerEvents(enchant, SandboxCore.instance)
    }

    fun getEnchantsForItemType(type: Int): List<Enchant>{
        return registeredEnchants.values.filter { it.worksOn.contains(type) }
    }

    fun getEnchants(): List<Enchant> {
        return registeredEnchants.values.toList()
    }

    fun getEnchant(id:String) : Enchant? {
        return registeredEnchants[id]
    }
}