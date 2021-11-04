package xyz.fragbots.sandboxcore.items.reforges.weapons

import xyz.fragbots.sandboxcore.items.SkyblockConsts
import xyz.fragbots.sandboxcore.items.reforges.SkyblockReforge
import xyz.fragbots.sandboxcore.items.reforges.SkyblockReforgeStats

class SpicyReforge : SkyblockReforge(
    name = "Spicy",
    type = SkyblockConsts.SWORD,
    commonStats = SkyblockReforgeStats( attackSpeed = 1),
    uncommonStats = SkyblockReforgeStats(attackSpeed = 2),
    rareStats = SkyblockReforgeStats(attackSpeed = 4),
    epicStats = SkyblockReforgeStats(attackSpeed = 7),
    legendaryStats = SkyblockReforgeStats(attackSpeed = 10),
    mythicStats = SkyblockReforgeStats(attackSpeed = 15),
) {
}
