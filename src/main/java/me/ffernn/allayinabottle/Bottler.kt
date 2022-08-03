package me.ffernn.allayinabottle

import net.kyori.adventure.key.Key
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.inventory.EquipmentSlot
import kotlin.random.Random

class Bottler : Listener {
    //Listener
    @EventHandler
    fun onRightClickEntity(event: PlayerInteractEntityEvent) {
        if (event.hand == EquipmentSlot.HAND) {
            var heldItemMeta = event.player.inventory.itemInMainHand.itemMeta
            heldItemMeta.setDisplayName("Bottle of Allay")
            heldItemMeta.setCustomModelData(42069)
            event.player.inventory.itemInMainHand.setItemMeta(heldItemMeta)
            event.player.playSound(net.kyori.adventure.sound.Sound.sound(Key.key("entity.slime.squish_small"), net.kyori.adventure.sound.Sound.Source.NEUTRAL, 1f, Random.nextFloat()))
        }
        else if (event.hand == EquipmentSlot.OFF_HAND) {
            Bukkit.getLogger().info(event.player.inventory.itemInOffHand.toString())
        }
    }
}