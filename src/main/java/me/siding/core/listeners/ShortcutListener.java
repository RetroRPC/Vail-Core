package me.siding.core.listeners;

import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ShortcutListener implements Listener {


    @EventHandler
    public void onCMD(PlayerCommandPreprocessEvent e) {
        if (e.getMessage().equalsIgnoreCase("/tl"))
            e.setMessage("/f coords");
        if (e.getMessage().equalsIgnoreCase("/mc"))
            e.setMessage("/lootbox");
        if (e.getMessage().equalsIgnoreCase("/am"))
            e.setMessage("/toggleanimation");
        if (e.getMessage().equalsIgnoreCase("/xp"))
            e.setMessage("/exp");
        if (e.getMessage().equalsIgnoreCase("/ft"))
            e.setMessage("/itemfilter toggle");

        if (e.getMessage().equalsIgnoreCase("/ah"))
            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BAT_TAKEOFF, 2.0F, 2.0F);
    }
}
