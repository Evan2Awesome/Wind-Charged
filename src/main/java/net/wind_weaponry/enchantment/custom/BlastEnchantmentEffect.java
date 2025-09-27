package net.wind_weaponry.enchantment.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.EnchantmentValueEffect;
import net.minecraft.util.math.random.Random;

public record BlastEnchantmentEffect(EnchantmentLevelBasedValue value) implements EnchantmentValueEffect {
    public static final MapCodec<BlastEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec((instance) ->
            instance.group(EnchantmentLevelBasedValue.CODEC.fieldOf("value").forGetter(BlastEnchantmentEffect::value)).apply(instance, BlastEnchantmentEffect::new));

    @Override
    public float apply(int level, Random random, float inputValue) {
        return inputValue + this.value.getValue(level);
    }

    @Override
    public MapCodec<BlastEnchantmentEffect> getCodec() {
        return CODEC;
    }
}
