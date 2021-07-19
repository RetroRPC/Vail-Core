package me.siding.core.commands;

import me.siding.core.utils.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PotsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Color.translate("&cYou are not a player!"));
            return false;
        }

        Player player = (Player) sender;

        int pots = 0;
        ItemStack hp = new ItemStack(Material.POTION, 1, (short) 16421);
        player.sendMessage(Color.translate("" + hp));

        for (int i = 0; i < 100; i++) {
            player.getInventory().addItem(hp);
            pots++;
        }
        return false;
    }
}

