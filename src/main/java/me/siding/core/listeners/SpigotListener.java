package me.siding.core.listeners;

import me.siding.core.Main;
import me.siding.core.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.help.HelpTopic;

public class SpigotListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)

    public void handleKicks(PlayerLoginEvent e) {
        if (e.getResult() == PlayerLoginEvent.Result.KICK_WHITELIST) {
            e.setKickMessage(Color.translate("&8&l<!> &a&lVail&f&lPvP &8&l<!> " + "\n" + "\n" + "&7We are currently &fWhitelisted&7 for &r" + Main.getInstance().getConfig().getString("whitelist_reason") + "&7."
                    + "\n" + "&7For more information, join our discord." + "\n" + "https://discord.gg/cVYdTnx"));
        }


        if (e.getResult() == PlayerLoginEvent.Result.KICK_FULL) {
            e.setKickMessage(Color.translate("&7The Server is full. " + "\n" + "Donate at &fstore.vailpvp.org &7to get higher priority."));
        }


    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player p = event.getPlayer();
        if (!event.isCancelled()) {
            String command = event.getMessage().split(" ")[0];
            HelpTopic htopic = Bukkit.getServer().getHelpMap().getHelpTopic(command);
            if (htopic == null) {
                p.sendMessage(Color.translate("&8[&a&lVail&f&lPvP&8] &fUnknown command. Type \"&7/help&f\" for help."));
                event.setCancelled(true);
            }
        }
    }
}
