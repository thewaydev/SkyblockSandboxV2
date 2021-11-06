package xyz.fragbots.sandboxcore.entitites

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.Item
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Zombie
import org.bukkit.inventory.ItemStack
import org.bukkit.metadata.FixedMetadataValue
import xyz.fragbots.sandboxcore.SandboxCore
import java.util.*

abstract class SkyblockEntity(val name:String){
    abstract val entityType: EntityType

    val id:Int? = null
    var entity:LivingEntity? = null
    var currentHealth:Long = 0
    //Entity Data Start

    /*
     * Statistics
     */

    open val health:Long = 100
    open val defense = 0.0
    open val damage:Long = 0
    open val speed = 100

    open val level = 1

    open val isRegularMob = true

    /*
     * Inventory data
     */

    open val inHand:ItemStack? = null

    open val helmet:ItemStack? = null
    open val chestplate:ItemStack ? = null
    open val leggings:ItemStack? = null
    open val boots:ItemStack? = null

    //Entity Data End

    /*
     * Spawns the entity instance and assigns a unique mob id
     */
    open fun spawn(loc:Location){
        val spawnedEntity = loc.world.spawnEntity(loc,entityType) as LivingEntity
        entity = spawnedEntity
        val id = SandboxCore.instance.entityManager.registerEntity(this)
        if(isRegularMob) {
            currentHealth = health
            spawnedEntity.customName = generateName()
            spawnedEntity.canPickupItems = false
            spawnedEntity.isCustomNameVisible = true

            //Load Items
            if(inHand != null) spawnedEntity.equipment.itemInHand = inHand
            if(helmet != null) spawnedEntity.equipment.helmet = helmet
            if(chestplate != null) spawnedEntity.equipment.chestplate = chestplate
            if(leggings != null) spawnedEntity.equipment.leggings = leggings
            if(boots!= null) spawnedEntity.equipment.boots = boots

            //Allow any customs thing to be done to the entity
            load(spawnedEntity)
        }

        spawnedEntity.setMetadata("sbEntityId",FixedMetadataValue(SandboxCore.instance,id))

    }

    open fun load(entity:LivingEntity) {}
    open fun damage(damage: Long) {
        currentHealth = 0.toLong().coerceAtLeast(currentHealth - damage)
        entity?.customName = generateName()
        if(currentHealth==0.toLong()){
            kill()
        }
    }

    open fun kill() {
        val ent = entity ?: return
        SandboxCore.instance.entityManager.unRegisterEntity(ent.entityId)
        ent.health=0.0
    }

    open fun generateName() : String {
        return "§8[§7Lv$level§8] §c$name ${if(currentHealth<=health/2) "§e" else "§a"}${if(currentHealth<=0) 0 else currentHealth}§f/§a$health§c❤"
    }
}