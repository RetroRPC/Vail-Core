package me.siding.core.commands;

import me.siding.core.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TrashCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Color.translate("&cYou are not a player!"));
            return false;
        }

        Player player = (Player) sender;

        player.openInventory(Bukkit.createInventory(null, 54, Color.translate("&a&lVail&f&lPvP &8Trash Bin")));
        player.sendMessage(Color.translate("&a&l(!) &aOpening &fTrashbin&a GUI..."));
        player.playSound(player.getLocation(), Sound.CHEST_OPEN, 1.0F, 1.0F);
        return true;
    }
}
