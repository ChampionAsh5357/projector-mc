package net.ashwork.mc.projector.neoforge.api;

import net.ashwork.mc.projector.api.ModLoaderPlatform;
import net.ashwork.mc.projector.api.data.DataModLoaderPlatform;
import net.ashwork.mc.projector.api.loader.AbstractPlatformFactory;
import net.ashwork.mc.projector.api.loader.PlatformFactory;
import net.ashwork.mc.projector.neoforge.api.data.NeoForgeDataLoaderPlatform;

public class NeoForgePlatformFactory extends AbstractPlatformFactory {

    @Override
    public ModLoaderPlatform createPlatform(String modId) {
        return new NeoForgeLoaderPlatform(this, modId);
    }

    @Override
    public DataModLoaderPlatform createDataPlatform(String modId) {
        return new NeoForgeDataLoaderPlatform(this, modId);
    }
}
