package net.ashwork.mc.projector.neoforge.api.registry.types;

import net.ashwork.mc.projector.api.registry.ObjectReference;
import net.ashwork.mc.projector.api.registry.types.CreativeModeTabRegistrar;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.BiFunction;

public class NeoForgeCreativeModeTabRegistrar extends NeoForgeRegistrar<CreativeModeTab> implements CreativeModeTabRegistrar {

    public NeoForgeCreativeModeTabRegistrar(DeferredRegister<CreativeModeTab> delegate) {
        super(delegate);
    }

    @Override
    public ObjectReference<CreativeModeTab, CreativeModeTab> registerTab(String name, BiFunction<ResourceLocation, CreativeModeTab.Builder, CreativeModeTab.Builder> builder) {
        return this.register(name, key -> builder.apply(key, CreativeModeTab.builder()).build());
    }
}
