package xyz.fragbots.sandboxcore.items.reforges.weapons

import xyz.fragbots.sandboxcore.items.SkyblockConsts
import xyz.fragbots.sandboxcore.items.reforges.SkyblockReforge
import xyz.fragbots.sandboxcore.items.reforges.SkyblockReforgeStats

class GentleReforge : SkyblockReforge(
    name = "Gentle",
    type = SkyblockConsts.SWORD,
    commonStats = SkyblockReforgeStats(strength = 3, attackSpeed = 8),
    uncommonStats = SkyblockReforgeStats(strength = 5, attackSpeed = 10),
    rareStats = SkyblockReforgeStats(strength = 7, attackSpeed = 15),
    epicStats = SkyblockReforgeStats(strength = 10, attackSpeed = 20),
    legendaryStats = SkyblockReforgeStats(strength = 15, attackSpeed = 25),
    mythicStats = SkyblockReforgeStats(strength = 20,  attackSpeed = 30),
) {
}
