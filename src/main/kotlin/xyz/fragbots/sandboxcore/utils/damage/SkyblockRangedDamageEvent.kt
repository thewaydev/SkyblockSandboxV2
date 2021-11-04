package xyz.fragbots.sandboxcore.utils.damage

import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import xyz.fragbots.sandboxcore.entitites.SkyblockEntity

/*
* Event Called before all PVE damage (For damage calculation and cancelling)
* Coded in java for better hooking into bukkit
*/
class SkyblockRangedDamageEvent(
    val player: Player,
    val entity: SkyblockEntity,
    val canCrit: Boolean,
    var isCrit: Boolean,
    var inititalDamage: Double,
    var damageMultiplier: Double,
    var armorMultiplier: Double
) :
    Event() {
    var isCancelled = false

    override fun getHandlers(): HandlerList {
        return HANDLERS
    }

    companion object {
        private val HANDLERS = HandlerList()

        @JvmStatic
        fun getHandlerList(): HandlerList {
            return HANDLERS
        }
    }
}