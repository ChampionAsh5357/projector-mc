package net.ashwork.mc.projector.fabric.api;

import net.ashwork.mc.projector.api.ModLoaderPlatform;
import net.ashwork.mc.projector.api.data.DataModLoaderPlatform;
import net.ashwork.mc.projector.api.loader.PlatformFactory;
import net.ashwork.mc.projector.fabric.api.data.FabricDataLoaderPlatform;

public class FabricPlatformFactory implements PlatformFactory {

    @Override
    public ModLoaderPlatform common(String modId) {
        return new FabricLoaderPlatform(modId);
    }

    @Override
    public DataModLoaderPlatform data(String modId) {
        return new FabricDataLoaderPlatform(modId);
    }
}
