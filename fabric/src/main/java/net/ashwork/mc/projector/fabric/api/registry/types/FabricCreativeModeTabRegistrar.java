package net.ashwork.mc.projector.fabric.api.registry.types;

import net.ashwork.mc.projector.api.registry.ObjectReference;
import net.ashwork.mc.projector.api.registry.types.CreativeModeTabRegistrar;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

import java.util.function.BiFunction;

public class FabricCreativeModeTabRegistrar extends FabricRegistrar<CreativeModeTab> implements CreativeModeTabRegistrar {

    public FabricCreativeModeTabRegistrar(String modId) {
        super(Registries.CREATIVE_MODE_TAB, modId);
    }


    @Override
    public ObjectReference<CreativeModeTab, CreativeModeTab> registerTab(String name, BiFunction<ResourceLocation, CreativeModeTab.Builder, CreativeModeTab.Builder> builder) {
        return this.register(name, key -> builder.apply(key, FabricItemGroup.builder()).build());
    }
}
