package net.ashwork.mc.projector.api.loader;

import net.ashwork.mc.projector.api.ModLoaderPlatform;
import net.ashwork.mc.projector.api.data.DataModLoaderPlatform;

public interface PlatformFactory {

    ModLoaderPlatform common(String modId);

    DataModLoaderPlatform data(String modId);
}
