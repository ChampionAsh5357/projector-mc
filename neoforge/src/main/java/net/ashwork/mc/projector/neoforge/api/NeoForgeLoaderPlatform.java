package net.ashwork.mc.projector.neoforge.api;

import net.ashwork.mc.projector.api.loader.AbstractModLoaderPlatform;
import net.ashwork.mc.projector.api.ModLoaderPlatform;
import net.ashwork.mc.projector.api.registry.Registrar;
import net.ashwork.mc.projector.neoforge.api.registry.NeoForgeRegistrar;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NeoForgeLoaderPlatform extends AbstractModLoaderPlatform implements ModLoaderPlatform {

    private final ModContainer container;

    public NeoForgeLoaderPlatform(final String modId) {
        super(modId);
        this.container = ModList.get().getModContainerById(modId).orElseThrow(() -> new IllegalStateException("How did this happen?"));
    }

    @Override
    public <REGISTRY> Registrar<REGISTRY> createRegistrar(ResourceKey<? extends Registry<REGISTRY>> registryKey) {
        var registrar = DeferredRegister.create(registryKey, this.modId());
        registrar.register(this.modBus());
        return new NeoForgeRegistrar<>(registrar);
    }

    private IEventBus modBus() {
        return this.container.getEventBus();
    }
}
