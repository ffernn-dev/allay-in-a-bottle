package me.ffernn.allayinabottle

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class AllayInABottle : JavaPlugin() {
    companion object {
        var instance: AllayInABottle? = null
        private set
    }

    override fun onEnable() {
        // Plugin startup logic
        val bottlerObject = Bottler()
        Bukkit.getPluginManager().registerEvents(bottlerObject, this)
        logger.info("Loaded!")
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}