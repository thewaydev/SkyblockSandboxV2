package xyz.fragbots.sandboxcore.entitites.mobs

import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Zombie
import xyz.fragbots.sandboxcore.entitites.SkyblockEntity

class SkyblockZombie : SkyblockEntity("Zombie") {
    override val entityType = EntityType.ZOMBIE

    override val health:Long = 100
    override val damage:Long = 20
    override val speed = 100

    override val level = 1

    override fun load(entity: LivingEntity) {
        val zombieEntity = entity as Zombie
        if(zombieEntity.isBaby){
            zombieEntity.isBaby = false;
        }
        if(zombieEntity.isVillager){
            zombieEntity.isVillager = false;
        }
    }
}