package me.siding.core.listeners;

import me.siding.core.utils.Color;
import me.siding.core.utils.Cooldowns;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ArmorSwapListener implements Listener {
    @EventHandler
    public void onUse(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            ItemStack i = p.getItemInHand();
            ItemStack helm = p.getInventory().getHelmet();
            ItemStack cplate = p.getInventory().getChestplate();
            ItemStack leggs = p.getInventory().getLeggings();
            ItemStack boots = p.getInventory().getBoots();
            ItemStack air = new ItemStack(Material.AIR);
            String s = i.getType().toString();

            if (s.endsWith("_HELMET")) {
                if (Cooldowns.isOnCooldown("armor_swap", p)) {
                    return;
                }
                p.getInventory().setHelmet(air);
                p.getInventory().setHelmet(i);
                p.setItemInHand(helm);
                p.playSound(p.getLocation(), Sound.valueOf("ORB_PICKUP"), 1.0F, 0.9F);
                p.updateInventory();
                Cooldowns.addCooldown("armor_swap", p, 1);

                if (helm.getItemMeta().getLore().contains(Color.translate("&6Drunk I"))) {
                    p.sendMessage(Color.translate("&c&l[-] Drunk I&7: Removing SLOW_DIGGING I"));
                    p.sendMessage(Color.translate("&c&l[-]&c Drunk I&7: Removing INCREASE_DAMAGE I"));
                    p.sendMessage(Color.translate("&c&l[-]&c Drunk I&7: Removing SLOW I"));
                } else if (helm.getItemMeta().getLore().contains(Color.translate("&6Drunk II"))) {
                    p.sendMessage(Color.translate("&c&l[-]&c Drunk II&7: Removing SLOW_DIGGING II"));
                    p.sendMessage(Color.translate("&c&l[-]&c Drunk II&7: Removing INCREASE_DAMAGE II"));
                    p.sendMessage(Color.translate("&c&l[-]&c Drunk II&7: Removing SLOW II"));
                } else if (helm.getItemMeta().getLore().contains(Color.translate("&6Drunk III"))) {
                    p.sendMessage(Color.translate("&c&l[-]&c Drunk III&7: Removing SLOW_DIGGING III"));
                    p.sendMessage(Color.translate("&c&l[-]&c Drunk III&7: Removing INCREASE_DAMAGE III"));
                    p.sendMessage(Color.translate("&c&l[-]&c Drunk III&7: Removing SLOW III"));
                } else if (helm.getItemMeta().getLore().contains(Color.translate("&6Drunk IV"))) {
                    p.sendMessage(Color.translate("&c&l[-]&c Drunk IV&7: Removing SLOW_DIGGING IV"));
                    p.sendMessage(Color.translate("&c&l[-]&c Drunk IV&7: Removing INCREASE_DAMAGE IV"));
                    p.sendMessage(Color.translate("&c&l[-]&c Drunk IV&7: Removing SLOW IV"));
                }
                return;
            }

            if (s.endsWith("_CHESTPLATE")) {
                if (Cooldowns.isOnCooldown("armor_swap", p)) {
                    return;
                }
                p.getInventory().setChestplate(air);
                p.getInventory().setChestplate(i);
                p.setItemInHand(cplate);
                Cooldowns.addCooldown("armor_swap", p, 1);

                p.playSound(p.getLocation(), Sound.valueOf("ORB_PICKUP"), 1.0F, 0.9F);
                p.updateInventory();
                return;
            }
            if (s.endsWith("_LEGGINGS")) {
                if (Cooldowns.isOnCooldown("armor_swap", p)) {
                    return;
                }
                p.getInventory().setLeggings(air);
                p.getInventory().setLeggings(i);
                p.setItemInHand(leggs);
                Cooldowns.addCooldown("leggings_swap", p, 1);

                p.playSound(p.getLocation(), Sound.valueOf("ORB_PICKUP"), 1.0F, 0.9F);
                p.updateInventory();
                return;
            }
            if (s.endsWith("_BOOTS")) {
                if (Cooldowns.isOnCooldown("armor_swap", p)) {
                    return;
                }
                p.getInventory().setBoots(air);
                p.getInventory().setBoots(i);
                p.setItemInHand(boots);
                Cooldowns.addCooldown("armor_swap", p, 1);
                p.playSound(p.getLocation(), Sound.valueOf("ORB_PICKUP"), 1.0F, 0.9F);
                p.updateInventory();
                return;
            }
        }
    }
}
