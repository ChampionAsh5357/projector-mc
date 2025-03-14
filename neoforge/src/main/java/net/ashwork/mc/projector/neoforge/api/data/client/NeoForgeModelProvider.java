package net.ashwork.mc.projector.neoforge.api.data.client;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.data.PackOutput;

import java.util.function.Consumer;

public class NeoForgeModelProvider extends ModelProvider {

    private final Consumer<BlockModelGenerators> blockModels;
    private final Consumer<ItemModelGenerators> itemModels;

    public NeoForgeModelProvider(PackOutput output, String modId, Consumer<BlockModelGenerators> blockModels, Consumer<ItemModelGenerators> itemModels) {
        super(output, modId);
        this.blockModels = blockModels;
        this.itemModels = itemModels;
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        this.blockModels.accept(blockModels);
        this.itemModels.accept(itemModels);
    }
}
