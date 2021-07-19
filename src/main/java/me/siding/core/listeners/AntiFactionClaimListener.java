package me.siding.core.listeners;

import com.massivecraft.factions.event.LandClaimEvent;
import me.siding.core.utils.Color;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class AntiFactionClaimListener implements Listener {
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onFactionClaim(LandClaimEvent event) {
        if (event.getPlayer() != null && !event.getPlayer().isOp()) {
            String worldName = event.getLocation().getWorldName();
            if (!worldName.equals("world") && !worldName.equals("world_nether") && !worldName.equals("world_event") && !worldName.equals("world_newdungeon")) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(Color.translate("&C&L<!>&c You cannot claim land in this world!"));
            }
        }
    }
}