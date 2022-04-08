package de.fdserver.worldprotect;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;

public class ProtectionListener implements Listener {

    private static String getError(Material m) {
        HashMap<Material, String> blocks = WorldProtect.getWorldProtectConfig().getBlocks();
        if (!blocks.containsKey(m))
            return null;
        return "§4Du darfst hier keine " + blocks.get(m) + "!";
    }

    // Listener, die Permissions prüfen

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (!Builder.isBuilder(e.getPlayer()) && !WorldProtect.getWorldProtectConfig().isBreakAllowed()) {
            e.setCancelled(true);
            if (WorldProtect.getWorldProtectConfig().isActionbarMessages())
                e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§4Du darfst hier keine Blöcke abbauen!"));
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if (!Builder.isBuilder(e.getPlayer()) && !WorldProtect.getWorldProtectConfig().isPlaceAllowed()) {
            e.setCancelled(true);
            if (WorldProtect.getWorldProtectConfig().isActionbarMessages())
                e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§4Du darfst hier keine Blöcke setzen!"));
        }
    }

    @EventHandler
    public void onExplode(EntityExplodeEvent e) {
        if (!WorldProtect.getWorldProtectConfig().isExplodeAllowed())
            e.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if (!Builder.isBuilder(e.getPlayer()) && !WorldProtect.getWorldProtectConfig().isDropAllowed()) {
            e.setCancelled(true);
            if (WorldProtect.getWorldProtectConfig().isActionbarMessages())
                e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§4Du darfst hier keine Items droppen!"));
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            if (!Builder.isBuilder(e.getPlayer()) && WorldProtect.getWorldProtectConfig().getBlocks().containsKey(e.getClickedBlock().getType())) {
                e.setCancelled(true);
                if (WorldProtect.getWorldProtectConfig().isActionbarMessages())
                    e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(getError(e.getClickedBlock().getType())));
            }
            if (e.getItem() != null && !Builder.isBuilder(e.getPlayer()) && !WorldProtect.getWorldProtectConfig().isInteractAllowed()) {
                Material m = e.getItem().getType();
                if (m.name().endsWith("SPAWN_EGG") || m.equals(Material.ARMOR_STAND) || m.equals(Material.PAINTING) || m.equals(Material.ITEM_FRAME) || m.equals(Material.END_CRYSTAL)) {
                    e.setCancelled(true);
                    if (WorldProtect.getWorldProtectConfig().isActionbarMessages())
                        e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§4Du darfst hier kein Entity spawnen!"));
                } else if (m.equals(Material.LAVA_BUCKET)
                        || m.equals(Material.WATER_BUCKET)) {
                    e.setCancelled(true);
                    if (WorldProtect.getWorldProtectConfig().isActionbarMessages())
                        e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§4Du darfst hier keine Blöcke setzen!"));
                } else if (m.equals(Material.BUCKET)) {
                    e.setCancelled(true);
                    if (WorldProtect.getWorldProtectConfig().isActionbarMessages())
                        e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§4Du darfst hier keine Blöcke abbauen!"));
                } else if (m.equals(Material.FLINT_AND_STEEL)) {
                    e.setCancelled(true);
                    if (WorldProtect.getWorldProtectConfig().isActionbarMessages())
                        e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§4Du darfst hier kein Feuerzeug verwenden!"));
                }
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEntityEvent e) {
        if (e.getRightClicked().getType().equals(EntityType.ITEM_FRAME) && !Builder.isBuilder(e.getPlayer()) && !WorldProtect.getWorldProtectConfig().isPlaceAllowed()) {
            e.setCancelled(true);
            if (WorldProtect.getWorldProtectConfig().isActionbarMessages())
                e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§4Du darfst hier keine Bilderrahmen verwenden!"));
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (!e.getDamager().getType().equals(EntityType.PLAYER))
            return;
        if (!Builder.isBuilder((Player) e.getDamager()) && !WorldProtect.getWorldProtectConfig().isDamageAllowed()) {
            e.setCancelled(true);
            if (WorldProtect.getWorldProtectConfig().isActionbarMessages())
                ((Player) e.getDamager()).spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§4Gewalt ist die Sprache der Dummen!"));
        }
    }

    @EventHandler
    public void onDestroyPainting(HangingBreakByEntityEvent e) {
        if (!e.getRemover().getType().equals(EntityType.PLAYER)) {
            e.setCancelled(true);
            return;
        }
        if (!Builder.isBuilder((Player) e.getRemover()) && !WorldProtect.getWorldProtectConfig().isBreakAllowed()) {
            e.setCancelled(true);
            if (WorldProtect.getWorldProtectConfig().isActionbarMessages())
                ((Player) e.getRemover()).spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§4Du darfst hier keine Bilderrahmen zerstören."));
        }
    }

    @EventHandler
    public void onFade(EntityChangeBlockEvent e) {
        if (e.getBlock().getType().equals(Material.FARMLAND))
            if (!e.getEntity().getType().equals(EntityType.PLAYER) || (!Builder.isBuilder((Player) e.getEntity()) && (!WorldProtect.getWorldProtectConfig().isBreakAllowed() || !WorldProtect.getWorldProtectConfig().isPlaceAllowed())))
                e.setCancelled(true);
    }

    @EventHandler
    public void onInteractFlowerPot(PlayerInteractEvent e) {
        if (e.getClickedBlock() != null && e.getClickedBlock().getType().equals(Material.FLOWER_POT) && !Builder.isBuilder(e.getPlayer()) && !WorldProtect.getWorldProtectConfig().isPlaceAllowed()) {
            e.setCancelled(true);
            e.getClickedBlock().getState().update();
            if (WorldProtect.getWorldProtectConfig().isActionbarMessages())
                e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§4Du darfst hier keine Blumen klauen!"));
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if (Builder.isBuilder(e.getPlayer()))
            Builder.removeBuilder(e.getPlayer());
    }
}
