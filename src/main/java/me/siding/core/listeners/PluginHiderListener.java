package me.siding.core.listeners;

import me.siding.core.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

public class PluginHiderListener implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void onCMD(PlayerCommandPreprocessEvent event) {
        if (!event.getMessage().contains(":"))
            return;
        String[] message = event.getMessage().split(":");
        if (message[0] != null)
            for (Plugin plugin : Main.get().getServer().getPluginManager().getPlugins()) {
                if (plugin.getName().toLowerCase().equalsIgnoreCase(message[0].toLowerCase().replace("/", ""))) {
                    event.setCancelled(true);
                    break;
                }
            }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerCommandPreProcess(PlayerCommandPreprocessEvent e) {
        String cmd = e.getMessage().toLowerCase();
        cmd = cmd.replace("/", "");
        Player pl = e.getPlayer();
        if (cmd.startsWith("bukkit:") && !pl.isOp()) {
            e.setCancelled(true);
            pl.sendMessage(ChatColor.RED + "The command " + cmd + " is unavailable.");
            return;
        }
        if (!pl.isOp() && (cmd.toLowerCase().startsWith("?") || cmd.toLowerCase().startsWith("?") || cmd
                .equalsIgnoreCase("pl") || cmd.toLowerCase().startsWith("pl ") || cmd.toLowerCase().startsWith("plugins"))) {
            e.setCancelled(true);
            pl.sendMessage(ChatColor.RED + "The command " + cmd + " is unavailable.");
            return;
        }
    }
}
