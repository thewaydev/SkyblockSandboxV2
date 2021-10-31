package xyz.fragbots.sandboxcore.items.reforges

abstract class SkyblockReforge(
    val name: String,
    val type: Int,
    val commonStats: SkyblockReforgeStats? = null,
    val uncommonStats: SkyblockReforgeStats? = null,
    val rareStats: SkyblockReforgeStats? = null,
    val epicStats: SkyblockReforgeStats? = null,
    val legendaryStats: SkyblockReforgeStats? = null,
    val mythicStats: SkyblockReforgeStats? = null,
    val divineStats: SkyblockReforgeStats? = null,
) {

    /**
     * Return the ability text; dynamic based on the rarity.
     * To get the rarity:
     * @see xyz.fragbots.sandboxcore.items.SkyblockConsts
     */
    open fun ability(rarity: Int): String {
        return ""
    };

}