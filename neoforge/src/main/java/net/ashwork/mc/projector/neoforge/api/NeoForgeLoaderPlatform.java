package net.ashwork.mc.projector.neoforge.api;

import net.ashwork.mc.projector.api.loader.AbstractModLoaderPlatform;
import net.ashwork.mc.projector.api.ModLoaderPlatform;
import net.ashwork.mc.projector.api.loader.reference.FactoryReferenceHolder;
import net.ashwork.mc.projector.api.registry.RegistrarPlatform;
import net.ashwork.mc.projector.api.registry.types.Registrar;
import net.ashwork.mc.projector.neoforge.api.registry.NeoForgeRegistrarPlatform;
import net.ashwork.mc.projector.neoforge.api.registry.types.NeoForgeRegistrar;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NeoForgeLoaderPlatform extends AbstractModLoaderPlatform implements ModLoaderPlatform {

    private final ModContainer container;
    private final RegistrarPlatform registrar;

    public NeoForgeLoaderPlatform(FactoryReferenceHolder factory, String modId) {
        super(factory, modId);
        this.container = ModList.get().getModContainerById(modId).orElseThrow(() -> new IllegalStateException("How did this happen?"));
        this.registrar = new NeoForgeRegistrarPlatform(this.modId(), this.modBus());
    }

    @Override
    public RegistrarPlatform registrars() {
        return this.registrar;
    }

    private IEventBus modBus() {
        return this.container.getEventBus();
    }
}
