package net.wind_weaponry.enchantment;

import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.EnchantmentEffectTarget;
import net.minecraft.enchantment.effect.value.AddEnchantmentEffect;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.wind_weaponry.WindChargedWeaponry;
import net.wind_weaponry.enchantment.custom.GustEnchantmentEffect;
import net.wind_weaponry.util.ModTags;

public class ModEnchantments {
    public static final RegistryKey<Enchantment> GUST_EFFECT =
            RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(WindChargedWeaponry.MOD_ID, "gust"));
    public static final RegistryKey<Enchantment> BLAST_EFFECT =
            RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(WindChargedWeaponry.MOD_ID, "blast"));

    public static void bootstrap(Registerable<Enchantment> registerable) {
        var enchantments = registerable.getRegistryLookup(RegistryKeys.ENCHANTMENT);
        var items = registerable.getRegistryLookup(RegistryKeys.ITEM);

        register(registerable, GUST_EFFECT, Enchantment.builder(Enchantment.definition(
                items.getOrThrow(ModTags.Items.LONGSWORD_ENCHANTABLE),
                items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                3,
                1,
                Enchantment.leveledCost(5,7),
                Enchantment.leveledCost(25,9),
                2,
                AttributeModifierSlot.MAINHAND))
                .exclusiveSet(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE_SET))
                .addEffect(EnchantmentEffectComponentTypes.POST_ATTACK,
                        EnchantmentEffectTarget.ATTACKER,EnchantmentEffectTarget.VICTIM,
                        new GustEnchantmentEffect()));

        register(registerable, BLAST_EFFECT, Enchantment.builder(Enchantment.definition(
                        items.getOrThrow(ModTags.Items.GAUNLET_ENCHANTABLE),
                        items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                        3,
                        1,
                        Enchantment.leveledCost(5,7),
                        Enchantment.leveledCost(25,9),
                        2,
                        AttributeModifierSlot.HAND))
                .exclusiveSet(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE_SET))
                .addNonListEffect(EnchantmentEffectComponentTypes.TRIDENT_SPIN_ATTACK_STRENGTH, new AddEnchantmentEffect(EnchantmentLevelBasedValue.linear(1F, 0F))));
    }

    public static void register(Registerable<Enchantment> registry, RegistryKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.getValue()));
    }
}
