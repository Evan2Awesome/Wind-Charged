package net.wind_weaponry.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.explosion.AdvancedExplosionBehavior;
import net.wind_weaponry.compatability.NTRDealsItems;
import net.wind_weaponry.item.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;
import java.util.function.Function;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow public abstract ItemCooldownManager getItemCooldownManager();

    @Shadow public abstract Iterable<ItemStack> getHandItems();

    @Inject(method = "disableShield*", at = @At("HEAD"))
    public void disableShield(CallbackInfo ci) {
        this.getItemCooldownManager().set(ModItems.WIND_LONGSWORD, NTRDealsItems.getSwordBreakTime(this));
        if (this.getStackInHand(Hand.MAIN_HAND).isOf(ModItems.WIND_LONGSWORD)) {
            this.getWorld().createExplosion(
                this,
                        null,
                        new AdvancedExplosionBehavior(true, false, Optional.of(2.5F), Registries.BLOCK.getEntryList(BlockTags.BLOCKS_WIND_CHARGE_EXPLOSIONS).map(Function.identity())),
                        this.getX()+(this.getRotationVector().x*1),
                        this.getStandingEyeHeight()+this.getY()+(this.getRotationVector().y*1),
                        this.getZ()+(this.getRotationVector().z*1),
                        2.5F,
                        false,
                        World.ExplosionSourceType.TRIGGER,
                        ParticleTypes.GUST_EMITTER_SMALL,
                        ParticleTypes.GUST_EMITTER_LARGE,
                        SoundEvents.ENTITY_WIND_CHARGE_WIND_BURST);
        }
    }
}
