package net.ashwork.mc.projector.api.registry;

import net.minecraft.resources.ResourceLocation;

import java.util.function.Function;
import java.util.function.Supplier;

public interface Registrar<REGISTRY> {

    default <IMPL extends REGISTRY> ObjectReference<REGISTRY, IMPL> register(String name, Supplier<IMPL> factory) {
        return this.register(name, key -> factory.get());
    }

    <IMPL extends REGISTRY> ObjectReference<REGISTRY, IMPL> register(String name, Function<ResourceLocation, IMPL> factory);
}
