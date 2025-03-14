package net.ashwork.mc.projector.api.data.generator;

import net.ashwork.mc.projector.api.data.client.TranslationProvider;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import org.apache.commons.lang3.function.Consumers;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public interface GeneratorFactory {

    <PROVIDER extends DataProvider> PROVIDER create(DataProvider.Factory<PROVIDER> factory);

    <PROVIDER extends DataProvider> PROVIDER create(FactoryWithRegistries<PROVIDER> factory);

    default void translations(Consumer<TranslationProvider> provider) {
        this.translations("en_us", provider);
    }

    void translations(String locale, Consumer<TranslationProvider> provider);

    default void blockModels(Consumer<BlockModelGenerators> blockModels) {
        this.models(blockModels, Consumers.nop());
    }

    default void itemModels(Consumer<ItemModelGenerators> itemModels) {
        this.models(Consumers.nop(), itemModels);
    }

    void models(Consumer<BlockModelGenerators> blockModels, Consumer<ItemModelGenerators> itemModels);

    interface FactoryWithRegistries<PROVIDER extends DataProvider> {

        PROVIDER create(PackOutput output, CompletableFuture<HolderLookup.Provider> registries);
    }
}
