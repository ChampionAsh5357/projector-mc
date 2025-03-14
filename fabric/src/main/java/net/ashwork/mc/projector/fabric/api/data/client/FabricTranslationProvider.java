package net.ashwork.mc.projector.fabric.api.data.client;

import net.ashwork.mc.projector.api.data.client.TranslationProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Item;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class FabricTranslationProvider extends FabricLanguageProvider {

    private final Consumer<TranslationProvider> provider;

    public FabricTranslationProvider(FabricDataOutput dataOutput, String locale, CompletableFuture<HolderLookup.Provider> registryLookup, Consumer<TranslationProvider> provider) {
        super(dataOutput, locale, registryLookup);
        this.provider = provider;
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
        this.provider.accept(new TranslationProvider() {
            @Override
            public void add(String key, String value) {
                translationBuilder.add(key, value);
            }

            @Override
            public void addItem(Supplier<? extends Item> key, String value) {
                translationBuilder.add(key.get(), value);
            }
        });
    }
}
