package net.ashwork.mc.projector.data.client;

import net.ashwork.mc.projector.init.ItemRegistrar;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

public interface ProjectorModels {

    static void buildItemModels(ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(ItemRegistrar.WITCH_HAT.get(), ModelTemplates.FLAT_ITEM);
    }
}
