package me.siding.core.board;

import org.bukkit.entity.Player;

import java.util.List;

public interface AssembleAdapter {

    String getTitle(Player player);

    List<String> getLines(Player player);

}
