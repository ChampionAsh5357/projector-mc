package net.ashwork.mc.projector.fabric.api.registry;

import net.ashwork.mc.projector.api.registry.RegistrarPlatform;
import net.ashwork.mc.projector.api.registry.types.CreativeModeTabRegistrar;
import net.ashwork.mc.projector.api.registry.types.ItemRegistrar;
import net.ashwork.mc.projector.api.registry.types.Registrar;
import net.ashwork.mc.projector.fabric.api.registry.types.FabricCreativeModeTabRegistrar;
import net.ashwork.mc.projector.fabric.api.registry.types.FabricItemRegistrar;
import net.ashwork.mc.projector.fabric.api.registry.types.FabricRegistrar;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class FabricRegistrarPlatform implements RegistrarPlatform {

    private final String modId;

    public FabricRegistrarPlatform(String modId) {
        this.modId = modId;
    }

    @Override
    public <REGISTRY> Registrar<REGISTRY> createRegistrar(ResourceKey<? extends Registry<REGISTRY>> registryKey) {
        return new FabricRegistrar<>(registryKey, this.modId);
    }

    @Override
    public ItemRegistrar createItemRegistrar() {
        return new FabricItemRegistrar(this.modId);
    }

    @Override
    public CreativeModeTabRegistrar createTabRegistrar() {
        return new FabricCreativeModeTabRegistrar(this.modId);
    }
}
