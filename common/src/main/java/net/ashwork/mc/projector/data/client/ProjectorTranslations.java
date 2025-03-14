package net.ashwork.mc.projector.data.client;

import net.ashwork.mc.projector.api.data.client.TranslationProvider;
import net.ashwork.mc.projector.init.ItemRegistrar;
import net.ashwork.mc.projector.init.TabsRegistrar;

public interface ProjectorTranslations {

    static void buildEnglishTranslations(TranslationProvider provider) {
        provider.addItem(ItemRegistrar.WITCH_HAT, "Witch Hat");
        provider.addCreativeTab(TabsRegistrar.PROJECTOR, "Projector");
    }
}
