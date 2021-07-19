package me.siding.core.listeners;

import org.bukkit.entity.Blaze;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Witch;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;

public class HostileMobsListener implements Listener {

    @EventHandler
    public void blaze(EntityTargetEvent e) {

        if ((e.getEntity() instanceof Blaze)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void skeleton(EntityTargetEvent e) {

        if ((e.getEntity() instanceof Skeleton)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void witch(EntityTargetEvent e) {

        if ((e.getEntity() instanceof Witch)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void creeper(EntityTargetEvent e) {

        if ((e.getEntity() instanceof Creeper)) {
            e.setCancelled(true);
        }
    }
}
