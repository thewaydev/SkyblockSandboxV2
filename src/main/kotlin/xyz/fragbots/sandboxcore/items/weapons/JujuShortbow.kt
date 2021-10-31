package xyz.fragbots.sandboxcore.items.weapons

import org.bukkit.Material
import org.bukkit.entity.Arrow
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import xyz.fragbots.sandboxcore.items.*
import xyz.fragbots.sandboxcore.utils.LoreGenerator
import xyz.fragbots.sandboxcore.utils.Utils
import xyz.fragbots.sandboxcore.utils.player.PlayerStats


class JujuShortbow : SkyblockItem(Material.BOW,"Juju Shortbow",SkyblockItemIDS.JUJUSHORTBOW){

    override fun getLore(playerStats: PlayerStats, itemStack: ItemStack?): Collection<String> {
        return LoreGenerator(
            Utils.format("&5Shortbow: Instantly shoots!"),
            Utils.format("&7Hits &c3 &7mobs on impact."),
            Utils.format("&7Can damage endermen."))
            .generic(getItemData(playerStats, false, itemStack), playerStats, this)
    }

    override fun getDefaultData(playerStats: PlayerStats):SkyblockItemData {
        val data = SkyblockItemData(id)

        data.rarity = SkyblockConsts.EPIC
        data.itemType = SkyblockConsts.BOW
        data.reforgeable = true
        data.dungeonitem = false

        data.baseDamage = 310
        data.baseStrength = 40
        data.baseCritChance = 10
        data.baseCritDamage = 110


        return data
    }

    override fun abilityUse(event: PlayerInteractEvent) {
        val player = event.player
        val action = event.action
        if(action != Action.LEFT_CLICK_AIR && action!=Action.RIGHT_CLICK_AIR) {
            return
        }


        val arrow = player.launchProjectile(Arrow::class.java,player.location.direction)
        arrow.velocity = arrow.velocity.multiply(3)
        //TODO add damage

    }

}
