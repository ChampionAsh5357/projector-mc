package net.ashwork.mc.projector.neoforge.api.data.server;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

public class NeoForgeRecipeProvider extends RecipeProvider.Runner {

    private final String modId;
    private final BiFunction<HolderLookup.Provider, RecipeOutput, ? extends RecipeProvider> factory;

    public NeoForgeRecipeProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries, String modId, BiFunction<HolderLookup.Provider, RecipeOutput, ? extends RecipeProvider> factory) {
        super(packOutput, registries);
        this.modId = modId;
        this.factory = factory;
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return this.factory.apply(registries, output);
    }

    @Override
    public String getName() {
        return "Recipes: " + this.modId;
    }
}
