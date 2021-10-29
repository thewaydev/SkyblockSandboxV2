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


class Giant_Sword : SkyblockItem(Material.IRON_SWORD,"Giant's Sword",SkyblockItemIDS.GIANT_SWORD){
    override var ability1:SkyblockItemAbility? = SkyblockItemAbility(
        "Giant's Slam","Giant's Slam", "&6Item Ability: Giant's Slam &e&lRIGHT CLICK \n&7Slam Your Sword into the ground \n&7dealing &c100,000 &7damage to\n&7nearby enemies.",
        100,
        30, 10000,9.6
    )

    override fun getLore(playerStats: PlayerStats, itemStack: ItemStack?): Collection<String> {
        return LoreGenerator().generic(getItemData(playerStats, true, itemStack), playerStats, this)
    }


    override fun getDefaultData(playerStats: PlayerStats):SkyblockItemData {
        val data = SkyblockItemData(id)

        data.rarity = SkyblockConsts.LEGENDARY
        data.itemType = SkyblockConsts.SWORD
        data.reforgeable = true
        data.dungeonitem = true

        data.baseDamage = 500

        return data
    }

    override fun abilityUse(event: PlayerInteractEvent) {
        val player = event.player
        val action = event.action
        if(action != Action.RIGHT_CLICK_BLOCK && action!=Action.RIGHT_CLICK_AIR) {
            return
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
        fun Player.location(world: org.bukkit.World, x: Double, y: Double, z: Double): Location? {
            return player.getLocation()
        }

        val giant = player.world.spawnEntity(player.location(player.world,player.location.x,player.location.y,player.location.z), EntityType.GIANT) as LivingEntity
        giant.addPotionEffect(PotionEffect(PotionEffectType.INVISIBILITY,1000,1))
        var giantPos = giant.location.add(0.0,1.0,0.0)
        giantPos = Location(giantPos.world,giantPos.x,giantPos.y,giantPos.z,giant.location.yaw,giant.location.pitch)
        giant.teleport(giantPos)
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
        object : BukkitRunnable() {
            override fun run() {
                giant.remove()
            }
        }.runTaskLater(SandboxCore.instance,50)


    }
}