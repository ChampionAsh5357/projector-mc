package net.ashwork.mc.projector.neoforge.api.client.model;

import net.ashwork.mc.projector.api.client.model.AbstractEntityModelManager;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class NeoForgeEntityModelManager extends AbstractEntityModelManager {

    public NeoForgeEntityModelManager(String modId, IEventBus modBus) {
        super(modId);
        modBus.addListener(this::registerLayerDefinitions);
        modBus.addListener(this::resolveModels);
    }

    private void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        this.modelReferences.forEach((layer, reference) -> event.registerLayerDefinition(layer, reference.getDefinition()));
    }

    private void resolveModels(EntityRenderersEvent.AddLayers event) {
        this.modelReferences.forEach((layer, reference) -> reference.resolveModel(layer, event.getEntityModels()));
    }
}
