package net.ashwork.mc.projector.neoforge.client;

import net.ashwork.mc.projector.ProjectorRef;
import net.ashwork.mc.projector.client.ProjectorClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = ProjectorRef.ID, dist = Dist.CLIENT)
public class NeoForgeProjectorClient {

    public NeoForgeProjectorClient() {
        ProjectorClient.init();
    }
}
