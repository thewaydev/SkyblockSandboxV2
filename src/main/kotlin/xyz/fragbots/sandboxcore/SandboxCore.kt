package xyz.fragbots.sandboxcore

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import fr.minuskube.inv.InventoryManager
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.craftbukkit.v1_8_R3.CraftServer
import org.bukkit.plugin.java.JavaPlugin
import org.reflections.Reflections
import xyz.fragbots.sandboxcore.commands.EnchantCommand
import xyz.fragbots.sandboxcore.commands.GetNBTCommand
import xyz.fragbots.sandboxcore.commands.ItemCommand
import xyz.fragbots.sandboxcore.commands.ReforgeCommand
import xyz.fragbots.sandboxcore.commands.SpawnEntityCommand
import xyz.fragbots.sandboxcore.entitites.SkyblockEntityManager
import xyz.fragbots.sandboxcore.items.SkyblockItemFactory
import xyz.fragbots.sandboxcore.items.enchants.EnchantManager
import xyz.fragbots.sandboxcore.listeners.*
import xyz.fragbots.sandboxcore.listeners.ranks.ChatListener
import xyz.fragbots.sandboxcore.listeners.ranks.LogOutListener
import xyz.fragbots.sandboxcore.listeners.ranks.TabPrefixListener
import xyz.fragbots.sandboxcore.utils.damage.DamageExecutor
import xyz.fragbots.sandboxcore.utils.player.ActionBarManager
import xyz.fragbots.sandboxcore.utils.player.DynamicStatManager

/*
    * Main plugin for the sandbox core, starts all the other processes
    * Shout out to KingRainbow44's Repository helped nme a lot with the recode: https://github.com/KingRainbow44/SkyblockSandbox/
 */
class SandboxCore : JavaPlugin() {
    lateinit var moshi:Moshi
    lateinit var itemFactory:SkyblockItemFactory
    lateinit var entityManager:SkyblockEntityManager
    lateinit var damageExecutor: DamageExecutor
    lateinit var dynamicStatManager: DynamicStatManager
    lateinit var actionBarManager: ActionBarManager
    lateinit var inventoryManager: InventoryManager
    lateinit var enchantManager: EnchantManager

    override fun onEnable() {
        loadClasses()
        registerCommands()
        registerListeners()
    }
    override fun onDisable() {
        entityManager.killAll()
    }
    private fun loadClasses() {
        instance = this
        moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
        itemFactory = SkyblockItemFactory()
        entityManager = SkyblockEntityManager()
        damageExecutor = DamageExecutor()
        dynamicStatManager = DynamicStatManager()
        actionBarManager = ActionBarManager()
        inventoryManager = InventoryManager(this)
        inventoryManager.init()
        enchantManager = EnchantManager()

        logger.info("Loaded Sandbox Core variables")
    }

    private fun registerCommands() {
        val reflections = Reflections("xyz.fragbots.sandboxcore.commands");
        reflections.getSubTypesOf(Command::class.java).forEach {
            registerCommand(it.getConstructor().newInstance())
        }
        
        logger.info("Loaded Sandbox Core Commands")
    }

    private fun registerListeners() {
        Bukkit.getPluginManager().registerEvents(DamageListener(), this)
        Bukkit.getPluginManager().registerEvents(ItemListener(), this)
        Bukkit.getPluginManager().registerEvents(InventoryListener(), this)
        Bukkit.getPluginManager().registerEvents(EntityListener(), this)
        Bukkit.getPluginManager().registerEvents(TeleportListener(), this)
        Bukkit.getPluginManager().registerEvents(PlayerJoinListener(), this)
        Bukkit.getPluginManager().registerEvents(ChatListener(), this)
        Bukkit.getPluginManager().registerEvents(LogOutListener(), this)
        Bukkit.getPluginManager().registerEvents(TabPrefixListener(), this)
        Bukkit.getPluginManager().registerEvents(dynamicStatManager, this)

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
