package net.wind_weaponry.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.registry.tag.ItemTags;
import net.wind_weaponry.item.ModItems;
import net.wind_weaponry.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
     public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookupFuture) {
        super(output, registryLookupFuture);
    }



    @Override
    protected void configure(RegistryWrapper.WrapperLookup lookup) {
         //watch kaupenjoe videos 7, 10, and 11 to understand tags
        getOrCreateTagBuilder(ItemTags.SWORDS)
                .add(ModItems.NEEDLE)
                .add(ModItems.WIND_LONGSWORD);

        getOrCreateTagBuilder(ItemTags.SWORD_ENCHANTABLE)
                .add(ModItems.NEEDLE)
                .add(ModItems.WIND_LONGSWORD);

        getOrCreateTagBuilder(ModTags.Items.LONGSWORD_ENCHANTABLE)
                .add(ModItems.WIND_LONGSWORD);

        getOrCreateTagBuilder(ModTags.Items.GAUNTLET_ENCHANTABLE)
                .add(ModItems.WIND_GAUNTLET);

        getOrCreateTagBuilder(ModTags.Items.NEEDLE_ENCHANTABLE)
                .add(ModItems.NEEDLE);

    }
}
