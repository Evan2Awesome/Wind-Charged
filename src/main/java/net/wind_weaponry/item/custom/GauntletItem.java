package net.wind_weaponry.item.custom;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageScaling;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.minecraft.world.explosion.AdvancedExplosionBehavior;
import net.wind_weaponry.enchantment.ModEnchantmentEffects;
import net.wind_weaponry.enchantment.ModEnchantments;
import net.wind_weaponry.util.Functions;

import java.util.Optional;
import java.util.function.Function;

public class GauntletItem extends ToolItem {
    //public static final int TICKS_PER_SECOND = 20;
    public float variant = 0;

    public GauntletItem(ToolMaterial toolMaterial, Item.Settings settings){
        super(toolMaterial, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand){
        if (user.getStackInHand(hand).getEnchantments().getEnchantments().contains(Functions.getEnchantmentEntry(world, ModEnchantments.BLAST_EFFECT)))
            variant = 1;
        else{
            variant = 0;
        }
        user.setCurrentHand(hand);
        return TypedActionResult.consume(user.getStackInHand(hand));
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        PlayerEntity temp = (PlayerEntity) user;
        stack.damage(4, user, EquipmentSlot.MAINHAND);
        if (variant == 0) temp.getItemCooldownManager().set(this, 30);
        else {
            temp.getItemCooldownManager().set(this, 60);
        }

        if (variant == 0){
            if (remainingUseTicks <= 71980){
                if (user.isSneaking())
                    world.createExplosion(
                            user,
                            null,
                            new AdvancedExplosionBehavior(true, false, Optional.of(2.5F), Registries.BLOCK.getEntryList(BlockTags.BLOCKS_WIND_CHARGE_EXPLOSIONS).map(Function.identity())),
                            user.getX()+(user.getRotationVector().x*1),
                            user.getStandingEyeHeight()+user.getY()+(user.getRotationVector().y*1),
                            user.getZ()+(user.getRotationVector().z*1),
                            1.2F,
                            false,
                            World.ExplosionSourceType.TRIGGER,
                            ParticleTypes.GUST_EMITTER_SMALL,
                            ParticleTypes.GUST_EMITTER_LARGE,
                            SoundEvents.ENTITY_WIND_CHARGE_WIND_BURST);
                else {
                    world.createExplosion(
                            null,
                            null,
                            new AdvancedExplosionBehavior(true, false, Optional.of(0.75F), Registries.BLOCK.getEntryList(BlockTags.BLOCKS_WIND_CHARGE_EXPLOSIONS).map(Function.identity())),
                            user.getX() + (user.getRotationVector().x * 1),
                            user.getStandingEyeHeight() + user.getY() + (user.getRotationVector().y * 1),
                            user.getZ() + (user.getRotationVector().z * 1),
                            1.2F,
                            false,
                            World.ExplosionSourceType.TRIGGER,
                            ParticleTypes.GUST_EMITTER_SMALL,
                            ParticleTypes.GUST_EMITTER_LARGE,
                            SoundEvents.ENTITY_WIND_CHARGE_WIND_BURST);
                    world.createExplosion(
                            user,
                            null,
                            new AdvancedExplosionBehavior(true, false, Optional.of(2.5F), Registries.BLOCK.getEntryList(BlockTags.BLOCKS_WIND_CHARGE_EXPLOSIONS).map(Function.identity())),
                            user.getX() + (user.getRotationVector().x * 1),
                            user.getStandingEyeHeight() + user.getY() + (user.getRotationVector().y * 1),
                            user.getZ() + (user.getRotationVector().z * 1),
                            1.2F,
                            false,
                            World.ExplosionSourceType.TRIGGER,
                            ParticleTypes.GUST_EMITTER_SMALL,
                            ParticleTypes.GUST_EMITTER_LARGE,
                            SoundEvents.ENTITY_WIND_CHARGE_WIND_BURST);
                }
            }
        }else{
            if (remainingUseTicks <= 71980){
                world.createExplosion(
                    null,
                    null,
                    new AdvancedExplosionBehavior(true, true, Optional.of(2F), Registries.BLOCK.getEntryList(BlockTags.BLOCKS_WIND_CHARGE_EXPLOSIONS).map(Function.identity())),
                    user.getX() + (user.getRotationVector().x * 1),
                    user.getStandingEyeHeight() + user.getY() + (user.getRotationVector().y * 1),
                    user.getZ() + (user.getRotationVector().z * 1),
                    1.4F,
                    false,
                    World.ExplosionSourceType.TRIGGER,
                    ParticleTypes.GUST_EMITTER_SMALL,
                    ParticleTypes.GUST_EMITTER_LARGE,
                    SoundEvents.ENTITY_WIND_CHARGE_WIND_BURST);
                world.createExplosion(
                    user,
                    null,
                    new AdvancedExplosionBehavior(true, true, Optional.of(3F), Registries.BLOCK.getEntryList(BlockTags.BLOCKS_WIND_CHARGE_EXPLOSIONS).map(Function.identity())),
                    user.getX() + (user.getRotationVector().x * 1),
                    user.getStandingEyeHeight() + user.getY() + (user.getRotationVector().y * 1),
                    user.getZ() + (user.getRotationVector().z * 1),
                    1.2F,
                    false,
                    World.ExplosionSourceType.TRIGGER,
                    ParticleTypes.GUST_EMITTER_SMALL,
                    ParticleTypes.GUST_EMITTER_LARGE,
                    SoundEvents.ENTITY_GENERIC_EXPLODE);
            }
        }
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        //((PlayerEntity) user).experienceLevel = remainingUseTicks;
        if (remainingUseTicks == 71991) {
            user.getWorld().playSound(null, user.getX(),user.getY(),user.getZ(),
                    SoundEvents.BLOCK_COPPER_BULB_PLACE, SoundCategory.PLAYERS, 1.3f, 1.7f);
            user.getWorld().playSound(null, user.getX(),user.getY(),user.getZ(),
                    SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON, SoundCategory.PLAYERS, 1f, 1.2f);
            user.getWorld().playSound(null, user.getX(),user.getY(),user.getZ(),
                    SoundEvents.ENTITY_BREEZE_INHALE, SoundCategory.PLAYERS, 1f, 1.8f);
        } else if (remainingUseTicks == 71981) {
            user.getWorld().playSound(null, user.getX(),user.getY(),user.getZ(),
                    SoundEvents.BLOCK_COPPER_TRAPDOOR_CLOSE, SoundCategory.PLAYERS, 0.9f, 1.5f);
        }
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 72000;
    }

    /* I stole the railgun code from another mod I worked on for reference (thanks Sawyer!)
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.getItemCooldownManager().set(this, 15); // firing cooldown (20 is an ender pearl for reference, 3 is just a test value)
        user.setCurrentHand(hand); // starts the "use" animation (charging)
        user.getWorld().playSound(null, user.getX(),user.getY(),user.getZ(), SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.PLAYERS, 0.4f, 2f);
        return TypedActionResult.consume(user.getStackInHand(hand));
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (world instanceof ServerWorld serverWorld && user instanceof PlayerEntity player)
            stopTriggeredAnim(player, GeoItem.getOrAssignId(player.getStackInHand(player.getActiveHand()), serverWorld), "Charging", "use.charging");

        if (remainingUseTicks <= 65) {
            if (world instanceof ServerWorld serverWorld && user instanceof PlayerEntity player){
                player.getWorld().playSound(null, player.getX(),player.getY(),player.getZ(), SoundEvents.BLOCK_RESPAWN_ANCHOR_DEPLETE, SoundCategory.PLAYERS, 0.9f, 1.7f);
                triggerAnim(player, GeoItem.getOrAssignId(player.getStackInHand(player.getActiveHand()), serverWorld), "Shooting", "use.shooting");
            }

            // man i started to comment all ts but bro this is so convoluted
            double range = 50.0;
            double d = range;
            double e = MathHelper.square(range);
            Vec3d vec3d = user.getCameraPosVec(1.0F);
            //System.out.println(user.getRotationVector());
            //System.out.println(vec3d);
            HitResult hitResult = user.raycast(d, 1.0F, false);
            double f = hitResult.getPos().squaredDistanceTo(vec3d);
            if (hitResult.getType() != HitResult.Type.MISS) {
                e = f;
                d = Math.sqrt(f);
            }

            Vec3d vec3d2 = user.getRotationVec(1.0F);
            Vec3d vec3d3 = vec3d.add(vec3d2.x * d, vec3d2.y * d, vec3d2.z * d);
            Box box = user.getBoundingBox().stretch(vec3d2.multiply(d)).expand(1.0, 1.0, 1.0);
            EntityHitResult entityHitResult = ProjectileUtil.raycast(user, vec3d, vec3d3, box, entity -> !entity.isSpectator() && entity.canHit(), e);
            BlockHitResult blockHitResult = world.raycast(new RaycastContext(
                    user.getCameraPosVec(1.0F),
                    user.getCameraPosVec(1.0F).add(user.getRotationVec(1.0F).multiply(range)),
                    RaycastContext.ShapeType.COLLIDER,
                    RaycastContext.FluidHandling.NONE,
                    user
            ));
            //######################################################################################

            if (entityHitResult != null) { //entity hit detection
                if(!world.isClient()) {
                    drawBeam(world, entityHitResult.getPos(), vec3d, user.getRotationVector()); //

                    //blows up creepers
                    if (entityHitResult.getEntity().getClass().equals(CreeperEntity.class)) {
                        CreeperEntity creeper = (CreeperEntity) entityHitResult.getEntity();
                        creeper.setInvulnerable(true); //make them invincible so no damage effect will happen (that red shading)
                        creeper.setFuseSpeed(30); // explode the creeper super fast
                        creeper.onStruckByLightning(null, null); // make them charged (it would be more efficient to directly set the custom nbt but idk how)
                        creeper.setFireTicks(0); // extinguish the fire that the lightning gives them

                        // blows up end crystals
                    } else if (entityHitResult.getEntity().getClass().equals(EndCrystalEntity.class)) {
                        entityHitResult.getEntity().damage(entityHitResult.getEntity().getDamageSources().generic(), 1.0F);
                        //does damage to end crystal to blow it up, idk where to call getDamageSources so i just used the same old entity

                    } else { // hits entities
                        entityHitResult.getEntity().damage(entityHitResult.getEntity().getDamageSources().playerAttack((PlayerEntity)user), 50.0F); //deals 50 player-dealt damage (for now)
                    }
                }
            } else { // block hit detection
                if(!world.isClient()) { //runs actual hits on server, and particles on client
                    drawBeam(world, blockHitResult.getPos(), vec3d, user.getRotationVector());

                    if (!world.getBlockState(blockHitResult.getBlockPos()).isAir()) { // does nothing when hitting air
                        if (world.getBlockState(blockHitResult.getBlockPos()).getBlock() == Blocks.TNT) { //tnt
                            world.breakBlock(blockHitResult.getBlockPos(), false);
                            world.createExplosion(user, blockHitResult.getBlockPos().getX() + 0.5, blockHitResult.getBlockPos().getY() + 0.5, blockHitResult.getBlockPos().getZ() + 0.5, 5.0F, World.ExplosionSourceType.MOB);
                            // slightly bigger explosion (4->5)

                        } else if (world.getBlockState(blockHitResult.getBlockPos()).getBlock() == Blocks.RESPAWN_ANCHOR) { //respawn anchor
                            world.breakBlock(blockHitResult.getBlockPos(), false);
                            world.createExplosion(user, blockHitResult.getBlockPos().getX() + 0.5, blockHitResult.getBlockPos().getY() + 0.5, blockHitResult.getBlockPos().getZ() + 0.5, 7.0F, World.ExplosionSourceType.MOB);

                            // to change the particles for testing, shoot an emerald or redstone block
                            // for some reason the railgun always runs twice so thats why its +0.5
                        } else if (world.getBlockState(blockHitResult.getBlockPos()).getBlock() == Blocks.EMERALD_BLOCK) {
                            if (j < 19) {j += 1;
                            } else {j = 0;}// moves particle type up when shot
                        } else if (world.getBlockState(blockHitResult.getBlockPos()).getBlock() == Blocks.REDSTONE_BLOCK) {
                            if (j > 0) {j -= 1;
                            } else {j = 19;} // moves particle type down when shot
                        }

                        //adding a noise to it
                        world.playSound((PlayerEntity) user, blockHitResult.getBlockPos().getX() + 0.5, blockHitResult.getBlockPos().getY() + 0.5, blockHitResult.getBlockPos().getZ() + 0.5, SoundEvents.ITEM_SHIELD_BLOCK, SoundCategory.PLAYERS);

                        if (world.getBlockState(blockHitResult.getBlockPos()).getBlock().getBlastResistance() <= 1.1F) {
                            if (Math.random() > 0.49) {
                                world.breakBlock(blockHitResult.getBlockPos(), true);
                            } else {
                                world.breakBlock(blockHitResult.getBlockPos(), false);
                            }
                        }
                    }
                    System.out.println("No Entity Target. Data: " + blockHitResult.getBlockPos() + " " + world.getBlockState(blockHitResult.getBlockPos()));

                }else{ //draws beam client-side
                    drawBeam(world, blockHitResult.getPos(), vec3d, user.getRotationVector());
                }
            }
        }

        ((PlayerEntity)user).getItemCooldownManager().set(this, 30);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {//does nothing in regards to the functionality of the item and is purely cosmetic
        return UseAction.NONE;
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (world instanceof ServerWorld serverWorld)
            triggerAnim(user, GeoItem.getOrAssignId(user.getStackInHand(user.getActiveHand()), serverWorld), "Charging", "use.charging");
        stack.setBobbingAnimationTime(0);
        ((PlayerEntity) user).experienceLevel = remainingUseTicks;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 80;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user){
        if (world instanceof ServerWorld serverWorld)
            stopTriggeredAnim(user, GeoItem.getOrAssignId(user.getStackInHand(user.getActiveHand()), serverWorld), "Charging", "use.charging");
        user.setFireTicks(60);
        user.damage(user.getDamageSources().onFire(), 0.5f);
        ((PlayerEntity)user).getItemCooldownManager().set(this, 60);
        return stack;
    }
    */
}