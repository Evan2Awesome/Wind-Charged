package net.wind_weaponry.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.wind_weaponry.WindChargedWeaponry;

public class ModTags {
    public static class Blocks {
        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(WindChargedWeaponry.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> LONGSWORD_ENCHANTABLE = createTag("longsword_enchantable");
        public static final TagKey<Item> GAUNTLET_ENCHANTABLE = createTag("gauntlet_enchantable");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(WindChargedWeaponry.MOD_ID, name));
        }
    }
}
