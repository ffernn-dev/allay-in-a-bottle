package me.ffernn.allayinabottle

import de.tr7zw.changeme.nbtapi.NBTItem
import net.kyori.adventure.key.Key
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import java.util.Vector
import kotlin.random.Random


class Bottler : Listener {
    private fun giveAllayBottle(entity: Entity, player: Player, mainHand: Boolean) {
        val usedItem = if (mainHand) player.inventory.itemInMainHand else player.inventory.itemInOffHand

        val isAlreadyAllayBottle = if(usedItem.hasItemMeta() && usedItem.itemMeta.hasCustomModelData()) usedItem.itemMeta.customModelData == 6250 else false

        if (usedItem.type == Material.GLASS_BOTTLE && !isAlreadyAllayBottle){
            var allayBottle = ItemStack(Material.GLASS_BOTTLE, 1)
            val nbti = NBTItem(allayBottle)
            val display = nbti.addCompound("display")
            display.setString("Name", "{\"text\":\"Bottle of Allay\",\"italic\":false}")
            nbti.setInteger("CustomModelData", 6250)
            allayBottle = nbti.item

            allayBottle.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1)
            val updatedMeta = allayBottle.itemMeta
            updatedMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS)
            allayBottle.itemMeta = updatedMeta


            entity.remove()

            usedItem.amount = usedItem.amount - 1
            val unGiveable = player.inventory.addItem(allayBottle)
            for ((p, value) in unGiveable) {
                player.world.dropItem(player.location, value).velocity = player.location.direction.clone().multiply(0.5)
            }

            player.playSound(net.kyori.adventure.sound.Sound.sound(Key.key("entity.slime.squish_small"), net.kyori.adventure.sound.Sound.Source.NEUTRAL, 1f, Random.nextFloat()))
        }
    }
    @EventHandler
    fun onRightClickEntity(event: PlayerInteractEntityEvent) {
        val eventEntity = event.rightClicked
        val eventPlayer = event.player
        if (eventEntity.type == EntityType.ALLAY) {
            if (event.hand == EquipmentSlot.HAND) {
                giveAllayBottle(event.rightClicked, eventPlayer, true)
            }
            else if (event.hand == EquipmentSlot.OFF_HAND) {
                giveAllayBottle(event.rightClicked, eventPlayer, false)
            }
        }
    }

    @EventHandler
    fun onBlockClick(event: PlayerInteractEvent) {
        if (event.action == Action.RIGHT_CLICK_BLOCK) {
            val eventPlayer = event.player
            val heldItem = NBTItem(eventPlayer.inventory.itemInMainHand)
            if (eventPlayer.inventory.itemInMainHand.type == Material.GLASS_BOTTLE && heldItem.getInteger("CustomModelData") == 6250){
                eventPlayer.sendMessage("let out allay")
            }
        }
    }
}