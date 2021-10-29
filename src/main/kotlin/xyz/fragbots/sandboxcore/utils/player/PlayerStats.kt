package xyz.fragbots.sandboxcore.utils.player

import org.bukkit.entity.Player
import xyz.fragbots.sandboxcore.items.SkyblockItemData

data class PlayerStats(val player:Player,
                       var damage:Long=0,
                       var strength:Long=0,
                       var trueDefense:Long=0,
                       var health:Long=100,
                       var intel:Long = 100,
                       var defense:Long=1,
                       var critChance:Long=30,
                       var critDamage:Long=0,
                       var attackSpeed:Long=0,
                       var speed:Long=100,
                       var seaCreatureChance:Long=0,
                       var magicFind:Long=0,
                       var petLuck:Long=0,
                       var abilityDamage:Long=0,
                       var ferocity:Long=0,
) {
    //Adds a skyblock items stats to the player stats
    fun addItemStats(item:SkyblockItemData?){
        if(item==null){
            return
        }
        damage+=item.getFinalDamage()
        strength+=item.getFinalStrength()
        trueDefense+=item.getFinalTrueDefense()
        health+=item.getFinalHealth()
        intel+=item.getFinalIntel()
        defense+=item.getFinalDefense()
        critChance+=item.getFinalCritChance()
        critDamage+=item.getFinalCritDamage()
        attackSpeed+=item.getFinalAttackSpeed()
        speed+=item.getFinalSpeed()
        seaCreatureChance+=item.getFinalSeaCreatureChance()
        magicFind+=item.getFinalMagicFind()
        petLuck+=item.getFinalPetLuck()
        abilityDamage+=item.getFinalAbilityDamage()
        ferocity+=item.getFinalFerocity()
    }
}