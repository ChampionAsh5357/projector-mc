package net.ashwork.mc.projector.neoforge.api.data;

import net.ashwork.mc.projector.api.data.DataModLoaderPlatform;
import net.ashwork.mc.projector.api.data.client.TranslationProvider;
import net.ashwork.mc.projector.api.data.generator.GeneratorFactory;
import net.ashwork.mc.projector.api.loader.AbstractModLoaderPlatform;
import net.ashwork.mc.projector.neoforge.api.data.client.NeoForgeModelProvider;
import net.ashwork.mc.projector.neoforge.api.data.client.NeoForgeTranslationProvider;
import net.ashwork.mc.projector.neoforge.api.data.server.NeoForgeRecipeProvider;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class NeoForgeDataLoaderPlatform extends AbstractModLoaderPlatform implements DataModLoaderPlatform {

    private final List<Consumer<GeneratorFactory>> globalRuns;

    public NeoForgeDataLoaderPlatform(String modId) {
        super(modId);
        this.globalRuns = new ArrayList<>();
    }

    @Override
    public void globalPack(Consumer<GeneratorFactory> factory) {
        this.globalRuns.add(factory);
    }

    @SubscribeEvent
    public void gatherData(GatherDataEvent.Client event) {
        var globalPack = new GeneratorFactory() {

            @Override
            public <PROVIDER extends DataProvider> PROVIDER create(DataProvider.Factory<PROVIDER> factory) {
                return event.createProvider(factory::create);
            }

            @Override
            public <PROVIDER extends DataProvider> PROVIDER create(FactoryWithRegistries<PROVIDER> factory) {
                return event.createProvider(factory::create);
            }

            @Override
            public void translations(String locale, Consumer<TranslationProvider> provider) {
                this.create(output -> new NeoForgeTranslationProvider(output, NeoForgeDataLoaderPlatform.this.modId(), locale, provider));
            }

            @Override
            public void recipes(BiFunction<HolderLookup.Provider, RecipeOutput, ? extends RecipeProvider> recipes) {
                this.create((output, registries) -> new NeoForgeRecipeProvider(output, registries, NeoForgeDataLoaderPlatform.this.modId(), recipes));
            }

            @Override
            public void models(Consumer<BlockModelGenerators> blockModels, Consumer<ItemModelGenerators> itemModels) {
                this.create(output -> new NeoForgeModelProvider(output, NeoForgeDataLoaderPlatform.this.modId(), blockModels, itemModels));
            }
        };

        this.globalRuns.forEach(run -> run.accept(globalPack));
    }
}
