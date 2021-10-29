package xyz.fragbots.sandboxcore.items.reforges

abstract class SkyblockReforge(
    val name: String,
    val type: SkyblockReforgeType,
    val commonStats: SkyblockReforgeStats? = null,
    val uncommonStats: SkyblockReforgeStats? = null,
    val rareStats: SkyblockReforgeStats? = null,
    val epicStats: SkyblockReforgeStats? = null,
    val legendaryStats: SkyblockReforgeStats? = null,
    val mythicStats: SkyblockReforgeStats? = null,
    val divineStats: SkyblockReforgeStats? = null,
) {
    open var ability: String? = null;

}