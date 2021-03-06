package de.fdserver.worldprotect;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import de.fdserver.worldprotect.event.BuildModeEnterEvent;
import de.fdserver.worldprotect.event.BuildModeLeaveEvent;
import de.fdserver.worldprotect.worledit.WorldEditListener;
import de.myfdweb.minecraft.api.CoreAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class WorldProtect extends JavaPlugin implements Listener {

    public static final String PREFIX = CoreAPI.getPrefix("BauModus"), PERMISSION = "fdserver.build", PERM_ERROR = "§cI'm sorry, but you do not have permission to perfom this command. Please contact the server administrators if you believe that this is in error.";
    private static final WorldProtectConfig config = new WorldProtectConfig();

    public static WorldProtectConfig getWorldProtectConfig() {
        return config;
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new ProtectionListener(), this);
        WorldEditPlugin p = ((WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit"));
        if (p != null)
            p.getWorldEdit().getEventBus().register(new WorldEditListener());
        getCommand("build").setExecutor(this);
    }

    @Override
    public void onDisable() {
        for (Player p : new ArrayList<>(Builder.getBuilder()))
            Builder.removeBuilder(p);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("build")) {
            if (args.length == 0) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("§4Du musst ein Spieler sein um bauen zu können!");
                    return true;
                }
                Player p = (Player) sender;
                if (Builder.isBuilder(p)) {
                    BuildModeLeaveEvent e = new BuildModeLeaveEvent(p);
                    Bukkit.getPluginManager().callEvent(e);
                    if (!e.isCancelled())
                        Builder.removeBuilder(p);
                } else if (p.hasPermission(PERMISSION)) {
                    BuildModeEnterEvent e = new BuildModeEnterEvent(p);
                    Bukkit.getPluginManager().callEvent(e);
                    if (!e.isCancelled())
                        Builder.addBuilder(p);
                } else
                    p.sendMessage(PERM_ERROR);
            } else if (args.length == 1) {
                if (!sender.hasPermission(PERMISSION)) {
                    sender.sendMessage(PERM_ERROR);
                    return true;
                }
                Player p = Bukkit.getPlayer(args[0]);
                if (p == null) {
                    sender.sendMessage("§4Dieser Spieler ist offline!");
                    return true;
                }
                if (Builder.isBuilder(p)) {
                    BuildModeLeaveEvent e = new BuildModeLeaveEvent(p);
                    Bukkit.getPluginManager().callEvent(e);
                    if (!e.isCancelled())
                        Builder.removeBuilder(p);
                } else {
                    BuildModeEnterEvent e = new BuildModeEnterEvent(p);
                    Bukkit.getPluginManager().callEvent(e);
                    if (!e.isCancelled())
                        Builder.addBuilder(p);
                }
            } else
                sender.sendMessage(PREFIX + "§4Syntax: §a/build [Spieler]");
        }
        return true;
    }
}
