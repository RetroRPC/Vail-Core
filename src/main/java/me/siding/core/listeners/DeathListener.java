package me.siding.core.listeners;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.struct.Relation;
import me.siding.core.Main;
import me.siding.core.utils.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.ItemStack;


public class DeathListener implements Listener {


    @EventHandler
    public void onKill(PlayerDeathEvent e) {


        Main.death_locations.put(e.getEntity().getUniqueId(), e.getEntity().getLocation());
        Player player = e.getEntity().getPlayer();
        Player killer = e.getEntity().getPlayer().getKiller();

        ItemStack it = killer.getItemInHand();
        String itemName;
        if (it == null || it.getType() == Material.AIR) {
            itemName = "their bare hands";
        } else {
            itemName = it.getItemMeta().getDisplayName();
            if (itemName == null)
                itemName = it.getType().toString().replace("_", " ").toUpperCase();
        }


        FPlayer fPlr = FPlayers.getInstance().getByPlayer(player);
        FPlayer fPL = FPlayers.getInstance().getByPlayer(player);
        int x = player.getLocation().getBlockX();
        int z = player.getLocation().getBlockZ();
        int y = player.getLocation().getBlockY();
        String plrname = killer.getName();

        if (fPL.getRelationTo(fPlr) == Relation.TRUCE) {
            plrname = "&b";
        } else if (fPL.getRelationTo(fPlr) == Relation.ALLY) {
            plrname = "&d";
        } else if (fPL.getRelationTo(fPlr) == Relation.ENEMY) {
            plrname = "&c";
        } else if (fPL.getRelationTo(fPlr) == Relation.NEUTRAL) {
            plrname = "&f";
        } else if (fPL.getRelationTo(fPlr) == Relation.MEMBER) {
            plrname = "&a";
        } else {
            plrname = "&2";
        }


        e.setDeathMessage(null);
        e.setDeathMessage(Color.translate(plrname + "❋ &7" + plrname + killer.getName() + " &7killed  " + plrname + player.getName() + " &7using &r" + itemName));


        if (player.getWorld().getName().equals("world_the_end")) {
            player.sendMessage("");
            player.sendMessage(Color.translate("&c&lYOU DIED AT &7[" + x + "x, " + y + "y, " + z + "z]"));
            player.sendMessage(Color.translate("&cYou cannot return back to the END DIMENSION."));
            player.sendMessage("");
            player.playSound(player.getLocation(), Sound.WITHER_IDLE, 1.0F, 1.0F);
        } else {

            player.sendMessage("");
            player.sendMessage(Color.translate("&c&lYOU DIED AT &7[" + x + "x, " + y + "y, " + z + "z]"));
            player.sendMessage(Color.translate("&cType &l/back&c to return to your previous death location."));
            player.sendMessage("");
        }

        //  p.setVelocity(new Vector(0, 0, 0));
        // p.spigot().respawn();


        if (killer == null) {
            return;
        } else {
            killer.playSound(killer.getLocation(), Sound.ORB_PICKUP, 1.0F, 1.0F);
        }


    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerCommandPreProcess(PlayerCommandPreprocessEvent e) {
        if (e.getMessage().toLowerCase().startsWith("/back") || e.getMessage().toLowerCase().startsWith("/return")) {
            e.setCancelled(true);
            e.getPlayer().performCommand("deathpoint");
        }
    }


}
