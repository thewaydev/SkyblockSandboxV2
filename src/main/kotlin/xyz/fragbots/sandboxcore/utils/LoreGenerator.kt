package xyz.fragbots.sandboxcore.utils

import xyz.fragbots.sandboxcore.items.SkyblockItemData


class LoreGenerator(vararg val extraLore: String) {
    fun generic(itemData: SkyblockItemData): Collection<String> {
        val finalLore = ArrayList<String>()
        var line = 0

        /*
         * Stage 1 lore Generation, Damage Things
         */
        var addBreak = false

        if(itemData.getFinalDamage() > 0){
            finalLore.add(line, Utils.format("&7Damage: &c+${itemData.getFinalDamage()}"))
            line++;addBreak=true
        }
        if(itemData.getFinalStrength() > 0){
            finalLore.add(line, Utils.format("&7Strength: &c+${itemData.getFinalStrength()}"))
            line++;addBreak=true
        }
        if(itemData.getFinalCritChance() > 0){
            finalLore.add(line, Utils.format("&7Crit Chance: &c+${itemData.getFinalCritChance()}%"))
            line++;addBreak=true
        }
        if(itemData.getFinalCritDamage() > 0){
            finalLore.add(line, Utils.format("&7Crit Damage: &c+${itemData.getFinalCritDamage()}%"))
            line++;addBreak=true
        }
        if(itemData.getFinalAttackSpeed() > 0){
            finalLore.add(line, Utils.format("&7Attack Speed: &c+${itemData.getFinalAttackSpeed()}%"))
            line++;addBreak=true
        }
        if(itemData.getFinalSeaCreatureChance() > 0){
            finalLore.add(line, Utils.format("&7Sea Creature Chance: &c+${itemData.getFinalSeaCreatureChance()}%"))
            line++;addBreak=true
        }
        if(itemData.getFinalAbilityDamage() > 0){
            finalLore.add(line, Utils.format("&7Ability Damage: &c+${itemData.getFinalAbilityDamage()}%"))
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
            finalLore.add(line, Utils.format("&7Health: &a+${itemData.getFinalHealth()}"))
            line++;addBreak2=true
        }
        if(itemData.getFinalDefense() > 0){
            finalLore.add(line, Utils.format("&7Defense: &a+${itemData.getFinalDefense()}"))
            line++;addBreak2=true
        }
        if(itemData.getFinalSpeed() > 0){
            finalLore.add(line, Utils.format("&7Speed: &a+${itemData.getFinalSeaCreatureChance()}"))
            line++;addBreak2=true
        }
        if(itemData.getFinalIntel() > 0){
            finalLore.add(line, Utils.format("&7Intelligence: &a+${itemData.getFinalIntel()}"))
            line++;addBreak2=true
        }
        if(itemData.getFinalTrueDefense() > 0){
            finalLore.add(line, Utils.format("&7True Defense: &a+${itemData.getFinalTrueDefense()}"))
            line++;addBreak2=true
        }
        if(itemData.getFinalMagicFind() > 0){
            finalLore.add(line, Utils.format("&7Magic Find &a+${itemData.getFinalMagicFind()}"))
            line++;addBreak2=true
        }
        if(itemData.getFinalPetLuck() > 0){
            finalLore.add(line, Utils.format("&7Pet Luck: &a+${itemData.getFinalPetLuck()}"))
            line++;addBreak2=true
        }
        if(itemData.getFinalFerocity() > 0){
            finalLore.add(line, Utils.format("&7Ferocity: &a+${itemData.getFinalFerocity()}"))
            line++;addBreak2=true
        }

        if(addBreak2){
            finalLore.add(line, " ")
            line++;
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

        var addBreak3 = false

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
}