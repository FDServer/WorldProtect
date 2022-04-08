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

    private static HashMap<Material, String> blocks = new HashMap<>();

    static void init() {
        blocks.put(Material.CHEST, "Kisten öffnen");
        blocks.put(Material.DISPENSER, "Werfer benutzen");
        blocks.put(Material.NOTE_BLOCK, "Notenblöcke verändern");
        blocks.put(Material.TNT, "TNTs benutzen");
        blocks.put(Material.CRAFTING_TABLE, "Werkbank benutzen");
        blocks.put(Material.FURNACE, "Öfen benutzen");
        blocks.put(Material.LEVER, "Hebel benutzen");
        blocks.put(Material.STONE_BUTTON, "Steinknöpfe benutzen");
        blocks.put(Material.JUKEBOX, "Schallplatenspieler benutzen");
        //blocks.put(Material.TRAP_DOOR, "Falltüren öffnen");
        blocks.put(Material.ENCHANTING_TABLE, "Zaubertische benutzen");
        blocks.put(Material.END_PORTAL_FRAME, "Endportalrahmen benutzen");
        blocks.put(Material.ENDER_CHEST, "Enderkisten öffnen");
        //blocks.put(Material.WOOD_BUTTON, "Holzknöpfe benutzen");
        blocks.put(Material.ANVIL, "Ambosse benutzen");
        blocks.put(Material.TRAPPED_CHEST, "Redstonetruhen öffnen");
        blocks.put(Material.DAYLIGHT_DETECTOR, "Tageslichtsensoren verstellen");
        blocks.put(Material.HOPPER, "Trichter öffnen");
        blocks.put(Material.DROPPER, "Spender öffnen");
        //blocks.put(Material.FENCE_GATE, "Zauntore öffnen");
        blocks.put(Material.ACACIA_FENCE_GATE, "Akazienholzzauntore öffnen");
        blocks.put(Material.BIRCH_FENCE_GATE, "Birkenholzzauntore öffnen");
        blocks.put(Material.DARK_OAK_FENCE_GATE, "Schwarzeichenholzzauntore öffnen");
        blocks.put(Material.JUNGLE_FENCE_GATE, "Tropenholzzauntore öffnen");
        blocks.put(Material.SPRUCE_FENCE_GATE, "Fichtenholzzauntore öffnen");
        blocks.put(Material.OBSERVER, "Beobachter benutzen");
        blocks.put(Material.BLACK_SHULKER_BOX, "schwarzen Shulkerboxen öffnen");
        blocks.put(Material.BLUE_SHULKER_BOX, "blauen Shulkerboxen öffnen");
        blocks.put(Material.BROWN_SHULKER_BOX, "braunen Shulkerboxen öffnen");
        blocks.put(Material.CYAN_SHULKER_BOX, "türkisen Shulkerboxen öffnen");
        blocks.put(Material.GRAY_SHULKER_BOX, "grauen Shulkerboxen öffnen");
        blocks.put(Material.GREEN_SHULKER_BOX, "grünen Shulkerboxen öffnen");
        blocks.put(Material.LIGHT_BLUE_SHULKER_BOX, "hellblauen Shulkerboxen öffnen");
        blocks.put(Material.LIME_SHULKER_BOX, "hellgrünen Shulkerboxen öffnen");
        blocks.put(Material.MAGENTA_SHULKER_BOX, "magentanen Shulkerboxen öffnen");
        blocks.put(Material.ORANGE_SHULKER_BOX, "orangenen Shulkerboxen öffnen");
        blocks.put(Material.PINK_SHULKER_BOX, "rosanen Shulkerboxen öffnen");
        blocks.put(Material.PURPLE_SHULKER_BOX, "lilanen Shulkerboxen öffnen");
        blocks.put(Material.RED_SHULKER_BOX, "roten Shulkerboxen öffnen");
        blocks.put(Material.LIGHT_GRAY_SHULKER_BOX, "silbernen Shulkerboxen öffnen");
        blocks.put(Material.WHITE_SHULKER_BOX, "weißen Shulkerboxen öffnen");
        blocks.put(Material.YELLOW_SHULKER_BOX, "gelben Shulkerboxen öffnen");
        //blocks.put(Material.BED, "Betten benutzen");
        blocks.put(Material.REPEATER, "Redstone-Repeater verstellen");
        blocks.put(Material.CAULDRON, "Kessel benutzen");
        blocks.put(Material.COMPARATOR, "Redstone-Komparatoren verstellen");
        blocks.put(Material.CAKE, "Kuche essen");
        blocks.put(Material.BREWING_STAND, "Braustände benutzen");
        blocks.put(Material.ACACIA_DOOR, "Akazienholztüren öffnen");
        blocks.put(Material.BIRCH_DOOR, "Birkenholztüren öffnen");
        blocks.put(Material.DARK_OAK_DOOR, "Schwarzeichenholztüren öffnen");
        blocks.put(Material.JUNGLE_DOOR, "Tropenholztüren öffnen");
        blocks.put(Material.SPRUCE_DOOR, "Fichtenholztüren öffnen");
        //blocks.put(Material.WOODEN_DOOR, "Türen öffnen");
        blocks.put(Material.DRAGON_EGG, "Dracheneier berühren");
    }

    private static String getError(Material m) {
        if (!blocks.containsKey(m))
            return null;
        return "§4Du darfst hier keine " + blocks.get(m) + "!";
    }

    // Listener, die Permissions prüfen

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (!Builder.isBuilder(e.getPlayer())) {
            e.setCancelled(true);
            e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§4Du darfst hier keine Blöcke abbauen!"));
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if (!Builder.isBuilder(e.getPlayer())) {
            e.setCancelled(true);
            e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§4Du darfst hier keine Blöcke setzen!"));
        }
    }

    @EventHandler
    public void onExplode(EntityExplodeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if (!Builder.isBuilder(e.getPlayer())) {
            e.setCancelled(true);
            e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§4Du darfst hier keine Items droppen!"));
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            if (!Builder.isBuilder(e.getPlayer()) && blocks.containsKey(e.getClickedBlock().getType())) {
                e.setCancelled(true);
                e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(getError(e.getClickedBlock().getType())));
            }
            if (e.getItem() != null && !Builder.isBuilder(e.getPlayer())) {
                Material m = e.getItem().getType();
                if (m.name().endsWith("SPAWN_EGG") || m.equals(Material.ARMOR_STAND) || m.equals(Material.PAINTING) || m.equals(Material.ITEM_FRAME) || m.equals(Material.END_CRYSTAL)) {
                    e.setCancelled(true);
                    e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§4Du darfst hier kein Entity spawnen!"));
                } else if (m.equals(Material.LAVA_BUCKET)
                        || m.equals(Material.WATER_BUCKET)) {
                    e.setCancelled(true);
                    e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§4Du darfst hier keine Blöcke setzen!"));
                } else if (m.equals(Material.BUCKET)) {
                    e.setCancelled(true);
                    e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§4Du darfst hier keine Blöcke abbauen!"));
                } else if (m.equals(Material.FLINT_AND_STEEL)) {
                    e.setCancelled(true);
                    e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§4Du darfst hier kein Feuerzeug verwenden!"));
                }
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEntityEvent e) {
        if (e.getRightClicked().getType().equals(EntityType.ITEM_FRAME)) {
            if (!Builder.isBuilder(e.getPlayer())) {
                e.setCancelled(true);
                e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§4Du darfst hier keine Bilderrahmen verwenden!"));
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (!e.getDamager().getType().equals(EntityType.PLAYER))
            return;
        if (!Builder.isBuilder((Player) e.getDamager())) {
            e.setCancelled(true);
            ((Player) e.getDamager()).spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent());
        }
    }

    @EventHandler
    public void onFade(EntityChangeBlockEvent e) {
        if (e.getBlock().getType().equals(Material.FARMLAND))
            if (!e.getEntity().getType().equals(EntityType.PLAYER) || !Builder.isBuilder((Player) e.getEntity()))
                e.setCancelled(true);
    }

    @EventHandler
    public void onDestroyPainting(HangingBreakByEntityEvent e) {
        if (!e.getRemover().getType().equals(EntityType.PLAYER)) {
            e.setCancelled(true);
            return;
        }
        if (!Builder.isBuilder((Player) e.getRemover())) {
            e.setCancelled(true);
            ((Player) e.getRemover()).spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent());
        }
    }

    @EventHandler
    public void onInteractFlowerPot(PlayerInteractEvent e) {
        if (e.getClickedBlock() != null)
            if (e.getClickedBlock().getType().equals(Material.FLOWER_POT))
                if (!Builder.isBuilder(e.getPlayer())) {
                    e.setCancelled(true);
                    e.getClickedBlock().getState().update();
                    e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§4Du darfst hier keine Blumen klauen!"));
                }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if (Builder.isBuilder(e.getPlayer()))
            Builder.removeBuilder(e.getPlayer());
    }
}
