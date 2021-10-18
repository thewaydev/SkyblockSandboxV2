package xyz.fragbots.sandboxcore

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.craftbukkit.v1_8_R3.CraftServer
import org.bukkit.plugin.java.JavaPlugin
import xyz.fragbots.sandboxcore.commands.ItemCommand
import xyz.fragbots.sandboxcore.commands.SpawnEntityCommand
import xyz.fragbots.sandboxcore.entitites.SkyblockEntityManager
import xyz.fragbots.sandboxcore.items.SkyblockItemFactory
import xyz.fragbots.sandboxcore.listeners.DamageListener
import xyz.fragbots.sandboxcore.utils.damage.DamageExecutor

/*
    * Main plugin for the sandbox core, starts all the other processes
    * Shout out to KingRainbow44's Repository helped nme a lot with the recode: https://github.com/KingRainbow44/SkyblockSandbox/
 */
class SandboxCore : JavaPlugin() {
    lateinit var moshi:Moshi
    lateinit var itemFactory:SkyblockItemFactory
    lateinit var entityManager:SkyblockEntityManager
    lateinit var damageExecutor: DamageExecutor
    override fun onEnable() {
        loadClasses()
        registerCommands()
        registerListeners()
    }

    private fun loadClasses() {
        instance = this
        moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
        itemFactory = SkyblockItemFactory()
        entityManager = SkyblockEntityManager()
        damageExecutor = DamageExecutor()
        logger.info("Loaded Sandbox Core variables")
    }

    private fun registerCommands() {
        registerCommand(ItemCommand())
        registerCommand(SpawnEntityCommand())

        logger.info("Loaded Sandbox Core Commands")
    }

    private fun registerListeners() {
        Bukkit.getPluginManager().registerEvents(DamageListener(),this)

        logger.info("Registered Listeners")
    }

    private fun registerCommand(command: Command) {
        (server as CraftServer).commandMap.register(command.name,command)
    }

    companion object {
        //Allow any object to access this instance
        lateinit var instance: SandboxCore;
    }

}