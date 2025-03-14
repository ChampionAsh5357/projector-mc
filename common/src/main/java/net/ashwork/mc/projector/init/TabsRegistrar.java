package net.ashwork.mc.projector.init;

import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class TabsRegistrar {

    public static final Supplier<CreativeModeTab> PROJECTOR = tab("projector", ItemRegistrar.WITCH_HAT, (params, output) -> {
        output.accept(ItemRegistrar.WITCH_HAT.get());
    });

    static Supplier<CreativeModeTab> tab(String name, Supplier<? extends Item> icon, CreativeModeTab.DisplayItemsGenerator displayItems) {
        return ProjectorRegistrars.CREATIVE_MODE_TAB.registerTab(name, (key, builder) ->
                builder.title(Component.translatable(Util.makeDescriptionId("creative_mode_tab", key)))
                        .icon(() -> icon.get().getDefaultInstance())
                        .displayItems(displayItems)
        );
    }

    static void register() {}
}
