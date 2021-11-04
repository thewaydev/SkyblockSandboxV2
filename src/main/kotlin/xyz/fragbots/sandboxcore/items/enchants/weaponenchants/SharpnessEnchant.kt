package xyz.fragbots.sandboxcore.items.enchants.weaponenchants

import xyz.fragbots.sandboxcore.SandboxCore
import xyz.fragbots.sandboxcore.items.SkyblockConsts
import xyz.fragbots.sandboxcore.items.enchants.Enchant
import xyz.fragbots.sandboxcore.utils.damage.SkyblockMeleeDamageEvent

class SharpnessEnchant : Enchant() {
    override val name = "Sharpness"
    override val id = "SHARPNESS"
    override val maxLevel = 100
    override val worksOn = listOf(SkyblockConsts.SWORD)

    override fun onMeleeDamageWithEnch(event: SkyblockMeleeDamageEvent, level: Int) {
        event.damageMultiplier+=(level*0.05)
    }
}