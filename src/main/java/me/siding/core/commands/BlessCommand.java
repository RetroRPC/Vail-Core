package me.siding.core.commands;


import me.siding.core.Main;
import me.siding.core.utils.Color;
import me.siding.core.utils.Cooldowns;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class BlessCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] label) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Color.translate("&cYou are not a player!"));
            return false;
        }

        Player player = (Player) sender;
        if (player.hasPermission("core.bless")) {
            if (Cooldowns.isOnCooldown("bless_cooldown", (Player) sender)) {
                sender.sendMessage(Color.translate("&c&l(!) &c&l&n/Bless&c: is still on a cooldown for another &l" + Main.getRemaining(Cooldowns.getCooldownForPlayerLong("bless_cooldown", (Player) sender), true)));
                return true;
            }
            ItemStack helm = player.getInventory().getHelmet();


            if (helm.getItemMeta().getLore().contains(Color.translate("&6Drunk I")) || (!player.hasPotionEffect(PotionEffectType.SLOW)) || (!player.hasPotionEffect(PotionEffectType.SLOW_DIGGING))) {
                player.sendMessage(Color.translate("&c&l[-] Drunk I&7: Removing SLOW_DIGGING I"));
                player.sendMessage(Color.translate("&c&l[-]&c Drunk I&7: Removing INCREASE_DAMAGE I"));
                player.sendMessage(Color.translate("&c&l[-]&c Drunk I&7: Removing SLOW I"));
            } else if (helm.getItemMeta().getLore().contains(Color.translate("&6Drunk II")) || (!player.hasPotionEffect(PotionEffectType.SLOW)) || (!player.hasPotionEffect(PotionEffectType.SLOW_DIGGING))) {
                player.sendMessage(Color.translate("&c&l[-]&c Drunk II&7: Removing SLOW_DIGGING II"));
                player.sendMessage(Color.translate("&c&l[-]&c Drunk II&7: Removing INCREASE_DAMAGE II"));
                player.sendMessage(Color.translate("&c&l[-]&c Drunk II&7: Removing SLOW II"));
            } else if (helm.getItemMeta().getLore().contains(Color.translate("&6Drunk III")) || (!player.hasPotionEffect(PotionEffectType.SLOW)) || (!player.hasPotionEffect(PotionEffectType.SLOW_DIGGING))) {
                player.sendMessage(Color.translate("&c&l[-]&c Drunk III&7: Removing SLOW_DIGGING III"));
                player.sendMessage(Color.translate("&c&l[-]&c Drunk III&7: Removing INCREASE_DAMAGE III"));
                player.sendMessage(Color.translate("&c&l[-]&c Drunk III&7: Removing SLOW III"));
            } else if (helm.getItemMeta().getLore().contains(Color.translate("&6Drunk IV")) || (!player.hasPotionEffect(PotionEffectType.SLOW)) || (!player.hasPotionEffect(PotionEffectType.SLOW_DIGGING))) {
                player.sendMessage(Color.translate("&c&l[-]&c Drunk IV&7: Removing SLOW_DIGGING IV"));
                player.sendMessage(Color.translate("&c&l[-]&c Drunk IV&7: Removing INCREASE_DAMAGE IV"));
                player.sendMessage(Color.translate("&c&l[-]&c Drunk IV&7: Removing SLOW IV"));
            }

            if (player.hasPotionEffect(PotionEffectType.BLINDNESS))
                player.removePotionEffect(PotionEffectType.BLINDNESS);
            if (player.hasPotionEffect(PotionEffectType.CONFUSION))
                player.removePotionEffect(PotionEffectType.CONFUSION);
            if (player.hasPotionEffect(PotionEffectType.HUNGER))
                player.removePotionEffect(PotionEffectType.HUNGER);
            if (player.hasPotionEffect(PotionEffectType.POISON))
                player.removePotionEffect(PotionEffectType.POISON);
            if (player.hasPotionEffect(PotionEffectType.SLOW))
                player.removePotionEffect(PotionEffectType.SLOW);
            if (player.hasPotionEffect(PotionEffectType.SLOW_DIGGING))
                player.removePotionEffect(PotionEffectType.SLOW_DIGGING);
            if (player.hasPotionEffect(PotionEffectType.WEAKNESS))
                player.removePotionEffect(PotionEffectType.WEAKNESS);
            if (player.hasPotionEffect(PotionEffectType.WITHER))
                player.removePotionEffect(PotionEffectType.WITHER);
            player.sendMessage(Color.translate("&e&L/bless: &eYou have blessed all your negative debuffs."));


            Cooldowns.addCooldown("bless_cooldown", (Player) sender, 60);
        } else {
            player.sendMessage("No Perms.");
        }
        return true;
    }
}
