package net.wind_weaponry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.wind_weaponry.item.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModGroup {
    public static final ItemGroup IKEA_ITEMS = Registry.register(Registries.ITEM_GROUP, Identifier.of(WindChargedWeaponry.MOD_ID, "wind_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.WIND_HAMMER))
                    .displayName(Text.translatable("itemgroup.wind_weaponry.wind_items"))
                    .entries((displayContext, entries) -> {
                        //add items here
                        //entries.add(ModItems.EXAMPLE);
                        entries.add(ModItems.WIND_LONGSWORD);
                        entries.add(ModItems.WIND_GAUNTLET);
                    })
                    .build());


    public static void registerItemGroups(){
        WindChargedWeaponry.LOGGER.info("Registering item groups for " + WindChargedWeaponry.MOD_ID);
    }
}
