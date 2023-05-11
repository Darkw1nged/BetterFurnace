package me.darkwinged.BetterFurnace.Listeners;

import me.darkwinged.BetterFurnace.Items;
import me.darkwinged.BetterFurnace.Main;
import me.darkwinged.BetterFurnace.Utilites.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class BlockEvents implements Listener {

    private final Main plugin = Main.getInstance;

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();
        if (!block.getType().equals(Material.FURNACE)) return;

        Player player = event.getPlayer();
        UUID uuid = UUID.randomUUID();

        plugin.getConfig().set("Furnaces." + player.getUniqueId() + "." + uuid + ".Location.World", block.getLocation().getWorld().getName());
        plugin.getConfig().set("Furnaces." + player.getUniqueId() + "." + uuid + ".Location.X", block.getLocation().getX());
        plugin.getConfig().set("Furnaces." + player.getUniqueId() + "." + uuid + ".Location.Y", block.getLocation().getY());
        plugin.getConfig().set("Furnaces." + player.getUniqueId() + "." + uuid + ".Location.Z", block.getLocation().getZ());

        plugin.getConfig().set("Furnaces." + player.getUniqueId() + "." + uuid + ".Speed", 0);
        plugin.getConfig().set("Furnaces." + player.getUniqueId() + "." + uuid + ".Efficiency", 0);
        plugin.getConfig().set("Furnaces." + player.getUniqueId() + "." + uuid + ".AutoOutput", 0);
        plugin.getConfig().set("Furnaces." + player.getUniqueId() + "." + uuid + ".DoubleSmelt", 0);
        plugin.saveConfig();
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        if (!block.getType().equals(Material.FURNACE)) return;

        Player player = event.getPlayer();
        if (plugin.getConfig().getConfigurationSection("Furnaces." + player.getUniqueId()) == null) return;
        for (String key : plugin.getConfig().getConfigurationSection("Furnaces." + player.getUniqueId()).getKeys(false)) {
            if (plugin.getConfig().getString("Furnaces." + player.getUniqueId() + "." + key + ".Location.World") == null) continue;

            String world = plugin.getConfig().getString("Furnaces." + player.getUniqueId() + "." + key + ".Location.World");
            double x = plugin.getConfig().getDouble("Furnaces." + player.getUniqueId() + "." + key + ".Location.X");
            double y = plugin.getConfig().getDouble("Furnaces." + player.getUniqueId() + "." + key + ".Location.Y");
            double z = plugin.getConfig().getDouble("Furnaces." + player.getUniqueId() + "." + key + ".Location.Z");

            if (world == null || Bukkit.getWorld(world) == null) continue;
            Location location = new Location(Bukkit.getWorld(world), x, y, z);
            if (location.equals(block.getLocation())) {
                int speed = plugin.getConfig().getInt("Furnaces." + player.getUniqueId() + "." + key + ".Speed");
                int efficiency = plugin.getConfig().getInt("Furnaces." + player.getUniqueId() + "." + key + ".Efficiency");
                int autoOutput = plugin.getConfig().getInt("Furnaces." + player.getUniqueId() + "." + key + ".AutoOutput");
                int doubleSmelt = plugin.getConfig().getInt("Furnaces." + player.getUniqueId() + "." + key + ".DoubleSmelt");

                ItemStack item = Items.upgradeSpeed();
                item.setAmount(speed);
                block.getWorld().dropItemNaturally(block.getLocation(), item);

                item = Items.upgradeEfficiency();
                item.setAmount(efficiency);
                block.getWorld().dropItemNaturally(block.getLocation(), item);

                item = Items.upgradeAutoOutput();
                item.setAmount(autoOutput);
                block.getWorld().dropItemNaturally(block.getLocation(), item);

                item = Items.upgradeDualSmelting();
                item.setAmount(doubleSmelt);
                block.getWorld().dropItemNaturally(block.getLocation(), item);

                plugin.getConfig().set("Furnaces." + player.getUniqueId() + "." + key, null);
                plugin.saveConfig();
            }
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getClickedBlock() == null || event.getHand() == null || !event.getHand().equals(EquipmentSlot.HAND)) return;

        Block block = event.getClickedBlock();
        if (!block.getType().equals(Material.FURNACE)) return;

        if (plugin.getConfig().getConfigurationSection("Furnaces." + player.getUniqueId()) == null) return;
        for (String value : plugin.getConfig().getConfigurationSection("Furnaces." + player.getUniqueId()).getKeys(false)) {
            if (plugin.getConfig().getString("Furnaces." + player.getUniqueId() + "." + value + ".Location.World") == null) continue;

            String world = plugin.getConfig().getString("Furnaces." + player.getUniqueId() + "." + value + ".Location.World");
            double x = plugin.getConfig().getDouble("Furnaces." + player.getUniqueId() + "." + value + ".Location.X");
            double y = plugin.getConfig().getDouble("Furnaces." + player.getUniqueId() + "." + value + ".Location.Y");
            double z = plugin.getConfig().getDouble("Furnaces." + player.getUniqueId() + "." + value + ".Location.Z");

            if (world == null || Bukkit.getWorld(world) == null) continue;
            Location location = new Location(Bukkit.getWorld(world), x, y, z);
            if (!location.equals(block.getLocation())) continue;

            int speed = plugin.getConfig().getInt("Furnaces." + player.getUniqueId() + "." + value + ".Speed");
            int efficiency = plugin.getConfig().getInt("Furnaces." + player.getUniqueId() + "." + value + ".Efficiency");
            int autoOutput = plugin.getConfig().getInt("Furnaces." + player.getUniqueId() + "." + value + ".AutoOutput");
            int doubleSmelt = plugin.getConfig().getInt("Furnaces." + player.getUniqueId() + "." + value + ".DoubleSmelt");

            if (player.isSneaking() && player.getItemInHand().getType().equals(Material.STICK)) {
                player.sendMessage(Utils.color("&7[&aBetterFurnace&7] &aFurnace Upgrades:"));
                player.sendMessage(Utils.color("&7- &aSpeed: &7" + speed));
                player.sendMessage(Utils.color("&7- &aEfficiency: &7" + efficiency));
                player.sendMessage(Utils.color("&7- &aAuto Output: &7" + autoOutput));
                player.sendMessage(Utils.color("&7- &aDouble Smelt: &7" + doubleSmelt));
                return;
            }

            if (player.isSneaking() && player.getItemInHand().getType().equals(Material.PRISMARINE_SHARD)) {
                ItemMeta meta = player.getItemInHand().getItemMeta();
                if (meta == null || !meta.getPersistentDataContainer().has(NamespacedKey.minecraft("bf_upgrade"))) return;

                String upgrade = meta.getPersistentDataContainer().get(NamespacedKey.minecraft("bf_upgrade"), PersistentDataType.STRING);
                if (upgrade == null) return;

                switch (upgrade) {
                    case "speed": {
                        if (speed == 5) {
                            player.sendMessage(Utils.color("&7[&aBetterFurnace&7] &cYour furnace is already at max speed!"));
                            return;
                        }

                        plugin.getConfig().set("Furnaces." + player.getUniqueId() + "." + value + ".Speed", speed + 1);
                        plugin.saveConfig();
                        player.sendMessage(Utils.color("&7[&aBetterFurnace&7] &aYou have upgraded your furnace's speed!"));
                        break;
                    }
                    case "efficiency": {
                        if (efficiency == 4) {
                            player.sendMessage(Utils.color("&7[&aBetterFurnace&7] &cYour furnace is already at max efficiency!"));
                            return;
                        }

                        plugin.getConfig().set("Furnaces." + player.getUniqueId() + "." + value + ".Efficiency", efficiency + 1);
                        plugin.saveConfig();
                        player.sendMessage(Utils.color("&7[&aBetterFurnace&7] &aYou have upgraded your furnace's efficiency!"));
                        break;
                    }
                    case "autoOutput": {
                        if (autoOutput == 1) {
                            player.sendMessage(Utils.color("&7[&aBetterFurnace&7] &cYour furnace is already at max auto output!"));
                            return;
                        }

                        plugin.getConfig().set("Furnaces." + player.getUniqueId() + "." + value + ".AutoOutput", autoOutput + 1);
                        plugin.saveConfig();
                        player.sendMessage(Utils.color("&7[&aBetterFurnace&7] &aYou have upgraded your furnace's auto output!"));
                        break;
                    }
                    case "doubleSmelt": {
                        if (doubleSmelt == 2) {
                            player.sendMessage(Utils.color("&7[&aBetterFurnace&7] &cYour furnace is already at max double smelt!"));
                            return;
                        }

                        plugin.getConfig().set("Furnaces." + player.getUniqueId() + "." + value + ".DoubleSmelt", doubleSmelt + 1);
                        plugin.saveConfig();
                        player.sendMessage(Utils.color("&7[&aBetterFurnace&7] &aYou have upgraded your furnace's double smelt!"));
                        break;
                    }
                }
                player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
            }
        }


    }




}
