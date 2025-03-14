package net.ashwork.mc.projector.api.data.client;

import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public interface TranslationProvider {

    void add(String key, String value);

    void addItem(Supplier<? extends Item> key, String value);
}
