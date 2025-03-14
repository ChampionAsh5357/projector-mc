package net.ashwork.mc.projector.neoforge.api.data.client;

import net.ashwork.mc.projector.api.data.client.TranslationProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.LanguageProvider;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class NeoForgeTranslationProvider extends LanguageProvider {

    private final Consumer<TranslationProvider> provider;

    public NeoForgeTranslationProvider(PackOutput output, String modid, String locale, Consumer<TranslationProvider> provider) {
        super(output, modid, locale);
        this.provider = provider;
    }

    @Override
    protected void addTranslations() {
        this.provider.accept(new TranslationProvider() {
            @Override
            public void add(String key, String value) {
                NeoForgeTranslationProvider.this.add(key, value);
            }

            @Override
            public void addItem(Supplier<? extends Item> key, String value) {
                NeoForgeTranslationProvider.this.addItem(key, value);
            }
        });
    }
}
