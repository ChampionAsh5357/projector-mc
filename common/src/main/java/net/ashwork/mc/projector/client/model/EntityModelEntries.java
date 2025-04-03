package net.ashwork.mc.projector.client.model;

import net.ashwork.mc.projector.api.client.model.EntityModelManager;
import net.ashwork.mc.projector.client.ProjectorClient;
import net.minecraft.client.model.geom.ModelLayerLocation;

public interface EntityModelEntries {

    ModelLayerLocation WITCH_HAT = ProjectorClient.PLATFORM.entityModels().modelLoc("witch_hat", "main");

    static void register(EntityModelManager entityModels) {
        entityModels.registerModelLayer(WITCH_HAT, WitchHatModel::createLayer);
    }
}
