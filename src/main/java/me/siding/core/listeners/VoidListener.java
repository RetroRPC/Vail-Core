package me.siding.core.listeners;

import me.siding.core.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ListIterator;

public class VoidListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerEnterVoid(EntityDamageEvent e) {
        if (e.getCause() == EntityDamageEvent.DamageCause.VOID && e
                .getEntity() instanceof Player && e
                .getEntity().getLocation().getY() < 0.0D && e
                .getEntity().getWorld().getName().equals("world_the_end") &&
                !e.getEntity().isDead()) {
            Player p = (Player) e.getEntity();
            p.closeInventory();
            Location l = p.getLocation();
            l.setY(0.0D);

            e.setCancelled(true);
            e.setDamage(0.0D);
            Player killer = ((Player) e.getEntity()).getPlayer().getKiller();

            if (killer != null && killer.isOnline()) {
                try {
                    for (ListIterator<ItemStack> listIterator = p.getInventory().iterator(); listIterator.hasNext(); ) {
                        ItemStack i = listIterator.next();
                        if (i != null && i.getType() != Material.AIR)
                            killer.getWorld().dropItem(killer.getLocation().add(0.0D, 1.0D, 0.0D), i);
                    }
                    for (ItemStack i : p.getInventory().getArmorContents()) {
                        if (i != null && i.getType() != Material.AIR)
                            killer.getWorld().dropItem(killer.getLocation().add(0.0D, 1.0D, 0.0D), i);
                    }
                    if (!killer.hasMetadata("knocked" + p.getName() + "OffEdge")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "contest add " + killer.getUniqueId() + " endKnockoff 1");
                        killer.setMetadata("knocked" + p.getName() + "OffEdge", new FixedMetadataValue(Main.get(), Boolean.valueOf(true)));
                    }
                } catch (Exception err) {
                    err.printStackTrace();
                }
                p.getInventory().clear();
                p.getInventory().setHelmet(new ItemStack(Material.AIR));
                p.getInventory().setChestplate(new ItemStack(Material.AIR));
                p.getInventory().setLeggings(new ItemStack(Material.AIR));
                p.getInventory().setBoots(new ItemStack(Material.AIR));
                if (p.getOpenInventory() != null && p.getOpenInventory().getTopInventory() != null && p.getOpenInventory().getTopInventory().getName().equals("container.crafting"))
                    p.getOpenInventory().getTopInventory().clear();
                p.setHealth(0.0D);
                p.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "*** YOU HAVE BEEN AUTO-KILLED SINCE YOU WERE KNOCKED OFF THE END ***");
                killer.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "(!) " + ChatColor.GREEN + ChatColor.UNDERLINE + p.getName() + ChatColor.GREEN + " died in the void, and you were the last player to damage them. So their loot is yours!");
            } else {
                p.setHealth(0.0D);
            }
            Bukkit.getScheduler().runTaskLater(Main.get(), new Runnable() {
                public void run() {
                    l.getBlock().setType(Material.AIR);
                }
            }, 20L);
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent e) {
        if (e.getPlayer().getWorld().getName().equals("world_the_end") && e
                .getPlayer().getLocation().getY() <= 30.0D) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.RED + "You cannot drop items while falling into the void.");
        }
    }

}
