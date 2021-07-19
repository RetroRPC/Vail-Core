package me.siding.core.commands;

import me.siding.core.utils.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class DupeCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Color.translate("&c&l<!> &cYou cannot dupe this item."));
            return false;
        }
        Player player = (Player) sender;
        ItemStack item = player.getItemInHand();
        if (item == null || !item.hasItemMeta() || !item.getItemMeta().hasDisplayName() || !item.getItemMeta().hasLore() || item.getType() != Material.BOOK) {
            sender.sendMessage(Color.translate("&c&l<!> &cYou cannot dupe this item."));
            return false;
        }
        ItemMeta itemMeta = item.getItemMeta();
        String display = itemMeta.getDisplayName();
        String color = display.substring(0, 2);
        List<String> lore = itemMeta.getLore();
        boolean isOpened = lore.contains(Color.translate("&7Drag n' Drop onto item to enchant."));
        if (!isOpened) {
            if (display.indexOf(" ") == -1) {
                sender.sendMessage(Color.translate("&c&l<!> &cYou cannot dupe this item."));
                return false;
            }
            display = display.substring(display.indexOf(" ") + 1);
        }
        if ((!display.startsWith("Enchantment Book") && !isOpened) || color.contains(Color.translate("&4"))) {
            sender.sendMessage(Color.translate("&c&l<!> &cYou cannot dupe this item."));
            return false;
        }
        item.setAmount(64);
        player.sendMessage(Color.translate("&a&l<!> &aYou have successfully duplicated your item."));
        return false;
    }

    //TODO: make a better way to make this
}
