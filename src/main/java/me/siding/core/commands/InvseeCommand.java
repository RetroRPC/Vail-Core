package me.siding.core.commands;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import me.siding.core.utils.Color;
import me.siding.core.utils.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class InvseeCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        Player sender1 = (Player) sender;


        if (sender == null) {
            Bukkit.getLogger().info("That's not a console command!");
            return true;
        }
        if (args.length == 0) {
            sender1.sendMessage(Color.translate("&cUsage: /invsee <player>"));
            return true;
        }
        if (args.length != 1) {
            return true;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender1.sendMessage(Color.translate(args[0] + "&c is not online."));
            return true;
        }
        sender1.openInventory(this.createCopy(target));
        return true;
    }


    public Inventory createCopy(Player player) {
        FPlayer fplayer = FPlayers.getInstance().getByPlayer(player);


// By FLocation/Location


// By Faction Name/Tag
        Inventory inventory = Bukkit.createInventory(null, player.getInventory().getSize() + 9, player.getName() + "'s Inventory");
        ItemStack[] armor = player.getEquipment().getArmorContents();
        ItemStack[] items = player.getInventory().getContents();
        for (int i = 0; i < items.length; ++i) {
            ItemStack item = items[i];
            if (item != null && item.getType() != Material.AIR) {
                item = item.clone();
            }
            inventory.setItem(i, item);
        }
        for (int slot = inventory.getSize() - 9; slot < inventory.getSize(); ++slot) {
            ItemStack item = inventory.getItem(slot);
            if (item == null || item.getType() == Material.AIR) {
                inventory.setItem(slot, ItemUtils.buildItem(Material.STAINED_GLASS_PANE, DyeColor.BLACK.getWoolData(), " ", ""));
                inventory.setItem(44, ItemUtils.Item(Material.BOOK, "&a&l<!> &fPlayer Information &a&l<!>", "" + "&A&lPLAYER " + player.getDisplayName(), "&A&LFACTION &r: " + fplayer.getFaction().getTag(), "&a&lKILLS &f" +
                        getKills(player), "&a&lDEATHS &f" + getDeaths(player)));
            }

        }


        inventory.setItem(inventory.getSize() - 7, armor[3]);
        inventory.setItem(inventory.getSize() - 6, armor[2]);
        inventory.setItem(inventory.getSize() - 4, armor[1]);
        inventory.setItem(inventory.getSize() - 3, armor[0]);


        return inventory;

    }


    public int getKills(Player player) {
        return player.getStatistic(Statistic.PLAYER_KILLS);
    }

    public int getDeaths(Player player) {
        return player.getStatistic(Statistic.DEATHS);
    }


    //TODO: clean this code, its messy ASF

}
