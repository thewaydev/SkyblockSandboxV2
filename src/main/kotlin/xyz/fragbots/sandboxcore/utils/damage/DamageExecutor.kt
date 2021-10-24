package xyz.fragbots.sandboxcore.utils.damage

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player
import xyz.fragbots.sandboxcore.entitites.SkyblockEntity
import xyz.fragbots.sandboxcore.items.SkyblockItemAbility
import xyz.fragbots.sandboxcore.utils.Utils
import xyz.fragbots.sandboxcore.utils.player.PlayerExtensions.getStats
import xyz.fragbots.sandboxcore.utils.player.PlayerStats
import kotlin.random.Random

class DamageExecutor {
    // https://hypixel-skyblock.fandom.com/wiki/Damage_Calculation
    fun executePVEMelee(damager: Player, damagee: SkyblockEntity):Long{
        val mob = damagee.entity ?: return 0
        val playerStats: PlayerStats = damager.getStats()
        val initialDamage:Double = (5.0+playerStats.damage.toDouble())*(1.0+(playerStats.strength.toDouble()/100.0))
        val damageMultiplier = 1.0/*+(Combat Level)+Enchants*/
        val armorBonus = 1.0
        val isCrit = (Random.nextInt(0,100)>=playerStats.critChance)
        val event = SkyblockMeleeDamageEvent(
            damager,
            damagee,
            isCrit,
            initialDamage,
            damageMultiplier,
            armorBonus
        )
        Bukkit.getPluginManager().callEvent(event)
        if(!event.isCancelled){
            var damage = event.inititalDamage*event.damageMultiplier*event.armorMultiplier
            if(event.isCrit){
                damage*=(1+(playerStats.critDamage/100))
            }
            val finalDamage = damage.toLong()
            createDmgHolo(mob.location,finalDamage,event.isCrit)
            damagee.damage(finalDamage)
            return finalDamage
        }
        return 0
    }

    //https://hypixel-skyblock.fandom.com/wiki/Ability_Damage
    fun executePVEMagic(damager: Player, damagee: SkyblockEntity, ability: SkyblockItemAbility): Long{
        val mob = damagee.entity ?: return 0
        val playerStats: PlayerStats = damager.getStats()
        val baseAbilityDamage = playerStats.abilityDamage.toDouble() + ability.damage.toDouble()
        val multiplier = (1.0+(playerStats.intel.toDouble()/100.0)*ability.scaling)
        val enchantMultiplier = 1.0
        val event = SkyblockMagicDamageEvent(damager,damagee,baseAbilityDamage,multiplier, enchantMultiplier)
        Bukkit.getPluginManager().callEvent(event)
        if(!event.isCancelled){
            val damage = (event.baseAbilityDamage*event.multipler*event.enchantMultiplier).toLong()
            createDmgHolo(mob.location,damage,false)
            damagee.damage(damage)
            return damage
        }
        return 0
    }

    fun createDmgHolo(loc: Location, damage: Long, crit:Boolean) {
        val damageString: String;
        if (crit) {
            val string = "✧${damage}✧"
            val sb = StringBuilder()
            string.forEachIndexed { index, c ->
                sb.append(when(index%6){
                    0,1->"§f"
                    2->"§e"
                    3->"§6"
                    4,5->"§c"
                    else -> ""
                }).append(c)
            }
            damageString = sb.toString()
        } else {
            damageString = "§7${damage}"
        }
        Utils.createHologramAndDelete(loc.add(0.0-Random.nextDouble(-1.0,1.0), 1.0-Random.nextDouble(-1.0,1.0), 0.0-Random.nextDouble(-1.0,1.0)), damageString, 20)

    }
}