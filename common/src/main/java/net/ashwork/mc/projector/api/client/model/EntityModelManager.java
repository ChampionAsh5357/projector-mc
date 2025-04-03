package net.ashwork.mc.projector.api.client.model;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;

import java.util.function.Supplier;

public interface EntityModelManager {

    ModelLayerLocation modelLoc(String name, String layer);

    void registerModelLayer(ModelLayerLocation layer, Supplier<LayerDefinition> defn);
}
