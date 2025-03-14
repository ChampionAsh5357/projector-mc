package net.ashwork.mc.projector.fabric.api.data.client;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;

import java.util.function.Consumer;

public class FabricModelProviderImpl extends FabricModelProvider {

    private final Consumer<BlockModelGenerators> blockModels;
    private final Consumer<ItemModelGenerators> itemModels;

    public FabricModelProviderImpl(FabricDataOutput output, Consumer<BlockModelGenerators> blockModels, Consumer<ItemModelGenerators> itemModels) {
        super(output);
        this.blockModels = blockModels;
        this.itemModels = itemModels;
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        this.blockModels.accept(blockStateModelGenerator);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        this.itemModels.accept(itemModelGenerator);
    }
}
