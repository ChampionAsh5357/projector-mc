package net.ashwork.mc.projector.fabric.client;

import net.ashwork.mc.projector.client.ProjectorClient;
import net.fabricmc.api.ClientModInitializer;

public class FabricProjectorClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ProjectorClient.init();
    }
}
