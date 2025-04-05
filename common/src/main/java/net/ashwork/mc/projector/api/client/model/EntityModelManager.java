package net.ashwork.mc.projector.api.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public interface EntityModelManager {

    ModelLayerLocation modelLoc(String name, String layer);

    <T extends EntityRenderState> void registerModel(ModelLayerLocation layer, Supplier<LayerDefinition> defn, Function<ModelPart, EntityModel<T>> factory, Consumer<Function<String, SyncParts>> syncParts);

    void registerEquipmentLayer(String name, EquipmentClientInfo.LayerType type, ModelLayerLocation layer);

    @Nullable
    <T extends EntityRenderState> ModelReference<T> getModel(ResourceKey<EquipmentAsset> asset, EquipmentClientInfo.LayerType type);

    interface ModelReference<T extends EntityRenderState> {

        EntityModel<T> model();

        default EntityModel<T> model(T renderState, Model parentModel) {
            return this.model();
        }
    }

    record SyncParts(ModelPart part, ModelPart parent) {}
}
