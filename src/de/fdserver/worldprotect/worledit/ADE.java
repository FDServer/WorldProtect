package de.fdserver.worldprotect.worledit;

import com.sk89q.worldedit.extent.AbstractDelegateExtent;
import com.sk89q.worldedit.extent.Extent;
import com.sk89q.worldedit.world.block.BaseBlock;
import de.fdserver.worldprotect.Builder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class ADE extends AbstractDelegateExtent {

    protected final Player p;

    public ADE(Extent extent, com.sk89q.worldedit.entity.Player p) {
        super(extent);
        this.p = Bukkit.getPlayer(p.getUniqueId());
    }

    public boolean setBlock(Vector location, BaseBlock block) {
        return Builder.isBuilder(p);
    }
}
