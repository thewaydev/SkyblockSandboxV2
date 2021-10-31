package xyz.fragbots.sandboxcore.items.reforges.weapons

import xyz.fragbots.sandboxcore.items.SkyblockConsts
import xyz.fragbots.sandboxcore.items.reforges.SkyblockReforge
import xyz.fragbots.sandboxcore.items.reforges.SkyblockReforgeStats

class SpicyReforge : SkyblockReforge(
    name = "Spicy",
    type = SkyblockConsts.SWORD,
    commonStats = SkyblockReforgeStats(strength = 2, critChance = 1, critDamage = 25, attackSpeed = 1),
    uncommonStats = SkyblockReforgeStats(strength = 3, critChance = 1, critDamage = 35, attackSpeed = 2),
    rareStats = SkyblockReforgeStats(strength = 4, critChance = 1, critDamage = 45, attackSpeed = 4),
    epicStats = SkyblockReforgeStats(strength = 7, critChance = 1, critDamage = 60, attackSpeed = 7),
    legendaryStats = SkyblockReforgeStats(strength = 10, critChance = 1, critDamage = 80, attackSpeed = 10),
    mythicStats = SkyblockReforgeStats(strength = 12, critChance = 1, critDamage = 100, attackSpeed = 15),
) {
}