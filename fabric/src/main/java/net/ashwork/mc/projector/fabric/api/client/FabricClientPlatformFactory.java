package net.ashwork.mc.projector.fabric.api.client;

import net.ashwork.mc.projector.api.client.ClientModLoaderPlatform;
import net.ashwork.mc.projector.api.client.loader.AbstractClientPlatformFactory;

public class FabricClientPlatformFactory extends AbstractClientPlatformFactory {

    @Override
    protected ClientModLoaderPlatform createClientPlatform(String modId) {
        return new FabricClientLoaderPlatform(this, modId);
    }
}
