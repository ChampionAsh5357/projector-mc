package net.ashwork.mc.projector.api.client.loader;

import net.ashwork.mc.projector.api.client.ClientModLoaderPlatform;

public interface ClientPlatformFactory {

    ClientModLoaderPlatform client(String modId);
}
