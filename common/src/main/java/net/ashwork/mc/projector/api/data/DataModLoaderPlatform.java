package net.ashwork.mc.projector.api.data;

import net.ashwork.mc.projector.api.data.generator.GeneratorFactory;
import net.ashwork.mc.projector.api.loader.ModIdentifier;

import java.util.function.Consumer;

public interface DataModLoaderPlatform extends ModIdentifier {

    void globalPack(Consumer<GeneratorFactory> factory);
}
