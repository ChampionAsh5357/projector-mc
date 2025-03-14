package net.ashwork.mc.projector.neoforge.api.registry;

import net.ashwork.mc.projector.api.registry.ObjectReference;
import net.ashwork.mc.projector.api.registry.Registrar;
import net.ashwork.mc.projector.api.registry.impl.HolderDelegateObjectReference;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class NeoForgeRegistrar<REGISTRY> implements Registrar<REGISTRY> {

    private final DeferredRegister<REGISTRY> delegate;

    public NeoForgeRegistrar(DeferredRegister<REGISTRY> delegate) {
        this.delegate = delegate;
    }

    @Override
    public <IMPL extends REGISTRY> ObjectReference<REGISTRY, IMPL> register(String name, Function<ResourceLocation, IMPL> factory) {
        var holder = this.delegate.register(name, factory);
        return new HolderDelegateObjectReference<>(holder);
    }
}
