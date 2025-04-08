package net.ashwork.mc.projector.fabric.api;

import net.ashwork.mc.projector.api.ModLoaderPlatform;
import net.ashwork.mc.projector.api.data.DataModLoaderPlatform;
import net.ashwork.mc.projector.api.loader.AbstractPlatformFactory;
import net.ashwork.mc.projector.fabric.api.data.FabricDataLoaderPlatform;

public class FabricPlatformFactory extends AbstractPlatformFactory {

    @Override
    protected ModLoaderPlatform createPlatform(String modId) {
        return new FabricLoaderPlatform(this, modId);
    }

    @Override
    protected DataModLoaderPlatform createDataPlatform(String modId) {
        return new FabricDataLoaderPlatform(this, modId);
    }
}
