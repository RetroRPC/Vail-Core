package me.siding.core.commands;

import me.siding.core.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveAllCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player p = (Player) sender;

        if (!(sender instanceof Player)) {
            sender.sendMessage(Color.translate("&cYou are not a player!"));
            return false;
        }

        if (!(sender.hasPermission("core.giveall"))) {
            p.sendMessage("no perms");
            return true;
        }
        if (label.equalsIgnoreCase("giveall")) {
            if (args.length == 0) {
                p.sendMessage(ChatColor.RED + "Usage: /giveall <itemid|cash|hand> <amount>");
                return true;
            }
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("hand")) {
                    ItemStack handstack = p.getItemInHand();
                    if (handstack == null) {
                        p.sendMessage(ChatColor.RED + "You have nothing in your hand!");
                        return true;
                    }
                    for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                        player.sendMessage(Color.translate("&a&l<!> &f" + p.getName() + " &ahas given everyone &f" + handstack.getAmount() + " &ax &f" + handstack.getType().toString() + "&a."));
                        if (!player.equals(p)) {
                            player.getInventory().addItem(handstack);
                            player.updateInventory();
                        }
                    }
                    return true;
                }
                p.sendMessage(ChatColor.RED + "Usage: /giveall <itemid|cash|hand> <amount>");
                return true;
            }
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("money") || args[0].equalsIgnoreCase("cash")) {
                    int cash = 0;
                    try {
                        cash = Integer.valueOf(args[1]).intValue();
                    } catch (NumberFormatException e) {
                        p.sendMessage(ChatColor.RED + "Thats not a number!");
                        return true;
                    }
                    for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                        player.sendMessage(Color.translate("&a&l<!> &f" + p.getName() + " &ahas given everyone &f" + "$" + cash + "&a."));
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + cash);
                    }
                    return true;
                }
                String itemid = args[0];
                int amount = 0;
                int data = 0;
                int itemidint = 0;
                if (itemid.contains(":")) {
                    String[] split = itemid.split(":");
                    try {
                        itemidint = Integer.valueOf(split[0]).intValue();
                    } catch (NumberFormatException e) {
                        p.sendMessage(ChatColor.RED + "Thats not a number!");
                        return true;
                    }
                    try {
                        data = Integer.valueOf(split[1]).intValue();
                    } catch (NumberFormatException e) {
                        p.sendMessage(ChatColor.RED + "Thats not a number!");
                        return true;
                    }
                } else {
                    try {
                        itemidint = Integer.valueOf(args[0]).intValue();
                    } catch (NumberFormatException e) {
                        p.sendMessage(ChatColor.RED + "Thats not a number!");
                        return true;
                    }
                }
                try {
                    amount = Integer.valueOf(args[1]).intValue();
                } catch (NumberFormatException e) {
                    p.sendMessage(ChatColor.RED + "Thats not a number!");
                    return true;
                }
                Material item = Material.getMaterial(itemidint);
                if (item == null) {
                    p.sendMessage(ChatColor.GOLD + "Thats not a valid item ID!");
                    return true;
                }
                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                    player.sendMessage(Color.translate("&a&l<!> &f" + p.getName() + " &ahas given everyone &f" + amount + " &ax&f " + item + "&a."));
                    player.getInventory().addItem(new ItemStack(item, amount, (short) data));
                }
                return true;
            }
        }
        return false;
    }
}
