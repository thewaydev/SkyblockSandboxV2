package xyz.fragbots.sandboxcore.items.reforges.weapons

import xyz.fragbots.sandboxcore.items.SkyblockConsts
import xyz.fragbots.sandboxcore.items.reforges.SkyblockReforge
import xyz.fragbots.sandboxcore.items.reforges.SkyblockReforgeStats

class OddReforge : SkyblockReforge(
    name = "Odd",
    type = SkyblockConsts.SWORD,
    commonStats = SkyblockReforgeStats(critChance = 12, critDamage = 10, intelligence = -5),
    uncommonStats = SkyblockReforgeStats(critChance = 15, critDamage = 15, intelligence = -10),
    rareStats = SkyblockReforgeStats(critChance = 15, critDamage = 15, intelligence = -18),
    epicStats = SkyblockReforgeStats(critChance = 20, critDamage = 22, intelligence = -32),
    legendaryStats = SkyblockReforgeStats(critChance = 25, critDamage = 30, intelligence = -50),
    mythicStats = SkyblockReforgeStats( critChance = 30, critDamage = 40, intelligence = -75),
) {
}