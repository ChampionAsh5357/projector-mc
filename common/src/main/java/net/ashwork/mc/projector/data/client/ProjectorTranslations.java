package net.ashwork.mc.projector.data.client;

import net.ashwork.mc.projector.api.data.client.TranslationProvider;
import net.ashwork.mc.projector.init.ItemEntries;
import net.ashwork.mc.projector.init.TabEntries;

public interface ProjectorTranslations {

    static void buildEnglishTranslations(TranslationProvider provider) {
        provider.addItem(ItemEntries.WITCH_HAT, "Witch Hat");
        provider.addCreativeTab(TabEntries.PROJECTOR, "Projector");
    }
}
