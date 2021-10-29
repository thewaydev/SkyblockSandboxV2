package xyz.fragbots.sandboxcore.items.weapons

import net.minecraft.server.v1_8_R3.NBTTagCompound
import org.bukkit.*
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import xyz.fragbots.sandboxcore.SandboxCore
import xyz.fragbots.sandboxcore.items.*
import xyz.fragbots.sandboxcore.utils.LoreGenerator
import xyz.fragbots.sandboxcore.utils.Utils
import xyz.fragbots.sandboxcore.utils.player.PlayerExtensions.getNearbySkyblockEntities
import xyz.fragbots.sandboxcore.utils.player.PlayerExtensions.sendFormattedMessage
import xyz.fragbots.sandboxcore.utils.player.PlayerStats
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.scheduler.BukkitRunnable


class GiantSword : SkyblockItem(Material.IRON_SWORD,"Giant's Sword",SkyblockItemIDS.GIANTSWORD){
    override var ability1:SkyblockItemAbility? = SkyblockItemAbility(
        "Giant's Slam","&6Item Ability: Giant's Slam &e&lRIGHT CLICK ",
        "&7Slam Your Sword into the ground \n" +
                "&7dealing &c%%dmg%% &7damage to\n" +
                "&7nearby enemies.",
        100,
        30, 100000,0.05
    )

    override fun getLore(playerStats: PlayerStats, itemStack: ItemStack?): Collection<String> {
        return LoreGenerator().generic(getItemData(playerStats, true, itemStack), playerStats, this)
    }


    override fun getDefaultData(playerStats: PlayerStats):SkyblockItemData {
        val data = SkyblockItemData(id)

        data.rarity = SkyblockConsts.LEGENDARY
        data.itemType = SkyblockConsts.SWORD
        data.reforgeable = true
        data.dungeonitem = false

        data.baseDamage = 500

        return data
    }
    fun setInvulnerable(en: Entity) {
        val nmsEn = (en as CraftEntity).handle
        val compound = NBTTagCompound()
        nmsEn.c(compound)
        compound.setByte("Invulnerable", 1.toByte())
        nmsEn.f(compound)
    }
    fun freezeEntity(en: Entity) {
        val nmsEn = (en as CraftEntity).handle
        val compound = NBTTagCompound()
        nmsEn.c(compound)
        compound.setByte("NoAI", 1.toByte())
        nmsEn.f(compound)
    }

    override fun abilityUse(event: PlayerInteractEvent) {
        val player = event.player
        val action = event.action
        if(action != Action.RIGHT_CLICK_BLOCK && action!=Action.RIGHT_CLICK_AIR) {
            return
        }

        if(!canUseAbility(player,ability1!!)) return sendManaMessage(player)

        //Giant Sword Ability Code

        val giant = player.world.spawnEntity(Location(player.world,player.location.x, player.location.y+1, player.location.z, 0f, 0f), EntityType.GIANT) as LivingEntity
        giant.addPotionEffect(PotionEffect(PotionEffectType.INVISIBILITY,1000,1))
        giant.customName = "Dinnerbone"
        giant.equipment.itemInHand = ItemStack(Material.IRON_SWORD)
        setInvulnerable(giant)
        freezeEntity(giant)

        var totalDamage:Long = 0
        val damagedEntities = player.getNearbySkyblockEntities(6.0,6.0,6.0)
        player.playSound(player.getLocation(), Sound.ANVIL_LAND, 1f, 1f)
        if(damagedEntities.size != 0 ) {
            for (mob in damagedEntities) {
                totalDamage+=SandboxCore.instance.damageExecutor.executePVEMagic(player,mob,ability1!!)
            }
            player.sendFormattedMessage("&7Your Giant's Sword hit &c${damagedEntities.size} &7enemies for &c${Utils.formatNumber(totalDamage)} &7damage.")
        }

        abilityUsed(player,ability1!!)

        object : BukkitRunnable() {
            override fun run() {
                giant.remove()
            }
        }.runTaskLater(SandboxCore.instance,50)


    }
}