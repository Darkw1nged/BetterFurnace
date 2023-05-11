package me.darkwinged.BetterFurnace.Listeners;

import me.darkwinged.BetterFurnace.menuCrafting;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class CustomCrafting implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        if (block == null) return;

        if (player.isSneaking() && block.getType().equals(Material.CRAFTING_TABLE)) {
            event.setCancelled(true);
            new menuCrafting(player).open();
        }
    }

}
