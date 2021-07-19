package me.siding.core.listeners;

import me.siding.core.Main;
import me.siding.core.commands.TemplarCommand;
import me.siding.core.utils.Color;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class RebootListener implements Listener {


    @EventHandler
    public void onReboot(AsyncPlayerPreLoginEvent event) {
        if (TemplarCommand.restart_task_exists || Main.plugin.disabling) {
            event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
            event.setKickMessage(Color.translate("&c&l(!) &cYou cannot join since the server is rebooting."));
        }
    }
}
