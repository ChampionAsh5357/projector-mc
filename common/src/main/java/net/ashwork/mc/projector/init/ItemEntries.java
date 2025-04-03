package net.ashwork.mc.projector.init;

import net.ashwork.mc.projector.Projector;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.item.equipment.Equippable;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class ItemEntries {

    public static final Supplier<Item> WITCH_HAT = basicItem("witch_hat", props ->
            props.stacksTo(1)
                    .component(DataComponents.EQUIPPABLE, Equippable.builder(EquipmentSlot.HEAD)
                            .setSwappable(false)
                            .setDamageOnHurt(false)
                            .setAsset(ResourceKey.create(EquipmentAssets.ROOT_ID, ResourceLocation.fromNamespaceAndPath(Projector.PLATFORM.modId(), "witch_hat")))
                            .build()
                    )
    );

    static Supplier<Item> basicItem(String name, UnaryOperator<Item.Properties> properties) {
        return ProjectorRegistrars.ITEM.registerBasicItem(name, properties);
    }

    static void register() {}
}
