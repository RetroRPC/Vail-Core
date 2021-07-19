package me.siding.core.commands;

import me.siding.core.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PingCommand implements CommandExecutor {

    private int getPlayerPing(Player player) {
        try {
            String a = Bukkit.getServer().getClass().getPackage().getName().substring(23);
            Class<?> b = Class.forName("org.bukkit.craftbukkit." + a + ".entity.CraftPlayer");
            Object c = b.getMethod("getHandle", new Class[0]).invoke(player);
            int d = ((Integer) c.getClass().getDeclaredField("ping").get(c)).intValue();
            d = (d < 0) ? 0 : d;
            return d;
        } catch (Exception e) {
            return true ? 0 : -1;
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if ((cmd.getName().equalsIgnoreCase("ping"))) {
            if (args.length < 1) {
                if ((sender instanceof Player)) {
                    sender.sendMessage("");
                    sender.sendMessage("");
                    sender.sendMessage(Color.translate("&7&l<!> &aLatency Information&7: "));
                    sender.sendMessage(Color.translate("  &7- &aYour Ping&7:&f " + getPlayerPing((Player) sender) + "ms"));
                    sender.sendMessage("");
                    sender.sendMessage("");
                } else {
                    sender.sendMessage(ChatColor.RED + "Correct usage: /ping <player>");
                }
            } else {
                boolean found = false;
                for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                    String name = p.getName();
                    CraftPlayer handler = (CraftPlayer) p;
                    if (name.equalsIgnoreCase(args[0])) {
                        sender.sendMessage("");
                        sender.sendMessage("");
                        sender.sendMessage(Color.translate("&7&l<!> &aLatency Information&7: "));
                        sender.sendMessage(Color.translate("  &7- &a" + name + "'s Ping&7: &f" + getPlayerPing(p) + "ms"));
                        sender.sendMessage("");
                        sender.sendMessage("");
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    sender.sendMessage(ChatColor.RED + "Player not found.");
                }
            }
        }
        return true;
    }
}
