package net.ashwork.mc.projector.api.data.provider;

import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.RegistryOps;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class JsonCodecProvider<KEY, OBJECT, OUTPUT extends BiConsumer<KEY, OBJECT>> implements DataProvider {

    private final PackOutput.PathProvider pathProvider;
    private final CompletableFuture<HolderLookup.Provider> registries;
    private final String directory;
    private final String modId;
    private final Codec<OBJECT> codec;
    private final BiFunction<PackOutput.PathProvider, KEY, Path> pathGetter;
    private final Function<BiConsumer<KEY, OBJECT>, OUTPUT> registrar;
    private final ObjectProvider<KEY, OBJECT, OUTPUT> provider;

    public JsonCodecProvider(
            PackOutput output, PackOutput.Target target, String directory, CompletableFuture<HolderLookup.Provider> registries,
            String modId, BiFunction<PackOutput.PathProvider, KEY, Path> pathGetter, Codec<OBJECT> codec,
            Function<BiConsumer<KEY, OBJECT>, OUTPUT> registrar, ObjectProvider<KEY, OBJECT, OUTPUT> provider
    ) {
        this.pathProvider = output.createPathProvider(target, directory);
        this.registries = registries;
        this.directory = directory;
        this.modId = modId;
        this.codec = codec;
        this.pathGetter = pathGetter;
        this.registrar = registrar;
        this.provider = provider;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        return this.registries.thenCompose(lookupProvider -> {
            // Add entries
            Map<KEY, OBJECT> entries = new HashMap<>();
            this.provider.add(lookupProvider, this.registrar.apply((key, value) -> {
                if (entries.putIfAbsent(key, value) != null) {
                    throw new IllegalStateException("Duplicate key: " + key);
                }
            }));

            // Generate objects
            RegistryOps<JsonElement> ops = lookupProvider.createSerializationContext(JsonOps.INSTANCE);
            return DataProvider.saveAll(cache, obj -> this.codec.encodeStart(ops, obj).getOrThrow(), k -> this.pathGetter.apply(this.pathProvider, k), entries);
        });
    }

    @Override
    public String getName() {
        return this.directory + ": " + this.modId;
    }

    @FunctionalInterface
    public interface ObjectProvider<KEY, OBJECT, OUTPUT extends BiConsumer<KEY, OBJECT>> {

        void add(HolderLookup.Provider registries, OUTPUT output);
    }
}
