package xyz.fragbots.sandboxcore.items.reforges.weapons

import xyz.fragbots.sandboxcore.items.SkyblockConsts
import xyz.fragbots.sandboxcore.items.reforges.SkyblockReforge
import xyz.fragbots.sandboxcore.items.reforges.SkyblockReforgeStats

class EpicReforge : SkyblockReforge(
    name = "Epic",
    type = SkyblockConsts.SWORD,
    commonStats = SkyblockReforgeStats(strength = 15, critDamage = 10, attackSpeed = 1),
    uncommonStats = SkyblockReforgeStats(strength = 20, critDamage = 15, attackSpeed = 2),
    rareStats = SkyblockReforgeStats(strength = 25, critDamage = 20, attackSpeed = 4),
    epicStats = SkyblockReforgeStats(strength = 32, critDamage = 27, attackSpeed = 7),
    legendaryStats = SkyblockReforgeStats(strength = 40, critDamage = 35, attackSpeed = 10),
    mythicStats = SkyblockReforgeStats(strength = 50,  critDamage = 45, attackSpeed = 15),
) {
}
