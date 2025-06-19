package net.wind_weaponry.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public record GustEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<GustEnchantmentEffect> CODEC = MapCodec.unit(GustEnchantmentEffect::new);

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        //PlayerEntity temp = (PlayerEntity) user;
        System.out.println(user.getType());
        if (user.getType() == EntityType.PLAYER) {
            user.addVelocity(0, 0.75, 0);
            world.spawnParticles(ParticleTypes.GUST, pos.x, pos.y, pos.z, 1, 0.0, 0.0, 0.0, 0.0);
        }else{
            user.addVelocity(0, 0.35, 0);
            world.spawnParticles(ParticleTypes.GUST, pos.x, pos.y, pos.z, 1, 0.0, 0.0, 0.0, 0.0);
        }
        //user.addVelocity(0, 0.35, 0);
        //world.spawnParticles(ParticleTypes.GUST, pos.x, pos.y, pos.z, 1, 0.0, 0.0, 0.0, 0.0);
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
