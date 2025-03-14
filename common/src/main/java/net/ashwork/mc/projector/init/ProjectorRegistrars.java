package net.ashwork.mc.projector.init;

import net.ashwork.mc.projector.Projector;
import net.ashwork.mc.projector.api.registry.RegistrarPlatform;
import net.ashwork.mc.projector.api.registry.types.CreativeModeTabRegistrar;
import net.ashwork.mc.projector.api.registry.types.Registrar;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ProjectorRegistrars {

    private static final List<Runnable> INIT = new ArrayList<>();

    static final Registrar<Item> ITEM = registrar(Registries.ITEM, ItemRegistrar::register);
    static final CreativeModeTabRegistrar CREATIVE_MODE_TAB = registrar(RegistrarPlatform::createTabRegistrar, TabsRegistrar::register);

    public static void init() {
        INIT.forEach(Runnable::run);
    }

    private static <REGISTRY> Registrar<REGISTRY> registrar(ResourceKey<? extends Registry<REGISTRY>> registryKey, Runnable registerEntries) {
        return registrar(platform -> platform.createRegistrar(registryKey), registerEntries);
    }

    private static <REGISTRY, REGISTRAR extends Registrar<REGISTRY>> REGISTRAR registrar(Function<RegistrarPlatform, REGISTRAR> factory, Runnable registerEntries) {
        var registrar = factory.apply(Projector.PLATFORM.registrars());
        INIT.add(registerEntries);
        return registrar;
    }
}
