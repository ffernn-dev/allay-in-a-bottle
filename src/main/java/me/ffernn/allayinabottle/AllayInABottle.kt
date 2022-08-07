package me.ffernn.allayinabottle

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Logger

public var logging: Logger? = null

class AllayInABottle : JavaPlugin() {
    companion object {
        var instance: AllayInABottle? = null
        private set
    }

    override fun onEnable() {
        // Plugin startup logic
        logging = logger
        val bottlerObject = Bottler()
        Bukkit.getPluginManager().registerEvents(bottlerObject, this)
        logger.info("Loaded!")
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}