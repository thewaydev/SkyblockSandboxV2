package xyz.fragbots.sandboxcore

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.bukkit.command.Command
import org.bukkit.craftbukkit.v1_8_R3.CraftServer
import org.bukkit.plugin.java.JavaPlugin
import xyz.fragbots.sandboxcore.commands.ItemCommand
import xyz.fragbots.sandboxcore.items.SkyblockItemFactory

/*
    * Main plugin for the sandbox core, starts all the other processes
    * Shout out to KingRainbow44's Repository helped nme a lot with the recode: https://github.com/KingRainbow44/SkyblockSandbox/
 */
class SandboxCore : JavaPlugin() {
    lateinit var moshi:Moshi
    lateinit var itemFactory:SkyblockItemFactory
    override fun onEnable() {
        loadClasses()
        registerCommands()
    }

    private fun loadClasses() {
        instance = this
        moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
        itemFactory = SkyblockItemFactory()
        logger.info("Loaded Sandbox Core variables")
    }

    private fun registerCommands() {
        registerCommand(ItemCommand())

        logger.info("Loaded Sandbox Core Commands")
    }

    private fun registerCommand(command: Command) {
        (server as CraftServer).commandMap.register(command.name,command)
    }

    companion object {
        //Allow any object to access this instance
        lateinit var instance: SandboxCore;
    }

}