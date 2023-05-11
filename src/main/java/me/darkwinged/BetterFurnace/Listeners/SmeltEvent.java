package me.darkwinged.BetterFurnace.Listeners;

import me.darkwinged.BetterFurnace.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.inventory.FurnaceStartSmeltEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SmeltEvent implements Listener {

    private final Main plugin = Main.getInstance;

    @EventHandler
    public void onSmelt(FurnaceStartSmeltEvent event) {
        Block block = event.getBlock();
        if (!block.getType().equals(Material.FURNACE)) return;

        for (String player : plugin.getConfig().getConfigurationSection("Furnaces").getKeys(false)) {
            for (String furnace : plugin.getConfig().getConfigurationSection("Furnaces." + player).getKeys(false)) {
                if (plugin.getConfig().getString("Furnaces." + player + "." + furnace + ".Location.World") == null) continue;

                String world = plugin.getConfig().getString("Furnaces." + player + "." + furnace + ".Location.World");
                double x = plugin.getConfig().getDouble("Furnaces." + player + "." + furnace + ".Location.X");
                double y = plugin.getConfig().getDouble("Furnaces." + player + "." + furnace + ".Location.Y");
                double z = plugin.getConfig().getDouble("Furnaces." + player + "." + furnace + ".Location.Z");

                if (world == null || Bukkit.getWorld(world) == null) continue;
                Location location = new Location(Bukkit.getWorld(world), x, y, z);
                if (!location.equals(block.getLocation())) continue;

                int speed = plugin.getConfig().getInt("Furnaces." + player + "." + furnace + ".Speed");
                event.setTotalCookTime((int) (event.getRecipe().getCookingTime() * (1 - (0.125 * speed))));
            }
        }
    }

    @EventHandler
    public void onSmelt(FurnaceBurnEvent event) {
        Block block = event.getBlock();
        if (!block.getType().equals(Material.FURNACE)) return;

        for (String player : plugin.getConfig().getConfigurationSection("Furnaces").getKeys(false)) {
            for (String furnace : plugin.getConfig().getConfigurationSection("Furnaces." + player).getKeys(false)) {
                if (plugin.getConfig().getString("Furnaces." + player + "." + furnace + ".Location.World") == null) continue;

                String world = plugin.getConfig().getString("Furnaces." + player + "." + furnace + ".Location.World");
                double x = plugin.getConfig().getDouble("Furnaces." + player + "." + furnace + ".Location.X");
                double y = plugin.getConfig().getDouble("Furnaces." + player + "." + furnace + ".Location.Y");
                double z = plugin.getConfig().getDouble("Furnaces." + player + "." + furnace + ".Location.Z");

                if (world == null || Bukkit.getWorld(world) == null) continue;
                Location location = new Location(Bukkit.getWorld(world), x, y, z);
                if (!location.equals(block.getLocation())) continue;

                int speed = plugin.getConfig().getInt("Furnaces." + player + "." + furnace + ".Speed");
                int efficiency = plugin.getConfig().getInt("Furnaces." + player + "." + furnace + ".Efficiency");

                event.setBurnTime((short) (event.getBurnTime() * (1 - (.15 * speed))));
                event.setBurnTime((short) (event.getBurnTime() * (1 + (1.25 * efficiency))));
            }
        }
    }

    @EventHandler
    public void onSmelt(FurnaceSmeltEvent event) {
        Block block = event.getBlock();
        if (!block.getType().equals(Material.FURNACE)) return;

        for (String player : plugin.getConfig().getConfigurationSection("Furnaces").getKeys(false)) {
            for (String furnace : plugin.getConfig().getConfigurationSection("Furnaces." + player).getKeys(false)) {
                if (plugin.getConfig().getString("Furnaces." + player + "." + furnace + ".Location.World") == null) continue;

                String world = plugin.getConfig().getString("Furnaces." + player + "." + furnace + ".Location.World");
                double x = plugin.getConfig().getDouble("Furnaces." + player + "." + furnace + ".Location.X");
                double y = plugin.getConfig().getDouble("Furnaces." + player + "." + furnace + ".Location.Y");
                double z = plugin.getConfig().getDouble("Furnaces." + player + "." + furnace + ".Location.Z");

                if (world == null || Bukkit.getWorld(world) == null) continue;
                Location location = new Location(Bukkit.getWorld(world), x, y, z);
                if (!location.equals(block.getLocation())) continue;

                int doubleSmelt = plugin.getConfig().getInt("Furnaces." + player + "." + furnace + ".DoubleSmelt");
                if (doubleSmelt == 1) {
                    if (Math.random() <= .1) {
                        event.setResult(new ItemStack(event.getRecipe().getResult().getType(), event.getRecipe().getResult().getAmount() + 1));
                    }
                } else if (doubleSmelt == 2) {
                    if (Math.random() <= .3) {
                        event.setResult(new ItemStack(event.getRecipe().getResult().getType(), event.getRecipe().getResult().getAmount() + 1));
                    }
                }

                int autoOutput = plugin.getConfig().getInt("Furnaces." + player + "." + furnace + ".AutoOutput");
                if (autoOutput == 1) {
                    Location right = new Location(block.getWorld(), block.getX() + 1, block.getY(), block.getZ());
                    Location bottom = new Location(block.getWorld(), block.getX(), block.getY() - 1, block.getZ());

                    if (right.getBlock().getType().equals(Material.CHEST) || right.getBlock().getType().equals(Material.TRAPPED_CHEST)) {
                        Chest chest = (Chest) right.getBlock().getState();
                        Inventory inventory = chest.getInventory();
                        if (inventory.firstEmpty() != -1) {
                            inventory.addItem(event.getResult());
                            event.setResult(null);
                        }
                    } else if (bottom.getBlock().getType().equals(Material.CHEST) || bottom.getBlock().getType().equals(Material.TRAPPED_CHEST)) {
                        Chest chest = (Chest) bottom.getBlock().getState();
                        Inventory inventory = chest.getInventory();
                        if (inventory.firstEmpty() != -1) {
                            inventory.addItem(event.getResult());
                            event.setResult(null);
                        }
                    }
                }
            }
        }
    }

}
