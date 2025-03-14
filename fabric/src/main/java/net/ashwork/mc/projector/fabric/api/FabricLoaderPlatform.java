package net.ashwork.mc.projector.fabric.api;

import net.ashwork.mc.projector.api.loader.AbstractModLoaderPlatform;
import net.ashwork.mc.projector.api.ModLoaderPlatform;
import net.ashwork.mc.projector.api.registry.RegistrarPlatform;
import net.ashwork.mc.projector.api.registry.types.Registrar;
import net.ashwork.mc.projector.fabric.api.registry.FabricRegistrarPlatform;
import net.ashwork.mc.projector.fabric.api.registry.types.FabricRegistrar;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class FabricLoaderPlatform extends AbstractModLoaderPlatform implements ModLoaderPlatform {

    private final RegistrarPlatform registrar;

    public FabricLoaderPlatform(String modId) {
        super(modId);
        this.registrar = new FabricRegistrarPlatform(this.modId());
    }

    @Override
    public RegistrarPlatform registrars() {
        return this.registrar;
    }
}
