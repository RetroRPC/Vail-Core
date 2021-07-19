package me.siding.core.commands;

import me.siding.core.Main;
import me.siding.core.utils.Color;
import me.siding.core.utils.TimeUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class LootboxAllCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Color.translate("&cYou are not a player!"));
            return false;
        }

        Player player = (Player) sender;

        player.sendMessage(Color.translate("&a&l*** THE NEXT LOOTBOX ALL IS IN:&f&l" + TimeUtil.formatTime(Main.time.get()) + " &A&L***"));


        return true;
    }
}
