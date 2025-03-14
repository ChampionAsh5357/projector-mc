package net.ashwork.mc.projector.fabric.api.data.server;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

public class FabricRecipeProviderImpl extends FabricRecipeProvider {

    private final String modId;
    private final BiFunction<HolderLookup.Provider, RecipeOutput, ? extends RecipeProvider> factory;

    public FabricRecipeProviderImpl(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture, String modId, BiFunction<HolderLookup.Provider, RecipeOutput, ? extends RecipeProvider> factory) {
        super(output, registriesFuture);
        this.modId = modId;
        this.factory = factory;
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter) {
        return this.factory.apply(registryLookup, exporter);
    }

    @Override
    public String getName() {
        return "Recipes: " + this.modId;
    }
}
