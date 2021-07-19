package me.siding.core.commands;

import me.siding.core.Main;
import me.siding.core.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class BalanceCommand implements CommandExecutor {

    private final DecimalFormat decimalFormat = new DecimalFormat("###,###.00");


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {


        if (!(sender instanceof Player)) {
            sender.sendMessage(Color.translate("&cYou are not a player!"));
            return false;
        }

        if (args.length > 0) {
            Player player = (Player) sender;
            double balance = Main.getEconomy().getBalance(player);
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(Color.translate("&c&l<!> &c" + Bukkit.getPlayer(args[0]) + " is currently offline."));
                return true;
            }

            double d = Main.getEconomy().getBalance(target);
            sender.sendMessage(Color.translate("&a&l" + target.getName() + "'s Balance: &r$" + this.decimalFormat.format(d)));
            if (d > balance) {
                sender.sendMessage(Color.translate("&7They're Richer than you!"));
            }
            if (d == balance) {
                sender.sendMessage(Color.translate("&7They're as rich as you!"));
            }
            if (d < balance) {
                sender.sendMessage(Color.translate("&7You're richer than them!"));
            }
            return true;
        }

        Player player = (Player) sender;
        double balance = Main.getEconomy().getBalance(player);
        player.sendMessage(Color.translate("&a&lYour Balance: &r$" + this.decimalFormat.format(balance)));
        player.sendMessage(Color.translate("&7Type /withdraw or /pay to transfer funds."));
        return true;
    }
}
