package de.fdserver.worldprotect;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Builder {

    private static ArrayList<Player> builder = new ArrayList<>();

    public static ArrayList<Player> getBuilder() {
        return builder;
    }

    private static void chatBuilder(String msg) {
        for (Player p : Bukkit.getOnlinePlayers())
            if (p.hasPermission(WorldProtect.PERMISSION) || isBuilder(p))
                p.sendMessage(WorldProtect.PREFIX + msg);
    }

    public static boolean isBuilder(Player p) {
        return builder.contains(p);
    }

    static void addBuilder(Player p) {
        builder.add(p);
        p.setGameMode(GameMode.CREATIVE);
        chatBuilder("§a+ " + p.getName());
    }

    static void removeBuilder(Player p) {
        chatBuilder("§c- " + p.getName());
        p.setGameMode(GameMode.ADVENTURE);
        p.setAllowFlight(p.hasPermission("fly.me"));
        builder.remove(p);
    }
}
