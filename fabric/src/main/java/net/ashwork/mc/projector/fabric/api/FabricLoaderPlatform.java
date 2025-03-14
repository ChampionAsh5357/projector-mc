package net.ashwork.mc.projector.fabric.api;

import net.ashwork.mc.projector.api.loader.AbstractModLoaderPlatform;
import net.ashwork.mc.projector.api.ModLoaderPlatform;
import net.ashwork.mc.projector.api.registry.Registrar;
import net.ashwork.mc.projector.fabric.api.registry.FabricRegistrar;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class FabricLoaderPlatform extends AbstractModLoaderPlatform implements ModLoaderPlatform {

    public FabricLoaderPlatform(String modId) {
        super(modId);
    }

    @Override
    public <REGISTRY> Registrar<REGISTRY> createRegistrar(ResourceKey<? extends Registry<REGISTRY>> registryKey) {
        return new FabricRegistrar<>(registryKey, this.modId());
    }
}
