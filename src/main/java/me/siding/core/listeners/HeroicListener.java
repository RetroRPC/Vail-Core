package me.siding.core.listeners;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class HeroicListener implements Listener {


    @EventHandler
    public void onDmg(EntityDamageByEntityEvent e) {

        if (e.getDamager() instanceof Player) {
            ItemStack item = ((Player) e.getDamager()).getInventory().getItemInHand();
            if (item == null)
                return;
            if (!item.hasItemMeta())
                return;
            if (!item.getItemMeta().hasLore())
                return;
            List<String> lore = item.getItemMeta().getLore();


            for (String s : lore) {
                if (s.contains("This weapon is stronger than diamond.")) {
                    e.setDamage(13);
                    //Bukkit.broadcastMessage("DEBUG WORKING");
                }
            }
        }
    }
}
