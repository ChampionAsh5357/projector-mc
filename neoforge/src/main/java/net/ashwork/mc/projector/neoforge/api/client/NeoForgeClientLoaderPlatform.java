package net.ashwork.mc.projector.neoforge.api.client;

import net.ashwork.mc.projector.api.client.ClientModLoaderPlatform;
import net.ashwork.mc.projector.api.client.model.EntityModelManager;
import net.ashwork.mc.projector.api.loader.AbstractModLoaderPlatform;
import net.ashwork.mc.projector.api.loader.reference.FactoryReferenceHolder;
import net.ashwork.mc.projector.neoforge.api.client.model.NeoForgeEntityModelManager;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;

public class NeoForgeClientLoaderPlatform extends AbstractModLoaderPlatform implements ClientModLoaderPlatform {

    private final EntityModelManager entityModels;

    public NeoForgeClientLoaderPlatform(FactoryReferenceHolder factory, String modId) {
        super(factory, modId);
        IEventBus modBus = ModList.get().getModContainerById(modId).orElseThrow().getEventBus();

        this.entityModels = new NeoForgeEntityModelManager(modId, modBus);
    }

    @Override
    public EntityModelManager entityModels() {
        return this.entityModels;
    }
}
