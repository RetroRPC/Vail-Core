package me.siding.core.listeners;

import net.ess3.api.events.NickChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;

import java.util.ArrayList;

public class EssentialsListener implements Listener {


    @EventHandler
    public void onNickChangeEvent(NickChangeEvent e) {
        if (e.getValue() != null && e.getValue().length() < 3 && !e.getController().getBase().isOp()) {
            e.setCancelled(true);
            e.getController().sendMessage(ChatColor.RED + "Error: " + ChatColor.DARK_RED + "That nickname is too short.");
        }
    }

    @EventHandler
    public void onPlayerChatTabCompleteEvent(PlayerChatTabCompleteEvent e) {
        if (!e.getPlayer().hasPermission("core.staff") || e.getPlayer().hasMetadata("vanished"))
            for (Object s : new ArrayList(e.getTabCompletions())) {
                if (Bukkit.getPlayer((String) s) != null && Bukkit.getPlayer((String) s).hasPermission("core.staff"))
                    if (Bukkit.getPlayer((String) s) != null && Bukkit.getPlayer((String) s).hasPermission("core.staff"))
                        e.getTabCompletions().remove(s);
            }
    }
}
