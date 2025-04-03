package net.ashwork.mc.projector.neoforge.api.client;

import net.ashwork.mc.projector.api.client.ClientModLoaderPlatform;
import net.ashwork.mc.projector.api.client.loader.AbstractClientPlatformFactory;

public class NeoForgeClientPlatformFactory extends AbstractClientPlatformFactory {

    @Override
    protected ClientModLoaderPlatform createClientPlatform(String modId) {
        return new NeoForgeClientLoaderPlatform(modId);
    }
}
