package xyz.fragbots.sandboxcore.items.reforges.weapons

import xyz.fragbots.sandboxcore.items.SkyblockConsts
import xyz.fragbots.sandboxcore.items.reforges.SkyblockReforge
import xyz.fragbots.sandboxcore.items.reforges.SkyblockReforgeStats

class LegendaryReforge : SkyblockReforge(
    name = "Legendary",
    type = SkyblockConsts.SWORD,
    commonStats = SkyblockReforgeStats(strength = 3, critChance = 5, critDamage = 5, attackSpeed = 2, intelligence = 5),
    uncommonStats = SkyblockReforgeStats(strength = 7, critChance = 7, critDamage = 10, attackSpeed = 3, intelligence = 8),
    rareStats = SkyblockReforgeStats(strength = 12, critChance = 9, critDamage = 15, attackSpeed = 5, intelligence = 12),
    epicStats = SkyblockReforgeStats(strength = 18, critChance = 12, critDamage = 22, attackSpeed = 7, intelligence = 18),
    legendaryStats = SkyblockReforgeStats(strength = 25, critChance = 15, critDamage = 28, attackSpeed = 10, intelligence = 25),
    mythicStats = SkyblockReforgeStats(strength = 32, critChance = 18, critDamage = 36, attackSpeed = 15, intelligence = 35),
) {
}