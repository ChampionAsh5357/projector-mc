package net.ashwork.mc.projector.fabric.api.client;

import net.ashwork.mc.projector.api.client.ClientModLoaderPlatform;
import net.ashwork.mc.projector.api.loader.AbstractModLoaderPlatform;

public class FabricClientLoaderPlatform extends AbstractModLoaderPlatform implements ClientModLoaderPlatform {

    public FabricClientLoaderPlatform(String modId) {
        super(modId);
    }
}
