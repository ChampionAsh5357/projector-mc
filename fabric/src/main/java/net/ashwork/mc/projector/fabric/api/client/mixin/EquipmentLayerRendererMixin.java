package net.ashwork.mc.projector.fabric.api.client.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.mojang.blaze3d.vertex.PoseStack;
import net.ashwork.mc.projector.api.client.loader.ClientGlobalKeys;
import net.ashwork.mc.projector.api.client.loader.ClientPlatformFactory;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.EquipmentLayerRenderer;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.EquipmentAsset;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EquipmentLayerRenderer.class)
public class EquipmentLayerRendererMixin {

    @WrapMethod(method = "renderLayers(Lnet/minecraft/client/resources/model/EquipmentClientInfo$LayerType;Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/client/model/Model;Lnet/minecraft/world/item/ItemStack;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/resources/ResourceLocation;)V")
    private void setModel(EquipmentClientInfo.LayerType layerType, ResourceKey<EquipmentAsset> equipmentAsset, Model armorModel, ItemStack item, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, ResourceLocation playerTexture, Operation<Void> original) {
        var modelReference = ClientPlatformFactory.INSTANCE.findFirstNotNull(platform -> platform.entityModels().getModel(equipmentAsset, layerType));
        if (modelReference != null) {
            armorModel = modelReference.model(ClientPlatformFactory.INSTANCE.get(ClientGlobalKeys.ENTITY_STATE), ClientPlatformFactory.INSTANCE.get(ClientGlobalKeys.PARENT_MODEL));
        }
        original.call(layerType, equipmentAsset, armorModel, item, poseStack, bufferSource, packedLight, playerTexture);
    }
}
