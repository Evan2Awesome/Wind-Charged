package net.wind_weaponry.enchantment;

import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.enchantment.effect.EnchantmentValueEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.wind_weaponry.WindChargedWeaponry;
import net.wind_weaponry.enchantment.custom.BlastEnchantmentEffect;
import net.wind_weaponry.enchantment.custom.GustEnchantmentEffect;
import net.wind_weaponry.enchantment.custom.ReapingEnchantmentEffect;
import net.wind_weaponry.enchantment.custom.ThreadStormEnchantmentEffect;

public class ModEnchantmentEffects {
    public static final MapCodec<? extends EnchantmentEntityEffect> GUST_EFFECT =
            registerEntityEffect("gust", GustEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentValueEffect> BLAST_EFFECT =
            registerValueEffect("blast", BlastEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentValueEffect> REAPING_EFFECT =
            registerValueEffect("reaping", ReapingEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentValueEffect> THREAD_STORM_EFFECT =
            registerValueEffect("thread_storm", ThreadStormEnchantmentEffect.CODEC);

    private static MapCodec<? extends EnchantmentEntityEffect> registerEntityEffect(String name, MapCodec<? extends EnchantmentEntityEffect> codec){
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.of(WindChargedWeaponry.MOD_ID,name), codec);
    }

    private static MapCodec<? extends EnchantmentValueEffect> registerValueEffect(String name, MapCodec<? extends EnchantmentValueEffect> codec){
        return Registry.register(Registries.ENCHANTMENT_VALUE_EFFECT_TYPE, Identifier.of(WindChargedWeaponry.MOD_ID,name), codec);
    }

    public static void registerEnchantmentEffects(){
        WindChargedWeaponry.LOGGER.info("Registering Mod Enchantment Effects for " + WindChargedWeaponry.MOD_ID);
    }
}