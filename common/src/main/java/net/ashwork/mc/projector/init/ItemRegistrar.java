package net.ashwork.mc.projector.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

import java.util.function.Function;
import java.util.function.Supplier;

public class ItemRegistrar {

    public static final Supplier<Item> WITCH_HAT = item("witch_hat", Item::new);

    static <IMPL extends Item> Supplier<IMPL> item(String name, Function<Item.Properties, IMPL> factory) {
        return ProjectorRegistrars.ITEM.register(name, key -> factory.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, key))));
    }

    static void register() {}
}
