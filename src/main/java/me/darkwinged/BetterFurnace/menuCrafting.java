package me.darkwinged.BetterFurnace;

import me.darkwinged.BetterFurnace.Utilites.Crafting.Menu;
import me.darkwinged.BetterFurnace.Utilites.Crafting.Recipe;
import me.darkwinged.BetterFurnace.Utilites.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class menuCrafting extends Menu {

    private final Main plugin = Main.getInstance;
    final int[] inputs = {10, 11, 12, 19, 20, 21, 28, 29, 30};
    final int output = 23;
    final int[] recommendations = {16, 25, 34};

    public menuCrafting(Player player) {
        super(player);
    }

    @Override
    public String getMenuName() {
        return Utils.color("&8Crafting");
    }

    @Override
    public int getSlots() {
        return 9*5;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {
        Inventory inventory = event.getClickedInventory();
        if (inventory == null || !inventory.equals(this.getInventory())) return;
        int slot = event.getSlot();

        if (Arrays.stream(inputs).anyMatch(i -> i == slot)) {
            checkMatrix(slot, event.getCursor());
        }

        if (slot == output) {
            ItemStack item = inventory.getItem(output);
            if (item == null) {
                if (event.getCursor() != null) {
                    event.setCancelled(true);
                    return;
                }
            }

            for (int input : inputs) {
                if (inventory.getItem(input) == null) continue;
                inventory.getItem(input).setAmount(inventory.getItem(input).getAmount() - 1);
            }

            if (checkMatrix(slot, null)) {
                event.setCancelled(true);
                if (event.getCursor() != null && event.getCursor().isSimilar(item)) {
                    ItemStack cursor = event.getCursor();
                    cursor.setAmount(cursor.getAmount() + 1);
                    event.setCursor(cursor);
                } else {
                    event.setCursor(item);
                }
            }
        }

    }

    @Override
    public void setMenuItems() {
        for (int i=0; i<this.getSlots(); i++) {
            int temp = i;
            if (Arrays.stream(inputs).anyMatch(j -> j == temp) ||
                temp == output ||
                Arrays.stream(recommendations).anyMatch(j -> j == temp))
                    continue;

            inventory.setItem(i, FILLER_GLASS);
            // TODO: recommendations
        }
    }

    private boolean checkMatrix(int slot, ItemStack item) {
        ItemStack[] matrix = new ItemStack[9];
        for (int i=0; i<inputs.length; i++) {
            if (inputs[i] == slot) {
                matrix[i] = item;
            } else {
                matrix[i] = inventory.getItem(inputs[i]);
            }
        }

        for (Recipe recipe : plugin.customRecipes) {
            if (recipe.matches(matrix)) {
                inventory.setItem(output, recipe.getResult());
                return true;
            }
        }
        return false;
    }

}
