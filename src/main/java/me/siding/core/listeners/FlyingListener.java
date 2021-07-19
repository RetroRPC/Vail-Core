package me.siding.core.listeners;


import me.siding.core.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class FlyingListener implements Listener {

    Main plugin;

    @EventHandler
    public void onFly(PlayerToggleFlightEvent e) {
        Player plr = e.getPlayer();
        if (plr.hasMetadata("vanished")) {
            return;
        } else {
            if (e.isFlying()) {
                plr.playSound(plr.getLocation(), Sound.FIREWORK_LAUNCH, 1.1F, 0.75F);
                plr.showPlayer(plr);
                for (Player players : Bukkit.getServer().getOnlinePlayers()) {
                    plr.showPlayer(players);
                    players.showPlayer(plr);
                }
            } else if (!e.isFlying()) {
                for (Player players : Bukkit.getServer().getOnlinePlayers()) {
                    plr.showPlayer(players);
                    players.showPlayer(plr);
                }
                plr.showPlayer(plr);
                plr.playSound(plr.getLocation(), Sound.FIREWORK_BLAST, 1.0F, 0.75F);
            }
            for (Player players : Bukkit.getServer().getOnlinePlayers()) {
                plr.showPlayer(players);
                players.showPlayer(plr);
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
    public void onPlayerFlyCommand(PlayerCommandPreprocessEvent e) {
        if (e.getPlayer().isOp())
            return;
        if (e.getMessage().equalsIgnoreCase("/fly") || e
                .getMessage().equalsIgnoreCase("/efly") || e
                .getMessage().equalsIgnoreCase("/essentials:fly") || e
                .getMessage().equalsIgnoreCase("/essentials:efly"))
            if (!e.getPlayer().isOnGround()) {
                e.getPlayer().sendMessage(ChatColor.RED + "You cannot use /fly if you are not on a solid block.");
                e.setCancelled(true);
            }
    }


    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        if (p.hasPermission("essentials.fly") &&
                !p.getAllowFlight())
            p.setAllowFlight(true);
    }
}



