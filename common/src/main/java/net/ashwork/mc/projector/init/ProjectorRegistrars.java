package net.ashwork.mc.projector.init;

import net.ashwork.mc.projector.Projector;
import net.ashwork.mc.projector.api.registry.Registrar;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ProjectorRegistrars {

    private static final List<Runnable> INIT = new ArrayList<>();

    static final Registrar<Item> ITEM = registrar(Registries.ITEM, ItemRegistrar::register);

    public static void init() {
        INIT.forEach(Runnable::run);
    }

    private static <REGISTRY> Registrar<REGISTRY> registrar(ResourceKey<? extends Registry<REGISTRY>> registryKey, Runnable registerEntries) {
        var registrar = Projector.PLATFORM.createRegistrar(registryKey);
        INIT.add(registerEntries);
        return registrar;
    }
}
