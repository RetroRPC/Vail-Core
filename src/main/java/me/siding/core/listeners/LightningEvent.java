package me.siding.core.listeners;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;


public class LightningEvent implements Listener {


    @EventHandler
    public void EntityDamage(EntityDamageEvent e) {
        if (e.getCause() == EntityDamageEvent.DamageCause.LIGHTNING)
            e.setCancelled(true);
        if (e.getCause() == EntityDamageEvent.DamageCause.LAVA) {
            e.setCancelled(true);
        }
    }
}
