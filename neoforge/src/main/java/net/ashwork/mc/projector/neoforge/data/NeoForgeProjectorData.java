package net.ashwork.mc.projector.neoforge.data;

import net.ashwork.mc.projector.ProjectorRef;
import net.ashwork.mc.projector.data.ProjectorData;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(ProjectorRef.ID)
public class NeoForgeProjectorData {

    public NeoForgeProjectorData(IEventBus modBus) {
        ProjectorData.init();

        modBus.register(ProjectorData.PLATFORM);
    }
}
