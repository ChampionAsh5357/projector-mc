package net.ashwork.mc.projector.data.server;

import net.ashwork.mc.projector.init.ItemEntries;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.level.block.Blocks;

public class ProjectorRecipes extends RecipeProvider {

    public ProjectorRecipes(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    public void buildRecipes() {
        this.shaped(RecipeCategory.MISC, ItemEntries.WITCH_HAT.get())
                .pattern(" B ")
                .pattern("BYB")
                .pattern("BBB")
                .define('B', Blocks.BLACK_WOOL)
                .define('Y', Blocks.YELLOW_WOOL)
                .unlockedBy(RecipeProvider.getHasName(Blocks.BLACK_WOOL), this.has(Blocks.BLACK_WOOL))
                .save(this.output);
    }
}
