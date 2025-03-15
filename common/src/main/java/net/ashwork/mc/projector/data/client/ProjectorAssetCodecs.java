package net.ashwork.mc.projector.data.client;

import net.ashwork.mc.projector.api.data.provider.ResourceKeyOutput;
import net.ashwork.mc.projector.data.ProjectorData;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.world.item.equipment.EquipmentAsset;

public interface ProjectorAssetCodecs {

    static void buildEquipment(ResourceKeyOutput<EquipmentAsset, EquipmentClientInfo> output) {
        output.accept("witch_hat", EquipmentClientInfo.builder()
                .addMainHumanoidLayer(ProjectorData.PLATFORM.modLoc("witch_hat"), false)
                .build()
        );
    }
}
