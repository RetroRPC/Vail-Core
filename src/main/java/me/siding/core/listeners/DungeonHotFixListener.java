package me.siding.core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class DungeonHotFixListener implements Listener {

    @EventHandler
    public void onMobSpawn(EntitySpawnEvent e) {
        e.setCancelled(true);
    }
}

