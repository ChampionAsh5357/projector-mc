package net.ashwork.mc.projector.fabric.api.client.model;

import net.ashwork.mc.projector.api.client.model.EntityModelManager;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public class FabricEntityModelManager implements EntityModelManager {

    private final String modId;

    public FabricEntityModelManager(String modId) {
        this.modId = modId;
    }

    @Override
    public ModelLayerLocation modelLoc(String name, String layer) {
        return new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(this.modId, name), layer);
    }

    @Override
    public void registerModelLayer(ModelLayerLocation layer, Supplier<LayerDefinition> defn) {
        EntityModelLayerRegistry.registerModelLayer(layer, defn::get);
    }
}
