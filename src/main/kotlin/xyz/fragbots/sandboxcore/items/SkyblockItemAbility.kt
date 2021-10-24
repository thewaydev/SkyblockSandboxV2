package xyz.fragbots.sandboxcore.items

import xyz.fragbots.sandboxcore.utils.player.PlayerStats

class SkyblockItemAbility(val name:String,val displayName:String,private val description:String, val manaCost:Int=0, val cooldown:Int=0, val damage:Long=0,val scaling:Double=1.0) {
    open fun getDescription(playerStats: PlayerStats) : String {
        val baseAbilityDamage = playerStats.abilityDamage.toDouble()+damage.toDouble()
        val multiplier = (1.0+(playerStats.intel.toDouble()/100.0))*scaling
        return description.replace("%%dmg%%",(baseAbilityDamage*multiplier).toString())
    }
}