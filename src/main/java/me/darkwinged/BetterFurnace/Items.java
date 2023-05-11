package me.darkwinged.BetterFurnace;

import me.darkwinged.BetterFurnace.Utilites.Utils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;

public class Items {

    public static ItemStack upgradeSpeed() {
        ItemStack item = new ItemStack(Material.PRISMARINE_SHARD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utils.color("&aRapid Heat Upgrade"));
        meta.setLore(Utils.color(Arrays.asList(
                "&7Smelts things faster.",
                "",
                "&7Level: &a1",
                "",
                "&7SShift+Right Click to apply."
        )));
        meta.getPersistentDataContainer().set(NamespacedKey.minecraft("bf_upgrade"), PersistentDataType.STRING, "speed");
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack upgradeEfficiency() {
        ItemStack item = new ItemStack(Material.PRISMARINE_SHARD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utils.color("&aFuel Optimization Upgrade"));
        meta.setLore(Utils.color(Arrays.asList(
                "&7Allow for more items to be ",
                "&7smelted with less fuel.",
                "",
                "&7Level: &a1",
                "",
                "&7SShift+Right Click to apply."
        )));
        meta.getPersistentDataContainer().set(NamespacedKey.minecraft("bf_upgrade"), PersistentDataType.STRING, "efficiency");
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack upgradeAutoOutput() {
        ItemStack item = new ItemStack(Material.PRISMARINE_SHARD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utils.color("&aAutomatic Ejection Upgrade"));
        meta.setLore(Utils.color(Arrays.asList(
                "&7Automatically input to a chest",
                "&7when finished smelting.",
                "",
                "&7Level: &a1",
                "",
                "&7SShift+Right Click to apply."
        )));
        meta.getPersistentDataContainer().set(NamespacedKey.minecraft("bf_upgrade"), PersistentDataType.STRING, "autoOutput");
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack upgradeDualSmelting() {
        ItemStack item = new ItemStack(Material.PRISMARINE_SHARD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utils.color("&aDual Smelting Upgrade"));
        meta.setLore(Utils.color(Arrays.asList(
                "&7Allows the furnace to smelt two",
                "&7items at once, effectively doubling",
                "",
                "&7Level: &a1",
                "",
                "&7SShift+Right Click to apply."
        )));
        meta.getPersistentDataContainer().set(NamespacedKey.minecraft("bf_upgrade"), PersistentDataType.STRING, "doubleSmelt");
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack ReinforcedPlate() {
        ItemStack item = new ItemStack(Material.IRON_INGOT);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utils.color("&aReinforced Plate"));
        meta.setLore(Utils.color(Arrays.asList(
                "&7Used to craft furnace upgrades."
        )));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack calculationProcessor() {
        ItemStack item = new ItemStack(Material.IRON_INGOT);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utils.color("&aCalculation Processor"));
        meta.setLore(Utils.color(Arrays.asList(
                "&7Used to craft furnace upgrades."
        )));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack calculationCircuit() {
        ItemStack item = new ItemStack(Material.IRON_INGOT);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utils.color("&aCalculation Circuit"));
        meta.setLore(Utils.color(Arrays.asList(
                "&7Used to craft furnace upgrades."
        )));
        item.setItemMeta(meta);
        return item;
    }

}
