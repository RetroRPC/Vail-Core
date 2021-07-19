package me.siding.core;


import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;
import me.siding.core.board.AssembleAdapter;
import me.siding.core.utils.Color;
import me.siding.core.utils.NumberUtils;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class FactionsAdapter implements AssembleAdapter {


    // <---Events--->
    Economy economy = Main.getEconomy();
    Faction faction = null;

    // <---EXPERIENCE--->
    private static int getExpAtLevel(Player player) {
        return getExpAtLevel(player.getLevel());
    }

    public static int getExpAtLevel(int level) {
        if (level > 29)
            return 62 + (level - 30) * 7;
        if (level > 15)
            return 17 + (level - 15) * 3;
        return 17;
    }

    public static int getExpToLevel(int level) {
        int currentLevel = 0;
        int exp = 0;
        while (currentLevel < level) {
            exp += getExpAtLevel(currentLevel);
            currentLevel++;
        }
        if (exp < 0)
            exp = Integer.MAX_VALUE;
        return exp;
    }

    public static int getTotalExperience(Player player) {
        int exp = Math.round(getExpAtLevel(player) * player.getExp());
        int currentLevel = player.getLevel();
        while (currentLevel > 0) {
            currentLevel--;
            exp += getExpAtLevel(currentLevel);
        }
        if (exp < 0)
            exp = Integer.MAX_VALUE;
        return exp;
    }

    @Override
    public String getTitle(Player player) {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd");

        return Color.translate("&a&lVail&f&lPvP &7" + format.format(Long.valueOf(System.currentTimeMillis())));
    }

    @Override
    public List<String> getLines(Player player) {
        List<String> toReturn = new ArrayList<>();

        FPlayer fplayer = FPlayers.getInstance().getByPlayer(player);


        //toReturn.add("&7&o       OP Factions"); //TODO: Make a util that centers text
        toReturn.add("");
        toReturn.add("&c&lAccount");
        toReturn.add("&f" + player.getName() + " &7[" + fplayer.getFaction().getKills() + ":" + fplayer.getFaction().getDeaths() + "]");
        toReturn.add("");
        toReturn.add("&6&lBalance");
        toReturn.add("$" + NumberUtils.formatMoney((long) Main.getEconomy().getBalance(player)));
        toReturn.add("");
        toReturn.add("&a&lExperience");
        toReturn.add(NumberUtils.formatMoney(getTotalExperience(player)));
        toReturn.add("");
        toReturn.add("&b&lFaction");
        toReturn.add("&a" + fplayer.getFaction().getTag() + " &7[" + fplayer.getFaction().getPowerRounded() + "/" + fplayer.getFaction().getPowerMaxRounded() + "]");
        toReturn.add("");
        toReturn.add("&7&Oplay.vailpvp.org     ");

        return toReturn;
    }

}