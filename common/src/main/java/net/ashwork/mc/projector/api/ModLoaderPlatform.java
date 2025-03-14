package net.ashwork.mc.projector.api;

import net.ashwork.mc.projector.api.loader.ModIdentifier;
import net.ashwork.mc.projector.api.registry.Registrar;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public interface ModLoaderPlatform extends ModIdentifier {

    <REGISTRY> Registrar<REGISTRY> createRegistrar(ResourceKey<? extends Registry<REGISTRY>> registryKey);
}
