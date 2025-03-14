package net.ashwork.mc.projector.fabric.data;

import net.ashwork.mc.projector.data.ProjectorData;
import net.ashwork.mc.projector.fabric.api.data.FabricDataLoaderPlatform;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class FabricProjectorData implements DataGeneratorEntrypoint {

    private final FabricDataLoaderPlatform platform;

    public FabricProjectorData() {
        ProjectorData.init();

        this.platform = (FabricDataLoaderPlatform) ProjectorData.PLATFORM;
    }

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        this.platform.initializeDataGenerator(fabricDataGenerator);
    }
}
