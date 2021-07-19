package me.siding.core.listeners;

import me.siding.core.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public class ArrkitOneShotListener implements Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onEntityDamage(EntityDamageEvent e) {
        if (e.getCause() == EntityDamageEvent.DamageCause.CUSTOM)
            return;
        if (e.getEntity() instanceof Player &&
                e.getDamage() >= 80.0D) {
            Player p = (Player) e.getEntity();
            if (e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
                Entity eAttacker = ((EntityDamageByEntityEvent) e).getDamager();
                if (eAttacker instanceof Player) {
                    Player pAttacker = (Player) eAttacker;         //TODO ADD DISCORD WEBHOOKS
                    Bukkit.getLogger().info(Color.translate("&6[Templar]&f Player " + pAttacker.getName() + " has dealt " + e.getDamage() + " damage to " + p.getName() + " (silenced: " + p.hasMetadata("noDefenseProcs") + ") with " + pAttacker.getItemInHand() + " at " + p.getLocation()));
                }
            } else {
                Bukkit.getLogger().info(Color.translate("&6[Templar]&f Player " + p.getName() + " has taken " + e.getDamage() + " damage from " + e.getCause().name() + " at " + p.getLocation()));
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerDamagedByPlayer(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player && (
                e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK || e.getCause() == EntityDamageEvent.DamageCause.CUSTOM)) {
            Player pDamaged = (Player) e.getEntity();
            if ((pDamaged.getHealth() - e.getDamage() <= 0.0D || e.getDamage() >= 125.0D) && pDamaged
                    .getHealth() / pDamaged.getMaxHealth() > 0.33D && pDamaged
                    .getHealth() != e.getDamage() &&
                    !isNaked(pDamaged)) {
                Bukkit.getLogger().info(Color.translate("&6[Templar]&f Preventing " + pDamaged.getName() + " (HP: " + pDamaged.getHealth() + "/" + pDamaged.getMaxHealth() + ") from dieing due to " + e.getDamage() + " DMG (f: " + e.getDamage() + "), new DMG: " + (e.getDamage() * 0.33D) + ", cause: " + e.getCause()));
                e.setDamage(e.getDamage() * 0.33D);
            }
        }
    }

    private boolean isNaked(Player p) {
        for (ItemStack i : p.getEquipment().getArmorContents()) {
            if (i != null && i.getType() != Material.AIR)
                return false;
        }
        return true;
    }
}
