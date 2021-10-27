package xyz.fragbots.sandboxcore.utils.damage

import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import xyz.fragbots.sandboxcore.entitites.SkyblockEntity

class SkyblockMagicDamageEvent(
    private val player: Player,
    private val entity: SkyblockEntity,
    var baseAbilityDamage: Double,
    var multipler: Double,
    var enchantMultiplier: Double
) :
    Event() {
    var isCancelled = false

    override fun getHandlers(): HandlerList {
        return handlerList
    }

    companion object {
        val handlerList = HandlerList()
    }
}