package net.ashwork.mc.projector.neoforge.api;

import net.ashwork.mc.projector.api.ModLoaderPlatform;
import net.ashwork.mc.projector.api.data.DataModLoaderPlatform;
import net.ashwork.mc.projector.api.loader.PlatformFactory;
import net.ashwork.mc.projector.neoforge.api.data.NeoForgeDataLoaderPlatform;

public class NeoForgePlatformFactory implements PlatformFactory {

    @Override
    public ModLoaderPlatform common(String modId) {
        return new NeoForgeLoaderPlatform(modId);
    }

    @Override
    public DataModLoaderPlatform data(String modId) {
        return new NeoForgeDataLoaderPlatform(modId);
    }
}
