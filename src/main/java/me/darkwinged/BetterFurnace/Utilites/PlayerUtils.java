package me.darkwinged.BetterFurnace.Utilites;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerUtils {

    public static UUID getPlayerUUID(OfflinePlayer player) {
        return player.getUniqueId();
    }

    public static String getPlayerDirection(Player player) {
        String direction = "W";
        double rotation = ((player.getLocation().getYaw() - 90.0F) % 360.0F);
        if (rotation < 0.0D)
            rotation += 360.0D;
        if (0.0D <= rotation && rotation < 22.5D)
            return "W";
        if (22.5D <= rotation && rotation < 67.5D)
            return "NW";
        if (67.5D <= rotation && rotation < 112.5D)
            return "N";
        if (112.5D <= rotation && rotation < 157.5D)
            return "NE";
        if (157.5D <= rotation && rotation < 202.5D)
            return "E";
        if (202.5D <= rotation && rotation < 247.5D)
            return "SE";
        if (247.5D <= rotation && rotation < 292.5D)
            return "S";
        if (292.5D <= rotation && rotation < 337.5D)
            return "SW";
        if (337.5D <= rotation && rotation < 360.0D)
            return "W";
        return direction;
    }

    public static boolean hasSpace(Player player, ItemStack targetItem) {
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null)
                if (item.hasItemMeta() && item.getItemMeta().hasDisplayName() && targetItem
                        .hasItemMeta() && targetItem.getItemMeta().hasDisplayName()) {
                    if (item.getItemMeta().getDisplayName().equals(targetItem.getItemMeta().getDisplayName()) &&
                            item.getType() == targetItem.getType() &&
                            item.getAmount() != item.getMaxStackSize()) {
                        item.setAmount(item.getAmount() + targetItem.getAmount());
                        return true;
                    }
                } else if (item.getType() == targetItem.getType() &&
                        item.getAmount() != item.getMaxStackSize()) {
                    item.setAmount(item.getAmount() + targetItem.getAmount());
                    return true;
                }
        }
        if (player.getInventory().firstEmpty() != -1) {
            player.getInventory().addItem(new ItemStack[] { targetItem });
            return true;
        }
        return false;
    }

    public static List<Integer> getEmptySlots(Player player) {
        List<Integer> emptySlots = new ArrayList<>();
        for (int i = 0; i < player.getInventory().getSize(); i++) {
            if (player.getInventory().getItem(i) == null)
                emptySlots.add(Integer.valueOf(i));
        }
        return emptySlots;
    }

    public static int getExpToLevelUp(int level) {
        return (level <= 15) ? (2 * level + 7) : ((level <= 30) ? (5 * level - 38) : (9 * level - 158));
    }

    public static int getExpAtLevel(int level) {
        return (level <= 16) ? (int)(Math.pow(level, 2.0D) + (6 * level)) : ((level <= 31) ? (int)(2.5D * Math.pow(level, 2.0D) - 40.5D * level + 360.0D) : (int)(4.5D * Math.pow(level, 2.0D) - 162.5D * level + 2220.0D));
    }

    public static int getPlayerExp(Player player) {
        int exp = 0;
        int level = player.getLevel();
        exp += getExpAtLevel(level);
        exp += Math.round(getExpToLevelUp(level) * player.getExp());
        return exp;
    }

    public static void removePlayerExp(Player player, int exp) {
        int currentExp = getPlayerExp(player);
        player.setExp(0.0F);
        player.setLevel(0);
        int newExp = currentExp - exp;
        player.giveExp(newExp);
    }

}
