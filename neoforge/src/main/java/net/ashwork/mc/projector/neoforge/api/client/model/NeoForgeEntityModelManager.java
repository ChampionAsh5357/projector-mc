package net.ashwork.mc.projector.neoforge.api.client.model;

import net.ashwork.mc.projector.api.client.model.EntityModelManager;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class NeoForgeEntityModelManager implements EntityModelManager {

    private final String modId;
    private final List<Consumer<EntityRenderersEvent.RegisterLayerDefinitions>> registerLayers;

    public NeoForgeEntityModelManager(String modId, IEventBus modBus) {
        this.modId = modId;
        this.registerLayers = new ArrayList<>();
        modBus.addListener(this::registerLayerDefinitions);
    }

    @Override
    public ModelLayerLocation modelLoc(String name, String layer) {
        return new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(this.modId, name), layer);
    }

    @Override
    public void registerModelLayer(ModelLayerLocation layer, Supplier<LayerDefinition> defn) {
        this.registerLayers.add(event -> event.registerLayerDefinition(layer, defn));
    }

    private void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        this.registerLayers.forEach(action -> action.accept(event));
    }
}
