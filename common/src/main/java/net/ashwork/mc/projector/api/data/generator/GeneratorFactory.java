package net.ashwork.mc.projector.api.data.generator;

import com.mojang.serialization.Codec;
import net.ashwork.mc.projector.api.data.client.TranslationProvider;
import net.ashwork.mc.projector.api.data.provider.JsonCodecProvider;
import net.ashwork.mc.projector.api.data.provider.ResourceKeyOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import org.apache.commons.lang3.function.Consumers;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public interface GeneratorFactory {

    default <PROVIDER extends DataProvider> PROVIDER create(DataProvider.Factory<PROVIDER> factory) {
        return this.create((output, registries, modId) -> factory.create(output));
    }

    default <PROVIDER extends DataProvider> PROVIDER create(FactoryWithRegistries<PROVIDER> factory) {
        return this.create((output, registries, modId) -> factory.create(output, registries));
    }

    default <PROVIDER extends DataProvider> PROVIDER createWithId(FactoryWithId<PROVIDER> factory) {
        return this.create((output, registries, modId) -> factory.create(output, modId));
    }

    <PROVIDER extends DataProvider> PROVIDER create(FactoryWithRegistriesAndId<PROVIDER> factory);

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

    void recipes(BiFunction<HolderLookup.Provider, RecipeOutput, ? extends RecipeProvider> recipes);

    void models(Consumer<BlockModelGenerators> blockModels, Consumer<ItemModelGenerators> itemModels);

    default void equipment(Consumer<ResourceKeyOutput<EquipmentAsset, EquipmentClientInfo>> provider) {
        this.assetCodec("equipment", EquipmentAssets.ROOT_ID, EquipmentClientInfo.CODEC, (registries, output) -> provider.accept(output));
    }

    default <KEY, OBJECT> void assetCodec(String directory, ResourceKey<? extends Registry<KEY>> registryKey, Codec<OBJECT> codec, JsonCodecProvider.ObjectProvider<ResourceKey<KEY>, OBJECT, ResourceKeyOutput<KEY, OBJECT>> provider) {
        this.resourceKeyCodec(PackOutput.Target.RESOURCE_PACK, directory, registryKey, codec, provider);
    }

    default <KEY, OBJECT> void dataCodec(String directory, ResourceKey<? extends Registry<KEY>> registryKey, Codec<OBJECT> codec, JsonCodecProvider.ObjectProvider<ResourceKey<KEY>, OBJECT, ResourceKeyOutput<KEY, OBJECT>> provider) {
        this.resourceKeyCodec(PackOutput.Target.DATA_PACK, directory, registryKey, codec, provider);
    }

    default <KEY, OBJECT> void resourceKeyCodec(PackOutput.Target target, String directory, ResourceKey<? extends Registry<KEY>> registryKey, Codec<OBJECT> codec, JsonCodecProvider.ObjectProvider<ResourceKey<KEY>, OBJECT, ResourceKeyOutput<KEY, OBJECT>> provider) {
        this.create((output, registries, modId) -> new JsonCodecProvider<>(
                output, target, directory, registries, modId, PackOutput.PathProvider::json, codec, registrar -> new ResourceKeyOutput<>(registrar, registryKey, modId), provider
        ));
    }

    interface FactoryWithId<PROVIDER extends DataProvider> {

        PROVIDER create(PackOutput output, String modId);
    }

    interface FactoryWithRegistriesAndId<PROVIDER extends DataProvider> {

        PROVIDER create(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, String modId);
    }

    interface FactoryWithRegistries<PROVIDER extends DataProvider> {

        PROVIDER create(PackOutput output, CompletableFuture<HolderLookup.Provider> registries);
    }
}
