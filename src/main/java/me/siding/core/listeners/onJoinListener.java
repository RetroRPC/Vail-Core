package me.siding.core.listeners;

import me.siding.core.Main;
import me.siding.core.utils.Color;
import me.siding.core.utils.Title;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.concurrent.ThreadLocalRandom;

public class onJoinListener implements Listener {


    @EventHandler
    public void onJoinEvent(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        for (int i = 0; i < 50; i++) {
            player.sendMessage("");
            if (player.hasPermission("essentials.fly")) {
                player.setAllowFlight(true);
            }
        }
        for (String s : Main.getInstance().getConfig().getStringList("welcome_message")) {
            player.sendMessage(Color.translate(s));
        }

        if (!(player.hasPlayedBefore())) {
            player.playSound(player.getLocation(), Sound.FIREWORK_BLAST, 2.0F, 0.5F);
            Title title = new Title("&6&lYOU HAVE ENTERED THE PLANET FOR THE", "&7&nFOR THE FIRST TIME", 15, 200, 15);
            title.setTitleColor(ChatColor.GOLD);
            title.setSubtitleColor(ChatColor.GREEN);
            title.setTimingsToTicks(); // IMPORTANT
            title.send(player);

        } else {
            player.playSound(player.getLocation(), Sound.GHAST_MOAN, ThreadLocalRandom.current().nextFloat(), 0.1F);
            //nothing here for now
        }
    }
}
