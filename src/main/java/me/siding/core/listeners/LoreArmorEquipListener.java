package me.siding.core.listeners;

import com.codingforcookies.armorequip.ArmorEquipEvent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class LoreArmorEquipListener implements Listener {


    String Translate(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    @EventHandler
    public void armorUnequip(ArmorEquipEvent e) {
        if (e.getOldArmorPiece() != null && e.getOldArmorPiece().getType() != Material.AIR && e.getOldArmorPiece().hasItemMeta()) {
            ItemStack armor = e.getOldArmorPiece();
            Player player = e.getPlayer();
            if (armor.getItemMeta().hasLore()) {
                if (armor.getItemMeta().getLore().contains(this.Translate("Godly Overload I"))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l[-]&c Godly Overload I&7: &7Removing HEALTH_BOOST  III "));
                } else if (armor.getItemMeta().getLore().contains(this.Translate("Godly Overload II"))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l[-]&c Godly Overload II&7: &7Removing HEALTH_BOOST  IV "));
                } else if (armor.getItemMeta().getLore().contains(this.Translate("Godly Overload III"))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l[-]&c Godly Overload III&7: &7Removing HEALTH_BOOST  V "));
                }
                if (armor.getItemMeta().getLore().contains(this.Translate("&6Overload I"))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l[-]&c Overload I&7: &7Removing HEALTH_BOOST  I "));
                } else if (armor.getItemMeta().getLore().contains(this.Translate("&6Overload II"))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l[-]&c Overload II&7: &7&7Removing HEALTH_BOOST  II "));
                } else if (armor.getItemMeta().getLore().contains(this.Translate("&6Overload III"))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l[-]&c Overload III&7: &7&7Removing HEALTH_BOOST  III "));
                } else if (armor.getItemMeta().getLore().contains(this.Translate("&eObsidianshield I"))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l[-]&c Obsidianshield I&7: &7Removing Fire Resistance I "));
                }
                if (armor.getItemMeta().getLore().contains(this.Translate("&6Gears I"))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l[-]&c Gears I&7: &7Removing SWIFTNESS I "));
                } else if (armor.getItemMeta().getLore().contains(this.Translate("&6Gears II"))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l[-]&c Gears II&7: &7Removing SWIFTNESS II "));
                } else if (armor.getItemMeta().getLore().contains(this.Translate("&6Gears III"))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l[-]&c Gears III&7: &7Removing SWIFTNESS III "));
                } else if (armor.getItemMeta().getLore().contains(this.Translate("&6Drunk I"))) {
                    player.sendMessage(this.Translate("&c&l[-] Drunk I&7: Removing SLOW_DIGGING I"));
                    player.sendMessage(this.Translate("&c&l[-]&c Drunk I&7: Removing INCREASE_DAMAGE I"));
                    player.sendMessage(this.Translate("&c&l[-]&c Drunk I&7: Removing SLOW I"));
                } else if (armor.getItemMeta().getLore().contains(this.Translate("&6Drunk II"))) {
                    player.sendMessage(this.Translate("&c&l[-]&c Drunk II&7: Removing SLOW_DIGGING II"));
                    player.sendMessage(this.Translate("&c&l[-]&c Drunk II&7: Removing INCREASE_DAMAGE II"));
                    player.sendMessage(this.Translate("&c&l[-]&c Drunk II&7: Removing SLOW II"));
                } else if (armor.getItemMeta().getLore().contains(this.Translate("&6Drunk III"))) {
                    player.sendMessage(this.Translate("&c&l[-]&c Drunk III&7: Removing SLOW_DIGGING III"));
                    player.sendMessage(this.Translate("&c&l[-]&c Drunk III&7: Removing INCREASE_DAMAGE III"));
                    player.sendMessage(this.Translate("&c&l[-]&c Drunk III&7: Removing SLOW III"));
                } else if (armor.getItemMeta().getLore().contains(this.Translate("&6Drunk IV"))) {
                    player.sendMessage(this.Translate("&c&l[-]&c Drunk IV&7: Removing SLOW_DIGGING IV"));
                    player.sendMessage(this.Translate("&c&l[-]&c Drunk IV&7: Removing INCREASE_DAMAGE IV"));
                    player.sendMessage(this.Translate("&c&l[-]&c Drunk IV&7: Removing SLOW IV"));
                }
                if (armor.getItemMeta().getLore().contains(this.Translate("&bSprings I"))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l[-]&c Springs I&7: &7Removing JUMP I "));
                } else if (armor.getItemMeta().getLore().contains(this.Translate("&bSprings II"))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l[-]&c Springs II&7: &7Removing JUMP II "));
                } else if (armor.getItemMeta().getLore().contains(this.Translate("&bSprings III"))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l[-]&c Springs III&7: &7Removing JUMP III "));
                }
                if (armor.getItemMeta().getLore().contains(this.Translate("&bAnti Gravity I"))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l[-]&c Anti Gravity I&7: &7Removing JUMP III "));
                } else if (armor.getItemMeta().getLore().contains(this.Translate("&bAnti Gravity II"))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l[-]&c Anti Gravity II&7: &7Removing JUMP IV "));
                } else if (armor.getItemMeta().getLore().contains(this.Translate("&bAnti Gravity III"))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l[-]&c Anti Gravity III&7: &7Removing JUMP V "));
                }
                if (armor.getItemMeta().getLore().contains(this.Translate("&7Glowing I"))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l[-]&c Glowing I: &7Removing Night Vision I "));
                }
            }
        }
    }

    @EventHandler
    public void armorEquip(ArmorEquipEvent e) {
        if (e.getNewArmorPiece() != null && e.getNewArmorPiece().getType() != Material.AIR && e.getNewArmorPiece().hasItemMeta()) {
            ItemStack armor = e.getNewArmorPiece();
            Player player = e.getPlayer();
            if (armor.getItemMeta().hasLore()) {
                if (armor.getItemMeta().getLore().contains(this.Translate("Godly Overload I"))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l[+]&a Godly Overload I: applying HEALTH_BOOST  III "));
                } else if (armor.getItemMeta().getLore().contains(this.Translate("Godly Overload II"))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l[+]&a Godly Overload II&7: applying HEALTH_BOOST  IV "));
                } else if (armor.getItemMeta().getLore().contains(this.Translate("Godly Overload III"))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l[+]&a Godly Overload III&7: Applying HEALTH_BOOST  V "));
                }
                if (armor.getItemMeta().getLore().contains(this.Translate("&6Overload I"))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l[+]&a Overload I&7: Applying HEALTH_BOOST  I "));
                } else if (armor.getItemMeta().getLore().contains(this.Translate("&6Overload II"))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l[+]&a Overload II&7: Applying HEALTH_BOOST  II "));
                } else if (armor.getItemMeta().getLore().contains(this.Translate("&6Overload III"))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l[+]&a Overload III&7: Applying HEALTH_BOOST  III "));
                } else if (armor.getItemMeta().getLore().contains(this.Translate("&eObsidianshield I"))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l[+]&a Obsidianshield I&7: Applying Fire Resistance I "));
                }
                if (armor.getItemMeta().getLore().contains(this.Translate("&6Gears I"))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l[+]&a Gears I&7: Applying SWIFTNESS I "));
                } else if (armor.getItemMeta().getLore().contains(this.Translate("&6Gears II"))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l[+]&a Gears II&7: Applying SWIFTNESS II "));
                } else if (armor.getItemMeta().getLore().contains(this.Translate("&6Gears III"))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l[+]&a Gears III&7: Applying SWIFTNESS III "));
                } else if (armor.getItemMeta().getLore().contains(this.Translate("&6Drunk I"))) {
                    player.sendMessage(this.Translate("&a&l[+]&a Drunk I&7: Applying SLOW_DIGGING I"));
                    player.sendMessage(this.Translate("&a&l[+]&a Drunk I&7: Applying INCREASE_DAMAGE I"));
                    player.sendMessage(this.Translate("&a&l[+]&a Drunk I&7: Applying SLOW I"));
                } else if (armor.getItemMeta().getLore().contains(this.Translate("&6Drunk II"))) {
                    player.sendMessage(this.Translate("&a&l[+]&a Drunk II&7: Applying SLOW_DIGGING II"));
                    player.sendMessage(this.Translate("&a&l[+]&a Drunk II&7: Applying INCREASE_DAMAGE II"));
                    player.sendMessage(this.Translate("&a&l[+]&a Drunk II&7: Applying SLOW II"));
                } else if (armor.getItemMeta().getLore().contains(this.Translate("&6Drunk III"))) {
                    player.sendMessage(this.Translate("&a&l[+]&a Drunk III&7: Applying SLOW_DIGGING III"));
                    player.sendMessage(this.Translate("&a&l[+]&a Drunk III&7: Applying INCREASE_DAMAGE III"));
                    player.sendMessage(this.Translate("&a&l[+]&a Drunk III&7: Applying SLOW III"));
                } else if (armor.getItemMeta().getLore().contains(this.Translate("&6Drunk IV"))) {
                    player.sendMessage(this.Translate("&a&l[+]&a Drunk IV&7: Applying SLOW_DIGGING IV"));
                    player.sendMessage(this.Translate("&a&l[+]&a Drunk IV&7: Applying INCREASE_DAMAGE IV"));
                    player.sendMessage(this.Translate("&a&l[+]&a Drunk IV&7: Applying SLOW IV"));
                }
                if (armor.getItemMeta().getLore().contains(this.Translate("&bSprings I"))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l[+]&a Springs I&7: Applying JUMP  I "));
                } else if (armor.getItemMeta().getLore().contains(this.Translate("&bSprings II"))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l[+]&a Springs II&7: Applying JUMP  II "));
                } else if (armor.getItemMeta().getLore().contains(this.Translate("&bSprings III"))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l[+]&a Springs III&7: Applying JUMP  III "));
                }
                if (armor.getItemMeta().getLore().contains(this.Translate("&bAnti Gravity I"))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l[+]&a Anti Gravity I&7: Applying JUMP  III "));
                } else if (armor.getItemMeta().getLore().contains(this.Translate("&bAnti Gravity II"))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l[+]&a Anti Gravity II&7: Applying JUMP  IV "));
                } else if (armor.getItemMeta().getLore().contains(this.Translate("&bAnti Gravity III"))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l[+]&a Anti Gravity III&7: Applying JUMP  V "));
                }
                if (armor.getItemMeta().getLore().contains(this.Translate("&7Glowing I"))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l[+]&a Glowing I&7: Applying Night Vision I "));
                } else if (armor.getItemMeta().getLore().contains(this.Translate("&eEnder Walker I")) || armor.getItemMeta().getLore().contains(this.Translate("&eEnder Walker II")) || armor.getItemMeta().getLore().contains(this.Translate("&eEnder Walker III")) || armor.getItemMeta().getLore().contains(this.Translate("&eEnder Walker IV")) || armor.getItemMeta().getLore().contains(this.Translate("&eEnder Walker V"))) {
                    player.playSound(player.getLocation(), Sound.WITHER_HURT, 4.0f, 0.2f);
                }
            }
        }
    }


}
