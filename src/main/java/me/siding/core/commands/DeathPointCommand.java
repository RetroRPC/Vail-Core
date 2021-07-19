package me.siding.core.commands;

import me.siding.core.Main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

public class DeathPointCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player pl = (sender instanceof Player) ? (Player) sender : null;
        if (pl == null) {
            sender.sendMessage("This command can only be used by players.");
            return false;
        }
        if (!pl.hasPermission("essentials.back")) {
            pl.sendMessage(ChatColor.RED + "You do " + ChatColor.UNDERLINE + "not" + ChatColor.RED + " have permission to use this feature!");
            return true;
        }
        if (Main.death_locations.containsKey(pl.getUniqueId())) {
            Location l = Main.death_locations.get(pl.getUniqueId());
            if (l.getWorld().getName().equals("world_koth")) {
                pl.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + "(!) " + ChatColor.RED + "You cannot return to a deathpoint inside KOTH.");
                return true;
            }
            if (l.getWorld().getEnvironment() == World.Environment.THE_END) {
                pl.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + "(!) " + ChatColor.RED + "You cannot return to a deathpoint in The End dimension.");
                return true;
            }
            if (l.getBlock().getType() != Material.AIR && l
                    .getBlock().getType() != Material.WATER && l.getBlock().getType() != Material.STATIONARY_WATER && l
                    .getBlock().getType() != Material.LAVA && l.getBlock().getType() != Material.STATIONARY_LAVA) {
                pl.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + "<!>> " + ChatColor.RED + "A block is blocking your deathpoint.");
                return true;
            }
            if (!l.getWorld().equals(pl.getWorld())) {
                pl.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + "(!) " + ChatColor.RED + "You cannot use /back to teleport to a location in a different world.");
                pl.sendMessage(ChatColor.GRAY + "Teleport to the world you died in before using /back.");
                return true;
            }
            if (pl.teleport(l, PlayerTeleportEvent.TeleportCause.PLUGIN)) {
                Main.death_locations.remove(pl.getUniqueId());
                pl.sendMessage(ChatColor.GOLD + "You have returned to your last point of death.");
            }
        } else {
            pl.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + "<!> " + ChatColor.RED + "You cannot /back since your deathpoint is invalid.");
        }
        return true;
    }
}

