package me.siding.core.listeners;

import org.arkhamnetwork.Arkkit.utils.WorldGuardUtils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityPortalEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NetherGlitchFixListener implements Listener {
    private final List<String> netherWorldNames = new ArrayList<>(Arrays.asList("world_nether"));

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPortal(PortalCreateEvent event) {
        Block block = event.getBlocks().get(0);
        World currentWorld = block.getWorld();
        if (!this.netherWorldNames.contains(currentWorld.getName()))
            return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onInteractLight(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        World currentWorld = player.getWorld();
        if (!this.netherWorldNames.contains(currentWorld.getName()))
            return;
        Block block = event.getClickedBlock();
        if (block == null || block.getType() == null || block.getType() != Material.OBSIDIAN)
            return;
        ItemStack itemInHand = player.getItemInHand();
        if (itemInHand == null || itemInHand.getType() == null || itemInHand.getType() != Material.FLINT_AND_STEEL)
            return;
        event.setCancelled(true);
        player.sendMessage(ChatColor.RED + "You may not make portals in the nether!");
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();
        World currentWorld = block.getWorld();
        Player player = event.getPlayer();
        if (!this.netherWorldNames.contains(currentWorld.getName()))
            return;
        if (block.getLocation().getY() < 127.0D)
            return;
        event.setCancelled(true);
        player.sendMessage(ChatColor.RED + "You may not build here!");
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onBedrockLevelBlockBreak(BlockBreakEvent e) {
        if (e.getBlock().getY() <= 0 && !e.getPlayer().isOp()) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "(!) " + ChatColor.RED + "You cannot break blocks at y:0 in the nether!");
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        World currentWorld = block.getWorld();
        Player player = event.getPlayer();
        if (!this.netherWorldNames.contains(currentWorld.getName()))
            return;
        if (block.getLocation().getY() < 127.0D)
            return;
        event.setCancelled(true);
        player.sendMessage(ChatColor.RED + "You may not build here!");
    }

    @EventHandler
    public void onPortalCreate(PortalCreateEvent e) {
        boolean atBedrock = false;
        for (Block b : e.getBlocks()) {
            if (b.getLocation().getY() <= 2.0D) {
                atBedrock = true;
                break;
            }
        }
        if (atBedrock)
            e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onEntityPortalEvent(EntityPortalEvent e) {
        if (e.getTo() != null && e.getTo().getWorld().getName().equals("world_nether")) {
            Location netherLocation = e.getTo();
            if (WorldGuardUtils.isPvPDisabled(netherLocation)) {
                if (e.getEntity() instanceof Player && (
                        (Player) e.getEntity()).isOp())
                    return;
                Bukkit.getLogger().info("[Arkkit (NetherGlitchFix)] Blocking portal creation event at: " + e.getTo() + " from entity: " + e.getEntity());
                e.getPortalTravelAgent().setCanCreatePortal(false);
            }
        }
    }
}
