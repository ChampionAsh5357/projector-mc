package net.ashwork.mc.projector.api.registry;

import net.ashwork.mc.projector.api.registry.types.CreativeModeTabRegistrar;
import net.ashwork.mc.projector.api.registry.types.Registrar;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public interface RegistrarPlatform {

    <REGISTRY> Registrar<REGISTRY> createRegistrar(ResourceKey<? extends Registry<REGISTRY>> registryKey);

    CreativeModeTabRegistrar createTabRegistrar();
}
