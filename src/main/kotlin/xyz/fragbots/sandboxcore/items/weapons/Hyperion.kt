package xyz.fragbots.sandboxcore.items.weapons
import org.bukkit.Effect
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import xyz.fragbots.sandboxcore.SandboxCore
import xyz.fragbots.sandboxcore.items.*
import xyz.fragbots.sandboxcore.utils.LoreGenerator
import xyz.fragbots.sandboxcore.utils.Utils
import xyz.fragbots.sandboxcore.utils.Utils.raycast
import xyz.fragbots.sandboxcore.utils.player.PlayerExtensions.getNearbySkyblockEntities
import xyz.fragbots.sandboxcore.utils.player.PlayerExtensions.sendFormattedMessage
import xyz.fragbots.sandboxcore.utils.player.PlayerStats


class Hyperion : SkyblockItem(Material.IRON_SWORD,"Hyperion",SkyblockItemIDS.HYPERION){
    override var ability1:SkyblockItemAbility? = SkyblockItemAbility("Wither Impact",
        "&aScroll Abilities:\n" +
                "&6Item Ability: Wither Impact &e&lRIGHT CLICK",
        "&7Teleport &a10 Blocks &7ahead of\n" +
                "&7you. Then implode dealing\n" +
                "&c%%dmg%%&7 damage to nearby\n" +
                "&7enemies. Also applies the wither\n" +
                "&7shield scroll ability reducing\n" +
                "&7damage taken and granting an\n" +
                "&7absorption shield for &e5\n" +
                "&7seconds",
        300,0,10000,0.3)


    override fun getLore(playerStats: PlayerStats, itemStack: ItemStack?): Collection<String> {
        return LoreGenerator(
            Utils.format("&7Deals &a+50% &7damage to"),
            Utils.format("&7Withers. Grants &c+1 ❁ Damage"),
            Utils.format("&7and &a+2&b ✎ Intelligence"),
            Utils.format("&7per &cCatacombs &7level."),
            " ",
            Utils.format("&7Your Catacombs level: &c0"))
            .generic(getItemData(playerStats, true, itemStack), playerStats, this)
    }


    override fun getDefaultData(playerStats: PlayerStats):SkyblockItemData {
        val data = SkyblockItemData(id)

        data.rarity = SkyblockConsts.LEGENDARY
        data.itemType = SkyblockConsts.SWORD
        data.reforgeable = true
        data.dungeonitem = true

        data.baseDamage = 260
        data.baseStrength = 150
        data.baseIntel = 350
        data.baseFerocity = 30

        return data
    }

    override fun abilityUse(event: PlayerInteractEvent) {
        val player = event.player
        val action = event.action
        if(action != Action.RIGHT_CLICK_BLOCK && action!=Action.RIGHT_CLICK_AIR) {
            return
        }

        if(!canUseAbility(player,ability1!!)) return sendManaMessage(player)

        //Wither Impact Ability Code
        player.teleport(player.raycast(10))
        player.fallDistance = 0.0f
        player.playSound(player.getLocation(), Sound.EXPLODE, 1f, 1f)
        player.world.playEffect(player.location.add(0.0,1.0,0.0), Effect.EXPLOSION_LARGE, 1)

        var totalDamage:Long = 0
        val damagedEntities = player.getNearbySkyblockEntities(6.0,6.0,6.0)
        if(damagedEntities.size != 0 ) {
            for (mob in damagedEntities) {
                totalDamage+=SandboxCore.instance.damageExecutor.executePVEMagic(player,mob,ability1!!)
            }
            player.sendFormattedMessage("&7Your Implosion hit &c${damagedEntities.size} &7enemies for &c${Utils.formatNumber(totalDamage)} &7damage.")
        }

        abilityUsed(player,ability1!!)
        //TODO Wither Shield
    }
}

