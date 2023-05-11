package me.darkwinged.BetterFurnace;

import me.darkwinged.BetterFurnace.Listeners.BlockEvents;
import me.darkwinged.BetterFurnace.Listeners.CustomCrafting;
import me.darkwinged.BetterFurnace.Listeners.MenuListener;
import me.darkwinged.BetterFurnace.Listeners.SmeltEvent;
import me.darkwinged.BetterFurnace.Utilites.Utils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import me.darkwinged.BetterFurnace.Utilites.Crafting.Recipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class Main extends JavaPlugin {

    public static Main getInstance;

    public void onEnable() {
        getInstance = this;

        loadEvents();

        getServer().getConsoleSender().sendMessage(Utils.color("&7[&aBetterFurnace&7] &aPlugin has been enabled!"));
    }

    public void onDisable() {
        getServer().getConsoleSender().sendMessage(Utils.color("&7[&aBetterFurnace&7] &cPlugin has been disabled!"));
    }

    private void loadEvents() {
        Arrays.asList(
                new BlockEvents(),
                new CustomCrafting(),
                new MenuListener(),
                new SmeltEvent()
        ).forEach(listener -> getServer().getPluginManager().registerEvents(listener, this));
    }

    public List<Recipe> customRecipes = Arrays.asList(
            new Recipe(Items.ReinforcedPlate(), new String[] { " P ", " I ", " A " }, Map.of( 'A', new ItemStack(Material.ANVIL), 'P', new ItemStack(Material.IRON_PICKAXE), 'I', new ItemStack(Material.IRON_BLOCK))),
            new Recipe(Items.calculationCircuit(), new String[] { "RDR", "DCD", "RDR" }, Map.of( 'R', new ItemStack(Material.REDSTONE_BLOCK), 'D', new ItemStack(Material.DIAMOND), 'C', new ItemStack(Material.CLOCK))),
            new Recipe(Items.calculationProcessor(), new String[] { "RPR", "GCG", "RPR" }, Map.of( 'R', new ItemStack(Material.REDSTONE_BLOCK), 'P', Items.ReinforcedPlate(), 'G', new ItemStack(Material.GOLD_INGOT), 'C', Items.calculationCircuit())),
            new Recipe(Items.upgradeSpeed(), new String[] { "P P", "CSC", "P P" }, Map.of( 'P', Items.ReinforcedPlate(), 'C', Items.calculationProcessor(), 'S', new ItemStack(Material.PRISMARINE_SHARD))),
            new Recipe(Items.upgradeEfficiency(), new String[] { " P ", "CSC", " P " }, Map.of( 'P', Items.calculationCircuit(), 'C', Items.calculationProcessor(), 'S', new ItemStack(Material.PRISMARINE_SHARD))),
            new Recipe(Items.upgradeAutoOutput(), new String[] { " C ", "PSP", " H " }, Map.of( 'P', Items.ReinforcedPlate(), 'C', Items.calculationProcessor(), 'S', new ItemStack(Material.PRISMARINE_SHARD), 'H', new ItemStack(Material.HOPPER))),
            new Recipe(Items.upgradeDualSmelting(), new String[] { "RCR", "PSP", "RCR" }, Map.of( 'P', Items.ReinforcedPlate(), 'C', Items.calculationProcessor(), 'S', new ItemStack(Material.PRISMARINE_SHARD), 'R', new ItemStack(Material.REDSTONE)))
    );

}
