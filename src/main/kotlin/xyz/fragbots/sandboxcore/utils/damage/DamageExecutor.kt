package xyz.fragbots.sandboxcore.utils.damage

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player
import xyz.fragbots.sandboxcore.entitites.SkyblockEntity
import xyz.fragbots.sandboxcore.utils.Utils
import xyz.fragbots.sandboxcore.utils.player.PlayerExtensions.getStats
import xyz.fragbots.sandboxcore.utils.player.PlayerStats
import kotlin.random.Random

class DamageExecutor {
    // https://hypixel-skyblock.fandom.com/wiki/Damage_Calculation
    fun executePVE(damager: Player, damagee: SkyblockEntity){
        val mob = damagee.entity ?: return
        val playerStats: PlayerStats = damager.getStats()
        val initialDamage:Double = (5.0+playerStats.damage.toDouble())*(1.0+(playerStats.strength.toDouble()/100.0))
        val damageMultiplier = 1.0/*+(Combat Level)+Enchants*/
        val armorBonus = 1.0
        val isCrit = false
        val event = SkyblockDamageEvent(damager,isCrit,initialDamage,damageMultiplier,armorBonus)
        Bukkit.getPluginManager().callEvent(event)
        if(!event.isCancelled){
            var damage = event.inititalDamage*event.damageMultiplier*event.armorMultiplier
            if(event.isCrit){
                damage*=(1+(playerStats.critDamage/100))
            }
            val finalDamage = damage.toLong()
            createDmgHolo(mob.location,finalDamage,isCrit)
            damagee.damage(finalDamage)
        }
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