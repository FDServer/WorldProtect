package de.fdserver.worldprotect;

import org.bukkit.Material;

import java.util.HashMap;

public class WorldProtectConfig {

    private boolean actionbarMessages = true, breakAllowed = false, placeAllowed = false, interactAllowed = false, explodeAllowed = false, dropAllowed = false, damageAllowed = false;
    private final HashMap<Material, String> blocks = new HashMap<>();

    public WorldProtectConfig() {
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
        blocks.put(Material.BEACON, "Leuchtfeuer benutzen");
    }

    public boolean isActionbarMessages() {
        return actionbarMessages;
    }

    public void setActionbarMessages(boolean actionbarMessages) {
        this.actionbarMessages = actionbarMessages;
    }

    public boolean isBreakAllowed() {
        return breakAllowed;
    }

    public void setBreakAllowed(boolean breakAllowed) {
        this.breakAllowed = breakAllowed;
    }

    public boolean isPlaceAllowed() {
        return placeAllowed;
    }

    public void setPlaceAllowed(boolean placeAllowed) {
        this.placeAllowed = placeAllowed;
    }

    public boolean isInteractAllowed() {
        return interactAllowed;
    }

    public void setInteractAllowed(boolean interactAllowed) {
        this.interactAllowed = interactAllowed;
    }

    public boolean isExplodeAllowed() {
        return explodeAllowed;
    }

    public void setExplodeAllowed(boolean explodeAllowed) {
        this.explodeAllowed = explodeAllowed;
    }

    public boolean isDropAllowed() {
        return dropAllowed;
    }

    public void setDropAllowed(boolean dropAllowed) {
        this.dropAllowed = dropAllowed;
    }

    public boolean isDamageAllowed() {
        return damageAllowed;
    }

    public void setDamageAllowed(boolean damageAllowed) {
        this.damageAllowed = damageAllowed;
    }

    public HashMap<Material, String> getBlocks() {
        return blocks;
    }
}
