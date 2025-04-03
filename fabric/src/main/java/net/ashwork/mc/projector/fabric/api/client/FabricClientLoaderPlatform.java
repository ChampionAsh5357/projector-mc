package net.ashwork.mc.projector.fabric.api.client;

import net.ashwork.mc.projector.api.client.ClientModLoaderPlatform;
import net.ashwork.mc.projector.api.client.model.EntityModelManager;
import net.ashwork.mc.projector.api.loader.AbstractModLoaderPlatform;
import net.ashwork.mc.projector.fabric.api.client.model.FabricEntityModelManager;

public class FabricClientLoaderPlatform extends AbstractModLoaderPlatform implements ClientModLoaderPlatform {

    private final EntityModelManager entityModels;

    public FabricClientLoaderPlatform(String modId) {
        super(modId);
        this.entityModels = new FabricEntityModelManager(modId);
    }

    @Override
    public EntityModelManager entityModels() {
        return this.entityModels;
    }
}
