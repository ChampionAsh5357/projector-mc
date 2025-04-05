package net.ashwork.mc.projector.api.client.model;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class AbstractEntityModelManager implements EntityModelManager {

    protected final String modId;
    protected final Map<ModelLayerLocation, EntityModelReference<?>> modelReferences;
    protected final Table<ResourceKey<EquipmentAsset>, EquipmentClientInfo.LayerType, ModelLayerLocation> equipment;

    protected AbstractEntityModelManager(String modId) {
        this.modId = modId;
        this.modelReferences = new HashMap<>();
        this.equipment = HashBasedTable.create();
    }

    @Override
    public ModelLayerLocation modelLoc(String name, String layer) {
        return new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(this.modId, name), layer);
    }

    @Override
    public <T extends EntityRenderState> void registerModel(ModelLayerLocation layer, Supplier<LayerDefinition> defn, Function<ModelPart, EntityModel<T>> factory, Consumer<Function<String, SyncParts>> syncParts) {
        synchronized (this) {
            if (this.modelReferences.putIfAbsent(layer, new EntityModelReference<>(defn, factory, syncParts)) != null) {
                throw new IllegalArgumentException("Duplicate model registration: " + layer);
            }
        }
    }

    @Override
    public void registerEquipmentLayer(String name, EquipmentClientInfo.LayerType type, ModelLayerLocation layer) {
        ResourceKey<EquipmentAsset> asset = ResourceKey.create(EquipmentAssets.ROOT_ID, ResourceLocation.fromNamespaceAndPath(this.modId, name));
        synchronized (this) {
            if (!this.modelReferences.containsKey(layer)) {
                throw new IllegalArgumentException("No registered model found. `registerModel` must be called for the following layer: " + layer);
            }

            if (this.equipment.contains(asset, type)) {
                throw new IllegalArgumentException("Duplicate equipment layer registration: " + name + ", " + type);
            }
            this.equipment.put(asset, type, layer);
        }
    }

    @Nullable
    @Override
    public <T extends EntityRenderState> EntityModelReference<T> getModel(ResourceKey<EquipmentAsset> asset, EquipmentClientInfo.LayerType type) {
        var layer = this.equipment.get(asset, type);
        if (layer == null) {
            return null;
        }

        @SuppressWarnings("unchecked")
        EntityModelReference<T> ref = (EntityModelReference<T>) this.modelReferences.get(layer);
        return ref;
    }

    public static final class EntityModelReference<T extends EntityRenderState> implements EntityModelManager.ModelReference<T> {

        private final Supplier<LayerDefinition> defn;
        private final Function<ModelPart, EntityModel<T>> factory;
        private final Consumer<Function<String, SyncParts>> syncParts;
        private EntityModel<T> model;

        public EntityModelReference(Supplier<LayerDefinition> defn, Function<ModelPart, EntityModel<T>> factory, Consumer<Function<String, SyncParts>> syncParts) {
            this.defn = defn;
            this.factory = factory;
            this.syncParts = syncParts;
        }

        public void resolveModel(ModelLayerLocation layer, EntityModelSet modelSet) {
            this.model = this.factory.apply(modelSet.bakeLayer(layer));
        }

        @Override
        public EntityModel<T> model() {
            return this.model;
        }

        @Override
        public EntityModel<T> model(T renderState, Model parentModel) {
            this.model.setupAnim(renderState);
            this.syncParts.accept(partName -> {
                var partOpt = this.model.getAnyDescendantWithName(partName);
                var parentPartOpt = parentModel.getAnyDescendantWithName(partName);
                if (partOpt.isPresent() && parentPartOpt.isPresent()) {
                    var part = partOpt.get();
                    var parentPart = parentPartOpt.get();
                    part.copyFrom(parentPart);
                    return new SyncParts(part, parentPart);
                }
                return null;
            });

            return this.model;
        }

        public Supplier<LayerDefinition> getDefinition() {
            return this.defn;
        }
    }
}
