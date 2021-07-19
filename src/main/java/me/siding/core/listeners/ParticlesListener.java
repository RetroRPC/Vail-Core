package me.siding.core.listeners;


import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class ParticlesListener implements Listener {

    @EventHandler
    public void flp(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (p.hasMetadata("vanished")) {
            return;
        } else {
            if (p.isFlying()) {
                p.getWorld().spigot().playEffect(p.getLocation().add(0.0, -1.0, 0.0), Effect.CLOUD, 0, 0, 0.0f, 0.0f, 0.0f, 0.0f, 8, 32);
            }
        }
    }
}
