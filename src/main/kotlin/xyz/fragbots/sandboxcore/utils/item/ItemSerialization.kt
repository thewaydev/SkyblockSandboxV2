package de.thehellscode.core.util;

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.PlayerInventory
import org.bukkit.util.io.BukkitObjectInputStream
import org.bukkit.util.io.BukkitObjectOutputStream
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException

/*https://gist.github.com/graywolf336/8153678*/

object ItemSerialization {

    /**
     * Converts the player inventory to a String array of Base64 strings. First string is the content and second string is the armor.
     *
     * @param playerInventory to turn into an array of strings.
     * @return Array of strings: [ main content, armor content ]
     * @throws IllegalStateException
     */
    fun playerInventoryToBase64(playerInventory: PlayerInventory): String? {
        //get the main content part, this doesn't return the armor
        return toBase64(playerInventory)
    }

    fun playerArmorToBase64(playerInventory: PlayerInventory) : String? {
        return itemStackArrayToBase64(playerInventory.armorContents)
    }

    /**
     *
     * A method to serialize an [ItemStack] array to Base64 String.
     *
     *
     *
     *
     * Based off of [.toBase64].
     *
     * @param items to turn into a Base64 String.
     * @return Base64 string of the items.
     * @throws IllegalStateException
     */
    private fun itemStackArrayToBase64(items: Array<ItemStack?>): String? {
        return try {
            val outputStream = ByteArrayOutputStream()
            val dataOutput = BukkitObjectOutputStream(outputStream)

            // Write the size of the inventory
            dataOutput.writeInt(items.size)

            // Save every element in the list
            for (i in items.indices) {
                dataOutput.writeObject(items[i])
            }

            // Serialize that array
            dataOutput.close()
            Base64Coder.encodeLines(outputStream.toByteArray())
        } catch (e: Exception) {
            null
        }
    }

    /**
     * A method to serialize an inventory to Base64 string.
     *
     *
     *
     *
     * Special thanks to Comphenix in the Bukkit forums or also known
     * as aadnk on GitHub.
     *
     * [Original Source](https://gist.github.com/aadnk/8138186)
     *
     * @param inventory to serialize
     * @return Base64 string of the provided inventory
     * @throws IllegalStateException
     */
    private fun toBase64(inventory: Inventory): String? {
        return try {
            val outputStream = ByteArrayOutputStream()
            val dataOutput = BukkitObjectOutputStream(outputStream)

            // Write the size of the inventory
            dataOutput.writeInt(inventory.size)

            // Save every element in the list
            for (i in 0 until inventory.size) {
                dataOutput.writeObject(inventory.getItem(i))
            }

            // Serialize that array
            dataOutput.close()
            Base64Coder.encodeLines(outputStream.toByteArray())
        } catch (e: Exception) {
            null
        }
    }

    /**
     *
     * A method to get an [Inventory] from an encoded, Base64, string.
     *
     *
     *
     *
     * Special thanks to Comphenix in the Bukkit forums or also known
     * as aadnk on GitHub.
     *
     * [Original Source](https://gist.github.com/aadnk/8138186)
     *
     * @param data Base64 string of data containing an inventory.
     * @return Inventory created from the Base64 string.
     * @throws IOException
     */
    fun fromBase64(data: String?): Inventory? {
        return try {
            val inputStream = ByteArrayInputStream(Base64Coder.decodeLines(data))
            val dataInput = BukkitObjectInputStream(inputStream)
            val inventory = Bukkit.getServer().createInventory(null, dataInput.readInt())

            // Read the serialized inventory
            for (i in 0 until inventory.size) {
                inventory.setItem(i, dataInput.readObject() as ItemStack)
            }
            dataInput.close()
            inventory
        } catch (e: ClassNotFoundException) {
            null
        }
    }

    /**
     * Gets an array of ItemStacks from Base64 string.
     *
     *
     *
     *
     * Base off of [.fromBase64].
     *
     * @param data Base64 string to convert to ItemStack array.
     * @return ItemStack array created from the Base64 string.
     * @throws IOException
     */
    fun itemStackArrayFromBase64(data: String): ArrayList<ItemStack>? {
        return try {
            val inputStream = ByteArrayInputStream(Base64Coder.decodeLines(data))
            val dataInput = BukkitObjectInputStream(inputStream)
            val size = dataInput.readInt()
            val items = ArrayList<ItemStack>()

            for ( i in 0 until size) {
                try {
                    items.add(dataInput.readObject() as ItemStack)
                }catch (e:Exception){
                    items.add(ItemStack(Material.AIR))
                }
            }
            dataInput.close()
            items
        } catch (e: ClassNotFoundException) {
            null
        }
    }

}