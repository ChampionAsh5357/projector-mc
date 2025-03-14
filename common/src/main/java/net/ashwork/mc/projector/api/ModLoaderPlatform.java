package net.ashwork.mc.projector.api;

import net.ashwork.mc.projector.api.loader.ModIdentifier;
import net.ashwork.mc.projector.api.registry.RegistrarPlatform;

public interface ModLoaderPlatform extends ModIdentifier {

    RegistrarPlatform registrars();
}
