package me.darkwinged.BetterFurnace.Utilites.Crafting;

import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class Recipe {

    private final ItemStack result;
    private final Map<Character, ItemStack> ingredients;
    private final String[] shape;

    public Recipe(ItemStack result, String[] shape, Map<Character, ItemStack> ingredients) {
        this.result = result;
        this.ingredients = ingredients;
        this.shape = shape;
    }

    public ItemStack getResult() {
        return result;
    }

    public Map<Character, ItemStack> getIngredients() {
        return ingredients;
    }

    public String[] getShape() {
        return shape;
    }

    public boolean matches(ItemStack[] matrix) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int index = i * 3 + j;
                ItemStack item = matrix[index];
                char c = shape[i].charAt(j);
                if (c == ' ' && item == null) {
                    continue;
                }
                if (c != ' ' && (item == null || !ingredients.containsKey(c) || !item.isSimilar(ingredients.get(c)))) {
                    return false;
                }
            }
        }
        return true;
    }



}
