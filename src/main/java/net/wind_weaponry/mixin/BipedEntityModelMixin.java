package net.wind_weaponry.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.util.Hand;
import net.wind_weaponry.item.ModItems;
import net.wind_weaponry.item.custom.NeedleItem;
import net.wind_weaponry.util.ModTags;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.wind_weaponry.item.custom.LongswordItem;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.ModelWithArms;
import net.minecraft.client.render.entity.model.ModelWithHead;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
@Mixin(BipedEntityModel.class)
public abstract class BipedEntityModelMixin<T extends LivingEntity> extends AnimalModel<T> implements ModelWithArms, ModelWithHead {
    @Shadow
    @Mutable
    @Final
    public ModelPart rightArm;
    @Shadow
    @Mutable
    @Final
    public ModelPart leftArm;
    @Shadow
    @Mutable
    @Final
    public ModelPart head;

    @Shadow public abstract void animateModel(T livingEntity, float f, float g, float h);

    @Shadow protected abstract void animateArms(T entity, float animationProgress);

    @Inject(method = "setAngles*", at = @At(value = "TAIL"))
    private void setAnglesMixin(T livingEntity, float f, float g, float h, float i, float j, CallbackInfo info) {
        if ((livingEntity.getOffHandStack().isEmpty() || livingEntity.getOffHandStack().isOf(ModItems.WIND_GAUNTLET)) && !livingEntity.isSwimming() && !livingEntity.hasVehicle()
                && livingEntity.getMainHandStack() != null) {
            if ((livingEntity.getMainHandStack().getItem() instanceof LongswordItem || livingEntity.getMainHandStack().getItem() instanceof NeedleItem) && !livingEntity.isUsingItem()) {
                this.rightArm.pitch = -0.8727F; // + (MathHelper.cos(f * 0.6662F) * 2.0F * g * 0.5F / 15)
                this.rightArm.yaw = -0.5672F;
                this.rightArm.roll = 0.0F;
                this.leftArm.pitch = -1.0472F; // + (MathHelper.cos(f * 0.6662F) * 2.0F * g * 0.5F / 15)
                this.leftArm.yaw = 0.829F;
                this.leftArm.roll = -0.0436F;
                if (this.handSwingProgress > 0) {
                    float gx = 1.0F - this.handSwingProgress;
                    float hx = MathHelper.sin(gx * 3.1415927F);
                    float kx = this.head.pitch;
                    if (kx < 0) {
                        kx = 0.25F;
                    }
                    float ix = MathHelper.sin(this.handSwingProgress * 3.1415927F) * -((kx) - 0.7F) * 0.75F * 0.6F;
                    this.rightArm.pitch = (float) ((double) this.rightArm.pitch - ((double) hx * 1.2D + (double) ix));
                    this.leftArm.pitch = (float) ((double) this.leftArm.pitch - ((double) hx * 1.2D + (double) ix) * 1.2D) * 0.75F;
                }
            }
        }
        if (livingEntity.getMainHandStack().getItem() instanceof LongswordItem){
            if (livingEntity.isBlocking() && livingEntity.getOffHandStack().isOf(ModItems.WIND_GAUNTLET)) {//block with gauntlet
                this.rightArm.yaw = -0.1f;
                this.rightArm.pitch = -1.45F;
                this.rightArm.roll = 1.1f;

                this.leftArm.yaw = -0.25f;
                this.leftArm.pitch = -1.6F;
                this.leftArm.roll = -1.2f;
            } else if (livingEntity.isBlocking() && !livingEntity.getOffHandStack().isOf(ModItems.WIND_GAUNTLET)) {//block without gauntlet
                this.rightArm.yaw = -0.2f;
                this.rightArm.pitch = -1.4F;
                this.rightArm.roll = 1.1f;

                this.leftArm.yaw = -0.05F;
                this.leftArm.pitch = 0.6F;
                this.leftArm.roll = -0.25F;
            } else if (!livingEntity.isBlocking() && livingEntity.isUsingItem() && (livingEntity.getMainHandStack().getItem() instanceof LongswordItem || livingEntity.getMainHandStack().getItem() instanceof NeedleItem)) {//use item with sword in main hand
                this.rightArm.yaw = 0.05F;
                this.rightArm.pitch = 0.6F;
                this.rightArm.roll = 0.25F;
            }
        }
    }
}

