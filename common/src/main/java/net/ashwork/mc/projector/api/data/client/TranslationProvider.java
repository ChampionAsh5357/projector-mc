package net.ashwork.mc.projector.api.data.client;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public interface TranslationProvider {

    void add(String key, String value);

    default void add(Component key, String value) {
        if (key.getContents() instanceof TranslatableContents translatable) {
            this.add(translatable.getKey(), value);
        }
    }

    void addItem(Supplier<? extends Item> key, String value);

    default void addCreativeTab(Supplier<? extends CreativeModeTab> key, String value) {
        this.add(key.get().getDisplayName(), value);
    }
}
