package me.ffernn.allayinabottle

import net.kyori.adventure.key.Key
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import kotlin.random.Random


class Bottler : Listener {
    //TODO Allay data, nonstack
    @EventHandler
    fun onRightClickEntity(event: PlayerInteractEntityEvent) {
        val eventPlayer = event.player
        if (event.hand == EquipmentSlot.HAND) {
            if (eventPlayer.inventory.itemInMainHand.equals(Material.GLASS_BOTTLE)){
                val allayBottle = ItemStack(Material.GLASS_BOTTLE, 1)
                val allayBottleMeta = allayBottle.itemMeta
                allayBottleMeta.setDisplayName("Bottle of Allay")
                allayBottleMeta.setCustomModelData(6250)
                allayBottle.itemMeta = allayBottleMeta
                event.rightClicked.remove()
                eventPlayer.inventory.addItem(allayBottle)
                eventPlayer.playSound(net.kyori.adventure.sound.Sound.sound(Key.key("entity.slime.squish_small"), net.kyori.adventure.sound.Sound.Source.NEUTRAL, 1f, Random.nextFloat()))
            }
        }
        else if (event.hand == EquipmentSlot.OFF_HAND) {
            if (eventPlayer.inventory.itemInOffHand.equals(Material.GLASS_BOTTLE)) {
                val allayBottle = ItemStack(Material.GLASS_BOTTLE, 1)
                val allayBottleMeta = allayBottle.itemMeta
                allayBottleMeta.setDisplayName("Bottle of Allay")
                allayBottleMeta.setCustomModelData(6250)
                allayBottle.itemMeta = allayBottleMeta
                event.rightClicked.remove()
                eventPlayer.inventory.addItem(allayBottle)
                eventPlayer.playSound(net.kyori.adventure.sound.Sound.sound(Key.key("entity.slime.squish_small"), net.kyori.adventure.sound.Sound.Source.NEUTRAL, 1f, Random.nextFloat()))
            }
        }
    }

    @EventHandler
    fun onBlockClick(event: PlayerInteractEvent) {
        if (event.action == Action.RIGHT_CLICK_BLOCK) {
            val eventPlayer = event.player
            if (eventPlayer.inventory.itemInMainHand.equals(Material.GLASS_BOTTLE)){
                eventPlayer.sendMessage("let out allay")
            }
        }
    }
}