package me.darkwinged.BetterFurnace.Utilites;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Hopper;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class WorldUtils {

    public static String getTime(World world) {
        long rawTime = world.getTime();
        long hours = rawTime / 1000L + 6L;
        long rawMinutes = rawTime % 1000L * 60L / 1000L;
        String clock = "am";
        if (hours >= 12L) {
            hours -= 12L;
            clock = "pm";
        }
        if (hours >= 12L) {
            hours -= 12L;
            clock = "am";
        }
        if (hours == 0L)
            hours = 12L;
        String minutes = "0" + rawMinutes;
        minutes = minutes.substring(minutes.length() - 2);
        return "" + hours + ":" + hours + minutes;
    }

    public static String getTime(World world, ChatColor coloredTime) {
        long rawTime = world.getTime();
        long hours = rawTime / 1000L + 6L;
        long rawMinutes = rawTime % 1000L * 60L / 1000L;
        String clock = "am";
        if (hours >= 12L) {
            hours -= 12L;
            clock = "pm";
        }
        if (hours >= 12L) {
            hours -= 12L;
            clock = "am";
        }
        if (hours == 0L)
            hours = 12L;
        String minutes = "0" + rawMinutes;
        minutes = minutes.substring(minutes.length() - 2);
        return "" + hours + ":" + hours + minutes + coloredTime;
    }


    public static boolean isFullyGrown(Block block) {
        return (((Ageable)block.getBlockData()).getAge() == ((Ageable)block.getBlockData()).getMaximumAge());
    }

    public static boolean isHopperEmpty(Hopper hopper) {
        for (ItemStack itemStack : hopper.getInventory().getContents()) {
            if (itemStack != null)
                return false;
        }
        return true;
    }

    public static List<Chunk> getLoadedChunks(World world) {
        return Arrays.asList(world.getLoadedChunks());
    }

    public static int getLoadedChunksCount(World world) {
        return (world.getLoadedChunks()).length;
    }

    public static List<Entity> getEntitiesInChunk(Chunk chunk, boolean excludePlayers) {
        List<Entity> entities = Arrays.asList(chunk.getEntities());
        if (excludePlayers)
            for (int i = 0; i < entities.size(); i++) {
                if (entities.get(i) instanceof org.bukkit.entity.LivingEntity) {
                    entities.remove(i);
                    i--;
                }
            }
        return entities;
    }

    public static List<Entity> getAnimalsInChunk(Chunk chunk) {
        List<Entity> entities = Arrays.asList(chunk.getEntities());
        for (int i = 0; i < entities.size(); i++) {
            if (!(entities.get(i) instanceof org.bukkit.entity.Animals)) {
                entities.remove(i);
                i--;
            }
        }
        return entities;
    }

    public static List<Entity> getMobsInChunk(Chunk chunk) {
        List<Entity> entities = Arrays.asList(chunk.getEntities());
        for (int i = 0; i < entities.size(); i++) {
            if (!(entities.get(i) instanceof org.bukkit.entity.Mob)) {
                entities.remove(i);
                i--;
            }
        }
        return entities;
    }

    private static String weather = "Sunny";

    public static String getWeather() {
        return weather;
    }

    public static void setWeather(String weather) {
        WorldUtils.weather = weather;
    }

    public static Map<UUID, Block> spawners = new HashMap<>();

}
