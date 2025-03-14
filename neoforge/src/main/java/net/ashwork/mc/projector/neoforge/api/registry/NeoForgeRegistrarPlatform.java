package net.ashwork.mc.projector.neoforge.api.registry;

import net.ashwork.mc.projector.api.registry.RegistrarPlatform;
import net.ashwork.mc.projector.api.registry.types.CreativeModeTabRegistrar;
import net.ashwork.mc.projector.api.registry.types.ItemRegistrar;
import net.ashwork.mc.projector.api.registry.types.Registrar;
import net.ashwork.mc.projector.neoforge.api.registry.types.NeoForgeCreativeModeTabRegistrar;
import net.ashwork.mc.projector.neoforge.api.registry.types.NeoForgeItemRegistrar;
import net.ashwork.mc.projector.neoforge.api.registry.types.NeoForgeRegistrar;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class NeoForgeRegistrarPlatform implements RegistrarPlatform {

    private final String modId;
    private final IEventBus modBus;

    public NeoForgeRegistrarPlatform(String modId, IEventBus modBus) {
        this.modId = modId;
        this.modBus = modBus;
    }

    @Override
    public <REGISTRY> Registrar<REGISTRY> createRegistrar(ResourceKey<? extends Registry<REGISTRY>> registryKey) {
        return this.create(registryKey, NeoForgeRegistrar::new);
    }

    @Override
    public ItemRegistrar createItemRegistrar() {
        return this.create(Registries.ITEM, NeoForgeItemRegistrar::new);
    }

    @Override
    public CreativeModeTabRegistrar createTabRegistrar() {
        return this.create(Registries.CREATIVE_MODE_TAB, NeoForgeCreativeModeTabRegistrar::new);
    }

    private <REGISTRY, REGISTRAR extends Registrar<REGISTRY>> REGISTRAR create(ResourceKey<? extends Registry<REGISTRY>> registryKey, Function<DeferredRegister<REGISTRY>, REGISTRAR> factory) {
        var registrar = DeferredRegister.create(registryKey, this.modId);
        registrar.register(this.modBus);
        return factory.apply(registrar);
    }
}
