package net.ashwork.mc.projector.fabric.api.data;

import net.ashwork.mc.projector.api.data.DataModLoaderPlatform;
import net.ashwork.mc.projector.api.data.client.TranslationProvider;
import net.ashwork.mc.projector.api.data.generator.GeneratorFactory;
import net.ashwork.mc.projector.api.loader.AbstractModLoaderPlatform;
import net.ashwork.mc.projector.fabric.api.data.client.FabricModelProviderImpl;
import net.ashwork.mc.projector.fabric.api.data.client.FabricTranslationProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.data.DataProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class FabricDataLoaderPlatform extends AbstractModLoaderPlatform implements DataModLoaderPlatform {

    private final List<Consumer<GeneratorFactory>> globalRuns;

    public FabricDataLoaderPlatform(String modId) {
        super(modId);
        this.globalRuns = new ArrayList<>();
    }

    @Override
    public void globalPack(Consumer<GeneratorFactory> factory) {
        this.globalRuns.add(factory);
    }

    public void initializeDataGenerator(FabricDataGenerator generator) {
        var globalPack = fromPack(generator.createPack());
        this.globalRuns.forEach(run -> run.accept(globalPack));
    }

    private static GeneratorFactory fromPack(FabricDataGenerator.Pack generator) {
        return new GeneratorFactory() {
            @Override
            public <PROVIDER extends DataProvider> PROVIDER create(DataProvider.Factory<PROVIDER> factory) {
                return generator.addProvider(factory);
            }

            @Override
            public <PROVIDER extends DataProvider> PROVIDER create(FactoryWithRegistries<PROVIDER> factory) {
                return generator.addProvider(factory::create);
            }

            @Override
            public void translations(String locale, Consumer<TranslationProvider> provider) {
                generator.addProvider((output, registries) -> new FabricTranslationProvider(output, locale, registries, provider));
            }

            @Override
            public void models(Consumer<BlockModelGenerators> blockModels, Consumer<ItemModelGenerators> itemModels) {
                generator.addProvider((FabricDataGenerator.Pack.Factory<FabricModelProviderImpl>) output -> new FabricModelProviderImpl(output, blockModels, itemModels));
            }
        };
    }
}
