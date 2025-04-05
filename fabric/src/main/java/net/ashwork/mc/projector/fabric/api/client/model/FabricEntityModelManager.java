package net.ashwork.mc.projector.fabric.api.client.model;

import net.ashwork.mc.projector.api.client.model.AbstractEntityModelManager;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.state.EntityRenderState;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class FabricEntityModelManager extends AbstractEntityModelManager {

    public FabricEntityModelManager(String modId) {
        super(modId);
    }

    @Override
    public <T extends EntityRenderState> void registerModel(ModelLayerLocation layer, Supplier<LayerDefinition> defn, Function<ModelPart, EntityModel<T>> factory, Consumer<Function<String, SyncParts>> syncParts) {
        super.registerModel(layer, defn, factory, syncParts);
        EntityModelLayerRegistry.registerModelLayer(layer, defn::get);
    }

    public void resolveModels(EntityModelSet modelSet) {
        this.modelReferences.forEach((layer, reference) -> reference.resolveModel(layer, modelSet));
    }
}
