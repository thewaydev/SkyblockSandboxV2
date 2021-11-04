package xyz.fragbots.sandboxcore.items.weapons

import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import xyz.fragbots.sandboxcore.items.*
import xyz.fragbots.sandboxcore.utils.LoreGenerator
import xyz.fragbots.sandboxcore.utils.Utils.raycast
import xyz.fragbots.sandboxcore.utils.player.PlayerStats


class Aote : SkyblockItem(Material.DIAMOND_SWORD,"Aspect Of The End",SkyblockItemIDS.AOTE){
    override var ability1:SkyblockItemAbility? = SkyblockItemAbility(
        "Instant Tranismission","&6Item Ability: Instant Tranismission &e&lRIGHT CLICK",
        "&7Teleport &a8 Blocks &7ahead of\n" +
                "&7you and gain &a+50 &f✦ Speed&7\n" +
                "&7for &a3 seconds&7.",
        50,
        0, 0, 0.0
    )

    override fun getLore(playerStats: PlayerStats, itemStack: ItemStack?): Collection<String> {
        return LoreGenerator() //It has no
                .generic(getItemData(playerStats, false, itemStack), playerStats, this)
    }

    override fun getDefaultData(playerStats: PlayerStats):SkyblockItemData {
        val data = SkyblockItemData(id)

        data.rarity = SkyblockConsts.RARE
        data.itemType = SkyblockConsts.SWORD
        data.reforgeable = true
        data.dungeonitem = false

        data.baseDamage = 100
        data.baseStrength = 100


        return data
    }

    override fun abilityUse(event: PlayerInteractEvent) {
        val player = event.player
        val action = event.action
        if(action != Action.RIGHT_CLICK_BLOCK && action!=Action.RIGHT_CLICK_AIR) {
            return
        }
        if(!canUseAbility(player,ability1!!)) return sendManaMessage(player)


        //Aote Ability Code

        player.teleport(player.raycast(8))
        player.fallDistance = 0.0f
        player.playSound(player.location, Sound.ENDERMAN_TELEPORT, 1f, 1f)

        abilityUsed(player,ability1!!)
    }

}

