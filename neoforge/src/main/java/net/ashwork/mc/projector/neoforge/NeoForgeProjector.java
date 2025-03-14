package net.ashwork.mc.projector.neoforge;

import net.ashwork.mc.projector.Projector;
import net.ashwork.mc.projector.ProjectorRef;
import net.neoforged.fml.common.Mod;

@Mod(ProjectorRef.ID)
public class NeoForgeProjector {

    public NeoForgeProjector() {
        Projector.init();
    }
}
