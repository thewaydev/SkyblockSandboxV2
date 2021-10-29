package xyz.fragbots.sandboxcore.items.reforges.weapons

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import xyz.fragbots.sandboxcore.items.reforges.SkyblockReforge
import xyz.fragbots.sandboxcore.items.reforges.SkyblockReforgeStats
import xyz.fragbots.sandboxcore.items.reforges.SkyblockReforgeType
import xyz.fragbots.sandboxcore.utils.damage.SkyblockMeleeDamageEvent

class SharpReforge : SkyblockReforge(name = "Sharp", SkyblockReforgeType.WEAPON,
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
}