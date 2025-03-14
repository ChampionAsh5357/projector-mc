package net.ashwork.mc.projector.api.registry.types;

import net.ashwork.mc.projector.api.registry.ObjectReference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

public interface CreativeModeTabRegistrar extends Registrar<CreativeModeTab> {

    default ObjectReference<CreativeModeTab, CreativeModeTab> registerTab(String name, UnaryOperator<CreativeModeTab.Builder> builder) {
        return this.registerTab(name, (key, factory) -> builder.apply(factory));
    }

    ObjectReference<CreativeModeTab, CreativeModeTab> registerTab(String name, BiFunction<ResourceLocation, CreativeModeTab.Builder, CreativeModeTab.Builder> builder);
}
