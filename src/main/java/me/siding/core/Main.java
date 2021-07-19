package me.siding.core;

import io.netty.util.internal.ThreadLocalRandom;
import me.siding.core.board.Assemble;
import me.siding.core.board.AssembleStyle;
import me.siding.core.commands.*;
import me.siding.core.listeners.*;
import me.siding.core.utils.Color;
import me.siding.core.utils.Cooldowns;
import me.siding.core.utils.EnglishTimeZoneFormats;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Main extends JavaPlugin {

    public static Main plugin;
    public static AtomicInteger time = new AtomicInteger(60);
    public static List<String> balanceMessages;
    public static HashMap<UUID, Location> death_locations;
    private static final long MINUTE = TimeUnit.MINUTES.toMillis(1L);
    private static final long HOUR = TimeUnit.HOURS.toMillis(1L);
    private static Economy econ = null;
    private static Chat chat = null;
    public boolean disabling = false;
    private final List<String> commands = new ArrayList<>();
    private final List<String> lootbox = new ArrayList<>();


    public void onEnable() {
        Main.plugin = this;
        this.saveDefaultConfig();
        death_locations = new HashMap<>();

        //if(!new AdvancedLicense(getConfig().getString("KEY"), "https://versachetesting1234567.000webhostapp.com/verify.php", this).setSecurityKey("YecoF0I6M05thxLeokoHuW8iUhTdIUInjkfF").register()) return;
        this.disabling = false;
        registerCommands();
        registerListeners();
        if (this.getConfig().getBoolean("booleans.scoreboard")) {
            Assemble assemble = new Assemble(this, new FactionsAdapter());
            assemble.setTicks(2);
            assemble.setAssembleStyle(AssembleStyle.MODERN);
        }


        Cooldowns.createCooldown("bless_cooldown"); //helmet_swap
        Cooldowns.createCooldown("armor_swap");


        // <---Setting Up Economy--->
        if (!setupEconomy()) {
            Bukkit.broadcastMessage(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }


        setupChat();


        if (this.getConfig().getBoolean("booleans.lootbox_all")) {
            getServer().getScheduler().runTaskTimerAsynchronously(this, () ->
                    Bukkit.broadcastMessage(getConfig().getString("message")), 20 * 60 * 30, 20 * 60 * 30);

            getServer().getScheduler().runTaskTimerAsynchronously(this, () -> {
                time.set(time.get() - 1);
                // announce msg
                if (time.get() == 30) {
                    Bukkit.broadcastMessage(Color.translate(""));
                    Bukkit.broadcastMessage(Color.translate(""));
                    Bukkit.broadcastMessage(Color.translate("       &a&l✧&f&l✧&a&l✧ MYSTERY LOOTBOX ALL ✧&f&l✧&a&l✧       "));
                    Bukkit.broadcastMessage(Color.translate("     &7A &aMystery Lootbox all &7will be given to       "));
                    Bukkit.broadcastMessage(Color.translate("          &7&nALL PLAYERS&7 in &f30 second(s)&7.       "));
                    Bukkit.broadcastMessage(Color.translate(""));
                    Bukkit.broadcastMessage(Color.translate(""));
                }
                if (time.get() == 5) {
                    Bukkit.broadcastMessage(Color.translate(""));
                    Bukkit.broadcastMessage(Color.translate(""));
                    Bukkit.broadcastMessage(Color.translate("       &a&l✧&f&l✧&a&l✧ MYSTERY LOOTBOX ALL ✧&f&l✧&a&l✧       "));
                    Bukkit.broadcastMessage(Color.translate("     &7A &aMystery Lootbox all &7will be given to       "));
                    Bukkit.broadcastMessage(Color.translate("          &7&nALL PLAYERS&7 in &f5 second(s)&7.       "));
                    Bukkit.broadcastMessage(Color.translate(""));
                    Bukkit.broadcastMessage(Color.translate(""));
                }
                if (time.get() == 2) {
                    Bukkit.broadcastMessage(Color.translate(""));
                    Bukkit.broadcastMessage(Color.translate(""));
                    Bukkit.broadcastMessage(Color.translate("       &a&l✧&f&l✧&a&l✧ MYSTERY LOOTBOX ALL ✧&f&l✧&a&l✧       "));
                    Bukkit.broadcastMessage(Color.translate("     &7A &aMystery Lootbox all &7will be given to       "));
                    Bukkit.broadcastMessage(Color.translate("          &7&nALL PLAYERS&7 in &f2 second(s)&7.       "));
                    Bukkit.broadcastMessage(Color.translate(""));
                    Bukkit.broadcastMessage(Color.translate(""));
                }
                if (time.get() == 1) {
                    Bukkit.broadcastMessage(Color.translate(""));
                    Bukkit.broadcastMessage(Color.translate(""));
                    Bukkit.broadcastMessage(Color.translate("       &a&l✧&f&l✧&a&l✧ MYSTERY LOOTBOX ALL ✧&f&l✧&a&l✧       "));
                    Bukkit.broadcastMessage(Color.translate("     &7A &aMystery Lootbox all &7will be given to       "));
                    Bukkit.broadcastMessage(Color.translate("          &7&nALL PLAYERS&7 in &f1 second(s)&7.       "));
                    Bukkit.broadcastMessage(Color.translate(""));
                    Bukkit.broadcastMessage(Color.translate(""));
                }

                if (time.get() == 0) {
                    Bukkit.broadcastMessage(Color.translate(""));
                    Bukkit.broadcastMessage(Color.translate(""));
                    Bukkit.broadcastMessage(Color.translate("&a&l<!> &aA total of &f" + Bukkit.getServer().getOnlinePlayers().size() + "&a Player(s) have received a &FRANDOM LOOTBOX&a."));
                    Bukkit.broadcastMessage(Color.translate(""));
                    Bukkit.broadcastMessage(Color.translate(""));
                }

                //
                if (time.get() == 0) {
                    for (String key : getConfig().getConfigurationSection("commands").getKeys(false)) {

                        ConfigurationSection section = getConfig().getConfigurationSection("commands." + key);
                        int timesToAdd = Math.round(section.getInt("chance"));
                        lootbox.add(section.getString("lootbox"));

                        for (int i = 0; i < timesToAdd; i++) {
                            commands.add(section.getString("command"));
                        }
                    }

                    for (Player player : getServer().getOnlinePlayers()) {
                        String command = commands.get(ThreadLocalRandom.current().nextInt(commands.size()));
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", player.getName()));
                        //player.sendMessage("&e&l<!> &eYou have recieved: \"" +  "&e\" &eLootbox.");
                    }
                    time.set(this.getConfig().getInt("lootbox_all"));
                }
            }, 0L, 20L);
        }
    }

    private void registerListeners() {
        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new onJoinListener(), this);
        manager.registerEvents(new VoidListener(), this);
        manager.registerEvents(new AntiExploitListener(), this);

        manager.registerEvents(new NetherGlitchFixListener(), this);

        manager.registerEvents(new WorldListener(), this);
        manager.registerEvents(new PluginHiderListener(), this);
        manager.registerEvents(new AntiDupeListener(), this);
        manager.registerEvents(new ArrkitOneShotListener(), this);
        manager.registerEvents(new ArmorSwapListener(), this);
        manager.registerEvents(new LoreArmorEquipListener(), this);
        manager.registerEvents(new EssentialsListener(), this);
        manager.registerEvents(new RebootListener(), this);

        manager.registerEvents(new HostileMobsListener(), this);


        manager.registerEvents(new SpigotListener(), this);
        manager.registerEvents(new HotFixListener(), this);


        manager.registerEvents(new LightningEvent(), this);
        manager.registerEvents(new DeathListener(), this);
        manager.registerEvents(new ShortcutListener(), this);


        manager.registerEvents(new HeroicListener(), this);
        if (this.getConfig().getBoolean("booleans.crafting")) {
            manager.registerEvents(new AntiCraftListener(), this);
        }
        if (this.getConfig().getBoolean("booleans.flying")) {
            manager.registerEvents(new FlyingListener(), this);
            manager.registerEvents(new ParticlesListener(), this);
        }
    }

    private void registerCommands() {
        getCommand("deathpoint").setExecutor(new DeathPointCommand());

        getCommand("ping").setExecutor(new PingCommand());

        getCommand("balance").setExecutor(new BalanceCommand());
        getCommand("bless").setExecutor(new BlessCommand());
        getCommand("invsee").setExecutor(new InvseeCommand());
        getCommand("pots").setExecutor(new PotsCommand());
        getCommand("near").setExecutor(new NearCommand());
        getCommand("templar").setExecutor(new TemplarCommand());
        getCommand("store").setExecutor(new StoreCommand());
        getCommand("fade").setExecutor(new FadeCommand());

        getCommand("trash").setExecutor(new TrashCommand());
        getCommand("dupe").setExecutor(new DupeCommand());
        getCommand("website").setExecutor(new WebsiteCommand());
        getCommand("lag").setExecutor(new LagCommand());
        getCommand("lootboxall").setExecutor(new LootboxAllCommand());
        getCommand("discord").setExecutor(new DiscordCommand());
        getCommand("giveall").setExecutor(new GiveAllCommand());
        getCommand("broadcast").setExecutor(new BroadcastCommand());
    }

    public void onDisable() {
        death_locations.clear();
        this.disabling = true;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Main get() {
        return plugin;
    }

    public static Chat getChat() {
        return chat;
    }

    public static Economy getEconomy() {
        return econ;
    }

    public static List<String> getBalanceMessages() {
        return balanceMessages;
    }

    public static String getRemaining(long millis, boolean milliseconds) {
        return getRemaining(millis, milliseconds, true);
    }

    public static String getRemaining(long duration, boolean milliseconds, boolean trail) {
        if ((milliseconds) && (duration < MINUTE)) {
            return (trail ? EnglishTimeZoneFormats.REMAINING_SECONDS_TRAILING
                    : EnglishTimeZoneFormats.REMAINING_SECONDS).get().format(duration * 0.001D) + 's';
        }
        return DurationFormatUtils.formatDuration(duration, (duration >= HOUR ? "HH:" : "") + "mm:ss");
    }

    public static Plugin getInstance() {
        return Bukkit.getServer().getPluginManager().getPlugin("Templar");
    }
}
