package xyz.fragbots.sandboxcore.items

import xyz.fragbots.sandboxcore.SandboxCore
import xyz.fragbots.sandboxcore.items.reforges.SkyblockReforge
import xyz.fragbots.sandboxcore.items.reforges.SkyblockReforgeStats


/*
    * Main class for storing item data
    * Serialized with mochi
    * Inspired by: https://github.com/KingRainbow44/SkyblockSandbox/blob/main/src/main/java/tk/skyblocksandbox/skyblocksandbox/item/SkyblockItemData.java
    * All values are allowed to be null because null values arent serialized so all values that arent set arent serialized which should save a lot of space
 */
data class SkyblockItemData(
                            /*Item Oriented Stats*/

                            val id:String,
                            var rarity:Int?=null,
                            var itemType:Int?=null,
                            var reforgeable:Boolean?=null,
                            var dungeonitem:Boolean?=null,

                            /* Normal Stats */

                            var baseDamage:Long?=null,
                            var baseStrength:Long?=null,
                            var baseTrueDefense:Long?=null,
                            var baseHealth:Long?=null,
                            var baseIntel:Long?=null,
                            var baseDefense:Long?=null,
                            var baseCritChance:Long?=null,
                            var baseCritDamage:Long?=null,
                            var baseAttackSpeed:Long?=null,
                            var baseSpeed:Long?=null,
                            var baseSeaCreatureChance:Long?=null,
                            var baseMagicFind:Long?=null,
                            var basePetLuck:Long?=null,
                            var baseAbilityDamage:Long?=null,
                            var baseFerocity:Long?=null,

                            var reforgeName: String? = null,
                            var reforgeStats: SkyblockReforgeStats? = null,
){
    //Getters from all the values to ensure null safety
    fun getRarity():Int {
        return rarity ?: SkyblockConsts.COMMON
    }
    fun getItemType():Int {
        return itemType ?:SkyblockConsts.SWORD
    }
    fun canReforge(): Boolean {
        return reforgeable ?: true
    }
    fun isDungeonItem(): Boolean {
        return dungeonitem ?: false
    }
    fun getBaseDamage():Long {
        return baseDamage ?: 0
    }
    fun getBaseStrength():Long {
        return baseStrength ?: 0
    }
    fun getBaseTrueDefense():Long {
        return baseTrueDefense ?: 0
    }
    fun getBaseHealth():Long {
        return baseHealth ?: 0
    }
    fun getBaseIntel():Long {
        return baseIntel ?: 0
    }
    fun getBaseDefense():Long {
        return baseDefense ?: 0
    }
    fun getBaseCritChance():Long {
        return baseCritChance ?: 0
    }
    fun getBaseCritDamage():Long {
        return baseCritDamage ?: 0
    }
    fun getBaseAttackSpeed():Long {
        return baseAttackSpeed ?: 0
    }
    fun getBaseSpeed():Long {
        return baseSpeed ?: 0
    }
    fun getBaseSeaCreatureChance():Long {
        return baseSeaCreatureChance ?: 0
    }
    fun getBaseMagicFind(): Long {
        return baseMagicFind ?: 0
    }
    fun getBasePetLuck(): Long {
        return basePetLuck ?: 0
    }
    fun getBaseAbilityDamage():Long {
        return baseAbilityDamage ?: 0
    }
    fun getBaseFerocity():Long {
        return baseFerocity ?: 0
    }

    //Final Values (All reforges, hpbs, item things added)
    fun getFinalDamage():Long {
        return getBaseDamage()
    }
    fun getFinalStrength():Long {
        if(reforgeStats != null)
            return getBaseStrength() + reforgeStats!!.strength;
        return getBaseStrength();
    }
    fun getFinalTrueDefense():Long {
        if(reforgeStats != null)
            return getBaseTrueDefense() + reforgeStats!!.trueDefense;
        return getBaseTrueDefense()
    }
    fun getFinalHealth():Long {
        if(reforgeStats != null)
            return getBaseHealth() + reforgeStats!!.health;
        return getBaseHealth()
    }
    fun getFinalIntel():Long {
        if(reforgeStats != null)
            return getBaseIntel() + reforgeStats!!.intelligence;
        return getBaseIntel()
    }
    fun getFinalDefense():Long {
        if(reforgeStats != null)
            return getBaseDefense() + reforgeStats!!.defense;
        return getBaseDefense()
    }
    fun getFinalCritChance():Long {
        if(reforgeStats != null)
            return getBaseCritChance() + reforgeStats!!.critChance;
        return getBaseCritChance()
    }
    fun getFinalCritDamage():Long {
        if(reforgeStats != null)
            return getBaseCritDamage() + reforgeStats!!.critDamage;
        return getBaseCritDamage()
    }
    fun getFinalAttackSpeed():Long {
        if(reforgeStats != null)
            return getBaseAttackSpeed() + reforgeStats!!.attackSpeed;
        return getBaseAttackSpeed()
    }
    fun getFinalSpeed():Long {
        if(reforgeStats != null)
            return getBaseSpeed() + reforgeStats!!.speed;
        return getBaseSpeed()
    }
    fun getFinalSeaCreatureChance():Long {
        if(reforgeStats != null)
            return getBaseSeaCreatureChance() + reforgeStats!!.seaCreatureChance;
        return getBaseSeaCreatureChance()
    }
    fun getFinalMagicFind(): Long {
        if(reforgeStats != null)
            return getBaseMagicFind() + reforgeStats!!.magicFind;
        return getBaseMagicFind()
    }
    fun getFinalPetLuck(): Long {
        if(reforgeStats != null)
            return getBasePetLuck() + reforgeStats!!.petLuck;
        return getBasePetLuck()
    }
    fun getFinalAbilityDamage():Long {
        if(reforgeStats != null)
            return getBaseAbilityDamage() + reforgeStats!!.abilityDamage;
        return getBaseAbilityDamage()
    }
    fun getFinalFerocity():Long {
        if(reforgeStats != null)
            return getBaseFerocity() + reforgeStats!!.ferocity;
        return getBaseFerocity()
    }

    companion object {
        val adapter = SandboxCore.instance.moshi.adapter(SkyblockItemData::class.java)
    }
}