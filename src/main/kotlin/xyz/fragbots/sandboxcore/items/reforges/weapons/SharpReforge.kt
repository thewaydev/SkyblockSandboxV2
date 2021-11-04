package xyz.fragbots.sandboxcore.items.reforges.weapons

import xyz.fragbots.sandboxcore.items.SkyblockConsts
import xyz.fragbots.sandboxcore.items.reforges.SkyblockReforge
import xyz.fragbots.sandboxcore.items.reforges.SkyblockReforgeStats

class SharpReforge : SkyblockReforge(name = "Sharp", SkyblockConsts.SWORD,
    commonStats = SkyblockReforgeStats(critChance = 10, critDamage = 20),
    uncommonStats = SkyblockReforgeStats(critChance = 12, critDamage = 30),
    rareStats = SkyblockReforgeStats(critChance = 14, critDamage = 40),
    epicStats = SkyblockReforgeStats(critChance = 17, critDamage = 55),
    legendaryStats = SkyblockReforgeStats(critChance = 20, critDamage = 75),
    mythicStats = SkyblockReforgeStats(critChance = 25, critDamage = 90)
) /* , Listener */{

    /* example reforge handling ( not implemented yet )
    @EventHandler
    public fun onKill(event: SkyblockMeleeDamageEvent) {
        if(event.reforge.name == this.name) {
            // exec logic
        }
    }
    */

    /* example ability text ( not implemented yet )
    override fun ability(rarity: Int): String {
        var whateverStat = when(rarity) {
            SkyblockConsts.COMMON -> 2
            SkyblockConsts.UNCOMMON -> 3
            SkyblockConsts.RARE -> 4
            SkyblockConsts.EPIC -> 6
            SkyblockConsts.LEGENDARY -> 8
            SkyblockConsts.MYTHIC -> 10
            else -> 0
        }

        return "Get $whateverStat crit chance when u do a backflip"
    }
    */
}