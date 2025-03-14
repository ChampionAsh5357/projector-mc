package net.ashwork.mc.projector.data.client;

import net.ashwork.mc.projector.init.ItemEntries;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

public interface ProjectorModels {

    static void buildItemModels(ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(ItemEntries.WITCH_HAT.get(), ModelTemplates.FLAT_ITEM);
    }
}
