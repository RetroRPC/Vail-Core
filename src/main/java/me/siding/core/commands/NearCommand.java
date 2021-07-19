package me.siding.core.commands;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.struct.Relation;
import me.siding.core.events.ArmorCheckEvent;
import me.siding.core.utils.Color;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class NearCommand implements CommandExecutor {
    StringBuilder output = new StringBuilder();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;


        int i = 0;
        Player pl = (Player) sender;
        String newName = "";
        String groupName = "";
        boolean hide = false;
        double plX = pl.getLocation().getX();
        double plY = pl.getLocation().getY();
        double plZ = pl.getLocation().getZ();
        FPlayer fPL = FPlayers.getInstance().getByPlayer(pl);
        for (Entity p : pl.getNearbyEntities(200D, 200D, 200D)) {
            if (p instanceof Player && p != pl) {
                Player plr = (Player) p;
                double pX = plr.getLocation().getX();
                double pY = plr.getLocation().getY();
                double pZ = plr.getLocation().getZ();
                double xNew = pX - plX;
                double yNew = pY - plY;
                double zNew = pZ - plZ;
                if (xNew < 0D)
                    xNew *= -1D;
                if (yNew < 0D)
                    yNew *= -1D;
                if (zNew < 0D)
                    zNew *= -1D;
                double distanceApart = Math.round(xNew + yNew + zNew);
                if (200D >= distanceApart) {
                    i++;
                    byte b;
                    int j;
                    for (j = b = 0; b < j; ) {
                        groupName = player.getDisplayName();
                        b++;
                    }
                    FPlayer fPlr = FPlayers.getInstance().getByPlayer(plr);
                    if (plr.getInventory().getBoots() != null && plr.getInventory().getBoots().getItemMeta().getLore() != null) {
                        ItemStack item = plr.getInventory().getBoots();
                        ItemMeta itemMeta = item.getItemMeta();
                        ArmorCheckEvent.CheckEnchants(itemMeta, "ghost", item);
                        if (ArmorCheckEvent.Check) {
                            ArrayList<String> lore = new ArrayList<>();
                            List<String> lore2 = itemMeta.getLore();
                            for (String str : lore2) {
                                lore.add(ChatColor.stripColor(str).toLowerCase());
                                int level = 0;
                                if (lore.contains("Ghost I")) {
                                    level = 1;
                                } else if (lore.contains("Ghost II")) {
                                    level = 2;
                                } else if (lore.contains("Ghost III")) {
                                    level = 3;
                                }
                                if (level >= 1) {
                                    if (fPlr.getRelationToLocation() == Relation.MEMBER)
                                        hide = true;
                                    if (level >= 2 && !hide &&
                                            fPlr.getRelationToLocation() == Relation.ALLY)
                                        hide = true;
                                    if (level == 3 && !hide &&
                                            fPlr.getRelationToLocation() == Relation.TRUCE)
                                        hide = true;
                                }
                            }
                        }
                    }
                    String plrname = plr.getDisplayName();

                    if (newName == "" && !hide || plr.hasMetadata("vanished")) {
                        newName = groupName + " " + plrname + " &a&l(&f" + distanceApart + "m&a&l)&r";
                        continue;
                    }
                    if (!hide) {
                        newName = newName + ", " + plrname + " &a&l(&f" + distanceApart + "m&a&l)&r";
                        continue;
                    }
                    return true;
                }
            }
        }
        if (i != 0) {
            pl.sendMessage(Color.translate("&a&l<!> &aThere are &f" + i + "&a player(s) nearby&7:"));
            pl.sendMessage(Color.translate(newName));
            pl.sendMessage("");
            //pl.sendMessage(Color.translate("&a&lNearby Members: &r" + (fPL.getRelationToLocation() == Relation.MEMBER)));
            //TODO nearby fac members & enemies also sum other stuff
        } else {
            pl.sendMessage(Color.translate("&e&l<!> &eThere is no one visible nearby..."));
        }
        return true;
    }
}
