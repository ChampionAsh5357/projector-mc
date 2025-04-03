package net.ashwork.mc.projector.neoforge.api.client;

import net.ashwork.mc.projector.api.client.ClientModLoaderPlatform;
import net.ashwork.mc.projector.api.loader.AbstractModLoaderPlatform;

public class NeoForgeClientLoaderPlatform extends AbstractModLoaderPlatform implements ClientModLoaderPlatform {

    public NeoForgeClientLoaderPlatform(String modId) {
        super(modId);
    }
}
