package net.ashwork.mc.projector.client.model;

import net.ashwork.mc.projector.api.client.model.EntityModelManager;
import net.ashwork.mc.projector.api.client.model.geom.ModelPartExtension;
import net.ashwork.mc.projector.client.ProjectorClient;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.resources.model.EquipmentClientInfo;

public interface EntityModelEntries {

    ModelLayerLocation WITCH_HAT = ProjectorClient.PLATFORM.entityModels().modelLoc("witch_hat", "main");

    static void register(EntityModelManager entityModels) {
        entityModels.registerModel(WITCH_HAT, WitchHatModel::createLayer, WitchHatModel::new, syncer -> {
            var parts = syncer.apply("head");
            if (parts != null) {
                parts.part().getChild("layer0").y = ((ModelPartExtension) (Object) parts.parent()).getBounds()[1];
            }
        });
        entityModels.registerEquipmentLayer("witch_hat", EquipmentClientInfo.LayerType.HUMANOID, WITCH_HAT);
    }
}
