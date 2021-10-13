package xyz.fragbots.sandboxcore

import org.bukkit.plugin.java.JavaPlugin

class SandboxCore : JavaPlugin() {

    override fun onEnable() {
        loadClasses()
    }

    private fun loadClasses() {
        logger.info("Loaded Sandbox Core variables")
        instance = this
    }

    companion object {
        lateinit var instance: SandboxCore;
    }

}