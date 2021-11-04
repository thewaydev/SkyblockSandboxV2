package xyz.fragbots.sandboxcore.items.reforges.weapons

import xyz.fragbots.sandboxcore.items.SkyblockConsts
import xyz.fragbots.sandboxcore.items.reforges.SkyblockReforge
import xyz.fragbots.sandboxcore.items.reforges.SkyblockReforgeStats

class FairReforge : SkyblockReforge(
    name = "Fair",
    type = SkyblockConsts.SWORD,
    commonStats = SkyblockReforgeStats(strength = 2, critChance = 2, critDamage = 2, attackSpeed = 2, intelligence = 2),
    uncommonStats = SkyblockReforgeStats(strength = 3, critChance = 3, critDamage = 3, attackSpeed = 3, intelligence = 3),
    rareStats = SkyblockReforgeStats(strength = 4, critChance = 4, critDamage = 4, attackSpeed = 4, intelligence = 4),
    epicStats = SkyblockReforgeStats(strength = 7, critChance = 7, critDamage = 7, attackSpeed = 7, intelligence = 7),
    legendaryStats = SkyblockReforgeStats(strength = 10, critChance = 10, critDamage = 10, attackSpeed = 10, intelligence = 10),
    mythicStats = SkyblockReforgeStats(strength = 12, critChance = 12, critDamage = 12, attackSpeed = 12, intelligence = 12),
) {
}