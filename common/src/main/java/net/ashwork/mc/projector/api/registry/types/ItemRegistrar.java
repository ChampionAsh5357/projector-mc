package net.ashwork.mc.projector.api.registry.types;

import net.ashwork.mc.projector.api.registry.ObjectReference;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public interface ItemRegistrar extends Registrar<Item> {

    default ObjectReference<Item, Item> registerBasicItem(String name, UnaryOperator<Item.Properties> properties) {
        return this.registerItem(name, props -> new Item(properties.apply(props)));
    }

    default <IMPL extends Item> ObjectReference<Item, IMPL> registerItem(String name, Function<Item.Properties, IMPL> factory) {
        return this.register(name, key -> factory.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, key))));
    }
}
