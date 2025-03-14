package net.ashwork.mc.projector.fabric.api.registry;

import net.ashwork.mc.projector.api.registry.ObjectReference;
import net.ashwork.mc.projector.api.registry.Registrar;
import net.ashwork.mc.projector.api.registry.impl.HolderDelegateObjectReference;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Function;

public class FabricRegistrar<REGISTRY> implements Registrar<REGISTRY> {

    private final Registry<REGISTRY> registry;
    private final String modId;

    @SuppressWarnings("unchecked")
    public FabricRegistrar(ResourceKey<? extends Registry<REGISTRY>> registryKey, String modId) {
        this.registry = (Registry<REGISTRY>) BuiltInRegistries.REGISTRY.getValue(registryKey.location());
        this.modId = modId;
    }

    @Override
    public <IMPL extends REGISTRY> ObjectReference<REGISTRY, IMPL> register(String name, Function<ResourceLocation, IMPL> factory) {
        var registryName = ResourceLocation.fromNamespaceAndPath(this.modId, name);
        var holder = Registry.registerForHolder(this.registry, registryName, factory.apply(registryName));
        return new HolderDelegateObjectReference<>(holder);
    }
}
