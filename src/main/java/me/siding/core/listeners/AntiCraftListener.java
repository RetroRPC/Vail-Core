package me.siding.core.listeners;

import me.siding.core.utils.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

public class AntiCraftListener implements Listener {


    @EventHandler
    public void onCraftItem(CraftItemEvent event) {
        ItemStack input = event.getCurrentItem();
        Player player = (Player) event.getWhoClicked();
        if (event.getRecipe().getResult().getType() == Material.HOPPER) {
            event.setResult(null);
            player.sendMessage(Color.translate("&c&L(!) &cYour are not allowed to craft Hoppers."));
        }
        if (input.getType() == Material.GOLDEN_APPLE && input.getDurability() == 1) {
            event.setCancelled(true);
            event.setCurrentItem(null);
            player.updateInventory();
            player.sendMessage(Color.translate("&c&L(!) &cYour are not allowed to craft God Apples."));
        }
    }
}
