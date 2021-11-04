package xyz.fragbots.sandboxcore.utils

import xyz.fragbots.sandboxcore.SandboxCore
import xyz.fragbots.sandboxcore.items.SkyblockItem
import xyz.fragbots.sandboxcore.items.SkyblockItemAbility
import xyz.fragbots.sandboxcore.items.SkyblockItemData
import xyz.fragbots.sandboxcore.items.enchants.Enchant
import xyz.fragbots.sandboxcore.utils.player.PlayerStats


class LoreGenerator(vararg val extraLore: String) {
    fun generic(itemData: SkyblockItemData,playerStats: PlayerStats, itemInstance: SkyblockItem): Collection<String> {
        val finalLore = ArrayList<String>()
        var line = 0

        /*
         * Stage 1 lore Generation, Damage Things
         */
        var addBreak = false

        if(itemData.getFinalDamage() > 0){
            finalLore.add(line, Utils.format("&7Damage: &c+${itemData.getFinalDamage()} "))
            line++;addBreak=true
        }
        if(itemData.getFinalStrength() > 0){
            val reforge = if (itemData.reforgeStats != null && itemData.reforgeStats!!.strength != 0L)
                "&9(+${itemData.reforgeStats!!.strength})" else ""
            finalLore.add(line, Utils.format("&7Strength: &c+${itemData.getFinalStrength()} $reforge"))
            line++;addBreak=true
        }
        if(itemData.getFinalCritChance() > 0){
            val reforge = if (itemData.reforgeStats != null && itemData.reforgeStats!!.critChance != 0L)
                "&9(+${itemData.reforgeStats!!.critChance}%)" else ""
            finalLore.add(line, Utils.format("&7Crit Chance: &c+${itemData.getFinalCritChance()}% $reforge"))
            line++;addBreak=true
        }
        if(itemData.getFinalCritDamage() > 0){
            val reforge = if (itemData.reforgeStats != null && itemData.reforgeStats!!.critDamage != 0L)
                "&9(+${itemData.reforgeStats!!.critDamage}%)" else ""
            finalLore.add(line, Utils.format("&7Crit Damage: &c+${itemData.getFinalCritDamage()}% $reforge"))
            line++;addBreak=true
        }
        if(itemData.getFinalAttackSpeed() > 0){
            val reforge = if (itemData.reforgeStats != null && itemData.reforgeStats!!.attackSpeed != 0L)
                "&9(+${itemData.reforgeStats!!.attackSpeed})" else ""
            finalLore.add(line, Utils.format("&7Attack Speed: &c+${itemData.getFinalAttackSpeed()}% $reforge"))
            line++;addBreak=true
        }
        if(itemData.getFinalSeaCreatureChance() > 0){
            val reforge = if (itemData.reforgeStats != null && itemData.reforgeStats!!.seaCreatureChance != 0L)
                "&9(+${itemData.reforgeStats!!.seaCreatureChance})" else ""
            finalLore.add(line, Utils.format("&7Sea Creature Chance: &c+${itemData.getFinalSeaCreatureChance()}% $reforge"))
            line++;addBreak=true
        }
        if(itemData.getFinalAbilityDamage() > 0){
            val reforge = if (itemData.reforgeStats != null && itemData.reforgeStats!!.abilityDamage != 0L)
                "&9(+${itemData.reforgeStats!!.abilityDamage})" else ""
            finalLore.add(line, Utils.format("&7Ability Damage: &c+${itemData.getFinalAbilityDamage()}% $reforge"))
            line++;addBreak=true
        }

        if(addBreak){
            finalLore.add(line, "")
            line++;
        }

        /*
         * Lore Generation: Non Damage Stats (EHP)
         */
        var addBreak2 = false

        if(itemData.getFinalHealth() > 0){
            val reforge = if (itemData.reforgeStats != null && itemData.reforgeStats!!.health != 0L)
                "&9(+${itemData.reforgeStats!!.health})" else ""
            finalLore.add(line, Utils.format("&7Health: &a+${itemData.getFinalHealth()} $reforge"))
            line++;addBreak2=true
        }
        if(itemData.getFinalDefense() > 0){
            val reforge = if (itemData.reforgeStats != null && itemData.reforgeStats!!.defense != 0L)
                "&9(+${itemData.reforgeStats!!.defense})" else ""
            finalLore.add(line, Utils.format("&7Defense: &a+${itemData.getFinalDefense()} $reforge"))
            line++;addBreak2=true
        }
        if(itemData.getFinalSpeed() > 0){
            val reforge = if (itemData.reforgeStats != null && itemData.reforgeStats!!.speed != 0L)
                "&9(+${itemData.reforgeStats!!.speed})" else ""
            finalLore.add(line, Utils.format("&7Speed: &a+${itemData.getFinalSeaCreatureChance()} $reforge"))
            line++;addBreak2=true
        }
        if(itemData.getFinalIntel() > 0){
            val reforge = if (itemData.reforgeStats != null && itemData.reforgeStats!!.intelligence != 0L)
                "&9(+${itemData.reforgeStats!!.intelligence})" else ""
            finalLore.add(line, Utils.format("&7Intelligence: &a+${itemData.getFinalIntel()} $reforge"))
            line++;addBreak2=true
        }
        if(itemData.getFinalTrueDefense() > 0){
            val reforge = if (itemData.reforgeStats != null && itemData.reforgeStats!!.trueDefense != 0L)
                "&9(+${itemData.reforgeStats!!.trueDefense})" else ""
            finalLore.add(line, Utils.format("&7True Defense: &a+${itemData.getFinalTrueDefense()} $reforge"))
            line++;addBreak2=true
        }
        if(itemData.getFinalMagicFind() > 0){
            val reforge = if (itemData.reforgeStats != null && itemData.reforgeStats!!.magicFind != 0L)
                "&9(+${itemData.reforgeStats!!.magicFind})" else ""
            finalLore.add(line, Utils.format("&7Magic Find &a+${itemData.getFinalMagicFind()} $reforge"))
            line++;addBreak2=true
        }
        if(itemData.getFinalPetLuck() > 0){
            val reforge = if (itemData.reforgeStats != null && itemData.reforgeStats!!.petLuck != 0L)
                "&9(+${itemData.reforgeStats!!.petLuck})" else ""
            finalLore.add(line, Utils.format("&7Pet Luck: &a+${itemData.getFinalPetLuck()} $reforge"))
            line++;addBreak2=true
        }
        if(itemData.getFinalFerocity() > 0){
            val reforge = if (itemData.reforgeStats != null && itemData.reforgeStats!!.ferocity != 0L)
                "&9(+${itemData.reforgeStats!!.ferocity})" else ""
            finalLore.add(line, Utils.format("&7Ferocity: &a+${itemData.getFinalFerocity()} $reforge"))
            line++;addBreak2=true
        }

        if(addBreak2){
            finalLore.add(line, " ")
            line++;
        }

        /**
         * Lore Generation: Enchants
         */

        val enchants = itemData.getEnchants()
        if(enchants.isNotEmpty()) {
            var enchString = ""
            enchants.keys.forEachIndexed() { index, enchantId ->
                val enchant = SandboxCore.instance.enchantManager.getEnchant(enchantId) ?: return@forEachIndexed
                val level = enchants[enchantId] ?: return@forEachIndexed
                if(index%2==0){
                    enchString+=generateEnchantString(enchant,level)
                }else{
                    enchString+=", "+generateEnchantString(enchant, level)+"\n"
                }
            }
            val enchLore = enchString.split("\n")
            finalLore.addAll(line,enchLore); line+=enchLore.size
            finalLore.add(line, " "); line++
        }


        /*
         * Lore Generation: Add extra item lore, if provided
         */
        if(extraLore.isNotEmpty()){
            finalLore.addAll(line, extraLore.toList())
            line+=extraLore.size
            finalLore.add(line, " ")
            line++;
        }

        /*
         * Lore Generation: Abilities
         */

        var addBreak4 = false

        val ability1 = itemInstance.ability1
        val ability2 = itemInstance.ability2
        val ability3 = itemInstance.ability3

        if(ability1!=null){
            addBreak4 = true
            if(finalLore[line-1].isNotBlank()){
                finalLore.add(line, " "); line++
            }
            val abilityLore = generateAbilityLore(ability1,playerStats)
            finalLore.addAll(line,abilityLore); line+=abilityLore.size
        }

        if(ability2!=null){
            addBreak4 = true
            if(finalLore[line-1].isNotBlank()){
                finalLore.add(line, " "); line++
            }
            val abilityLore = generateAbilityLore(ability2,playerStats)
            finalLore.addAll(line, abilityLore); line+=abilityLore.size
        }

        if(ability3!=null){
            addBreak4 = true
            if(finalLore[line-1].isNotBlank()){
                finalLore.add(line, " "); line++
            }
            val abilityLore = generateAbilityLore(ability3,playerStats)
            finalLore.addAll(line, abilityLore); line+=abilityLore.size
        }

        if(addBreak4) {
            finalLore.add(line, " ")
            line++;
        }

        /*
         * Lore Generation: Ending
         */
        if(itemData.canReforge()) {
            finalLore.add(line, Utils.format("&8This item can be reforged!"))
            line++;
        }
        var lastLine = ""
        lastLine += Utils.rarityToString(itemData.getRarity())
        if(itemData.isDungeonItem()){
            lastLine += " DUNGEON"
        }
        if(Utils.rarityToString(itemData.getItemType()).isNotBlank()){
            lastLine += " ${Utils.itemTypeToString(itemData.getItemType())}"
        }
        finalLore.add(line, lastLine)
        return finalLore
    }

    fun generateAbilityLore(ability:SkyblockItemAbility, playerStats: PlayerStats):Collection<String>{
        val lore = ArrayList<String>()
        lore.addAll(Utils.format(ability.displayName).split("\n"))
        lore.addAll(Utils.format(ability.getDescription(playerStats)).split("\n"))
        if(ability.manaCost>0){
            lore.add(Utils.format("&7Mana Cost: &3${ability.manaCost}"))
        }
        if(ability.cooldown>0){
            lore.add(Utils.format("&7Cooldown: &a${ability.cooldown}s"))
        }
        return lore
    }

    fun generateEnchantString(enchant: Enchant, level:Int): String {
        return Utils.format("&9${enchant.name} $level")
    }
}
