package xyz.fragbots.sandboxcore.utils.damage

import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import xyz.fragbots.sandboxcore.entitites.SkyblockEntity

class SkyblockMagicDamageEvent(
    val player: Player,
    val entity: SkyblockEntity,
    var baseAbilityDamage: Double,
    var multipler: Double,
    var enchantMultiplier: Double
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