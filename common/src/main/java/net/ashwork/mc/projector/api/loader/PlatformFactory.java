package net.ashwork.mc.projector.api.loader;

import net.ashwork.mc.projector.api.ModLoaderPlatform;
import net.ashwork.mc.projector.api.data.DataModLoaderPlatform;
import net.ashwork.mc.projector.api.util.PlatformHandler;

public interface PlatformFactory {

    PlatformFactory INSTANCE = PlatformHandler.loadFirst(PlatformFactory.class);

    ModLoaderPlatform common(String modId);

    DataModLoaderPlatform data(String modId);
}
