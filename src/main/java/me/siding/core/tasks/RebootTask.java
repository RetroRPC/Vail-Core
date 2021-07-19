package me.siding.core.tasks;

import me.siding.core.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

public class RebootTask extends BukkitRunnable {

    volatile int time = 60;
    private final String warn = Color.translate("&f&l*&a&L*&f&L* &a&lTHE SERVER IS REBOOTING IN [time] SECONDS &F&L*&A&L*&F&L*");

    public RebootTask(boolean force) {

        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage(Color.translate("&6&l*** A TEMPLAR REBOOT IS GOING TO ACCUR *** "));
        Bukkit.broadcastMessage(Color.translate("&7This happens when a thread is forced to reboot."));
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");

        if (force) {
            this.time = 0;
        }
    }

    private void doBroadcast() {
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage(this.warn.replace("[time]", String.valueOf(this.time)));
        Bukkit.broadcastMessage("");
    }

    public void ScheduledRebootTask(boolean force) {

        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage(Color.translate("&6&l*** A TEMPLAR REBOOT IS GOING TO ACCUR *** "));
        Bukkit.broadcastMessage(Color.translate("&7This happens when a thread is forced to reboot."));
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");

        if (force) {
            this.time = 0;
        }
    }


    @Override
    public synchronized void run() {
        if (this.time < -10) {
            return;
        }
        switch (this.time) {
            case 60:
                doBroadcast();
                break;
            case 30:
                doBroadcast();
                break;
            case 15:
                doBroadcast();
                break;
            case 10:
                doBroadcast();
                Bukkit.savePlayers();
                for (World world : Bukkit.getWorlds()) {
                    world.save();
                }
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "/save-all");
                Bukkit.savePlayers();

                break;
            case 5:
                doBroadcast();
                break;
            case 4:
                doBroadcast();
                break;
            case 3:
                doBroadcast();
                break;
            case 2:
                doBroadcast();
                break;
            case 1:
                doBroadcast();
                break;
            case 0:
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "/send all hub-1");
                break;
            case -10:
                Bukkit.shutdown();
                break;
        }
        this.time--;

    }
}
