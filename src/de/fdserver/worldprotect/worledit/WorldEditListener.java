package de.fdserver.worldprotect.worledit;

import com.sk89q.worldedit.entity.Player;
import com.sk89q.worldedit.event.extent.EditSessionEvent;
import com.sk89q.worldedit.extension.platform.Actor;
import com.sk89q.worldedit.util.eventbus.EventHandler;
import com.sk89q.worldedit.util.eventbus.Subscribe;

import java.beans.ConstructorProperties;

public class WorldEditListener {

    @ConstructorProperties({"plugin"})
    public WorldEditListener() {
    }

    @Subscribe(priority = EventHandler.Priority.VERY_EARLY)
    public void onEditSessionEvent(EditSessionEvent event) {
        Actor actor = event.getActor();
        if ((actor != null) && (actor.isPlayer()))
            event.setExtent(new ADE(event.getExtent(), (Player) actor));
    }
}
