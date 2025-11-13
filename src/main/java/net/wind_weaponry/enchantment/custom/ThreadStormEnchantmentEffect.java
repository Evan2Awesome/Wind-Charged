package net.wind_weaponry.enchantment.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.enchantment.effect.EnchantmentValueEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.wind_weaponry.item.component.ModDataComponents;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

public record ThreadStormEnchantmentEffect(EnchantmentLevelBasedValue value) implements EnchantmentValueEffect {
    public static final MapCodec<ThreadStormEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec((instance) ->
            instance.group(EnchantmentLevelBasedValue.CODEC.fieldOf("value").forGetter(ThreadStormEnchantmentEffect::value)).apply(instance, ThreadStormEnchantmentEffect::new));

    /*
    @Override
    public boolean isCompatibleWith(Enchantment other) {
        // Example: Make your custom enchantment incompatible with Mending
        if (other instanceof PLACEHOLDER_EFFECT) {
            return false;
        }
        return super.isCompatibleWith(other); // Or return true if it should be compatible with others
    }
    */

    @Override
    public float apply(int level, Random random, float inputValue) {
        return 0;
    }

    @Override
    public MapCodec<ThreadStormEnchantmentEffect> getCodec() {
        return CODEC;
    }
}
