package net.ashwork.mc.projector.api.loader;

public abstract class AbstractModLoaderPlatform implements ModIdentifier {

    private final String modId;

    protected AbstractModLoaderPlatform(String modId) {
        this.modId = modId;
    }

    @Override
    public String modId() {
        return this.modId;
    }
}
