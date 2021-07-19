package me.siding.core.utils;

import com.google.common.collect.Lists;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemUtils {
    public static ItemStack buildItem(Material m, short metaData, String name, String... description) {
        ItemStack is = new ItemStack(m, 1, metaData);
        ItemMeta im = is.getItemMeta();
        if (name != null)
            im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        if (description != null) {
            List<String> lore = Lists.newArrayList();
            for (String desc : description) {
                if (desc != null)
                    lore.add(ChatColor.translateAlternateColorCodes('&', desc));
            }
            im.setLore(lore);
        }
        is.setItemMeta(im);
        return is;
    }

    public static ItemStack Item(Material m, String name, String... description) {
        ItemStack is = new ItemStack(m, 1);
        ItemMeta im = is.getItemMeta();
        if (name != null)
            im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        if (description != null) {
            List<String> lore = Lists.newArrayList();
            for (String desc : description) {
                if (desc != null)
                    lore.add(ChatColor.translateAlternateColorCodes('&', desc));
            }
            im.setLore(lore);
        }
        is.setItemMeta(im);
        return is;
    }
}
