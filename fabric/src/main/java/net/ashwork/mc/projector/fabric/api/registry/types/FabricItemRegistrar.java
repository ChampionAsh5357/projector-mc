package net.ashwork.mc.projector.fabric.api.registry.types;

import net.ashwork.mc.projector.api.registry.types.ItemRegistrar;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;

public class FabricItemRegistrar extends FabricRegistrar<Item> implements ItemRegistrar {

    public FabricItemRegistrar(String modId) {
        super(Registries.ITEM, modId);
    }
}
