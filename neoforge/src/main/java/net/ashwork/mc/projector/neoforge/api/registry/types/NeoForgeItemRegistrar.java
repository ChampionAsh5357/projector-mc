package net.ashwork.mc.projector.neoforge.api.registry.types;

import net.ashwork.mc.projector.api.registry.types.ItemRegistrar;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NeoForgeItemRegistrar extends NeoForgeRegistrar<Item> implements ItemRegistrar {

    public NeoForgeItemRegistrar(DeferredRegister<Item> delegate) {
        super(delegate);
    }
}
