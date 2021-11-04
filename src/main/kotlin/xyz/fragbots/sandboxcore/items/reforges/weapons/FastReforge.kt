package xyz.fragbots.sandboxcore.items.reforges.weapons

import xyz.fragbots.sandboxcore.items.SkyblockConsts
import xyz.fragbots.sandboxcore.items.reforges.SkyblockReforge
import xyz.fragbots.sandboxcore.items.reforges.SkyblockReforgeStats

class FastReforge : SkyblockReforge(
    name = "Fast",
    type = SkyblockConsts.SWORD,
    commonStats = SkyblockReforgeStats(attackSpeed = 10),
    uncommonStats = SkyblockReforgeStats(attackSpeed = 20),
    rareStats = SkyblockReforgeStats(attackSpeed = 30),
    epicStats = SkyblockReforgeStats(attackSpeed = 40),
    legendaryStats = SkyblockReforgeStats(attackSpeed = 50),
    mythicStats = SkyblockReforgeStats(attackSpeed = 60),
) {
}
