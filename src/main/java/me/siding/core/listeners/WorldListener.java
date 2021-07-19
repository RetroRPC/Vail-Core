package me.siding.core.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WorldListener implements Listener {


    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
    public void onWeatherChange(WeatherChangeEvent e) {
        if (e.toWeatherState()) {
            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.hasBlock() && e.getAction() == Action.LEFT_CLICK_BLOCK) {
            Material m = e.getClickedBlock().getType();
            if (m == Material.DOUBLE_PLANT)
                e.getClickedBlock().setType(Material.AIR);
        }

    }
}