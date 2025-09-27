package net.wind_weaponry.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.wind_weaponry.WindChargedWeaponry;
import net.wind_weaponry.enchantment.ModEnchantmentEffects;
import net.wind_weaponry.enchantment.ModEnchantments;
import net.wind_weaponry.enchantment.custom.ReapingEnchantmentEffect;
import net.wind_weaponry.item.component.ModDataComponents;
import net.wind_weaponry.util.Functions;
import net.wind_weaponry.util.ModTags;

import java.util.List;

public class NeedleItem extends SwordItem {
    public NeedleItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand);
        return super.use(world, user, hand);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        PlayerEntity temp = (PlayerEntity) user;
        temp.getItemCooldownManager().set(stack.getItem(), 20);
        super.onStoppedUsing(stack, world, user, remainingUseTicks);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        if (getSilk(stack) >= 13) {
            return 20;
        }else {
            return 72000;
        }
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        PlayerEntity temp = (PlayerEntity) user;
        if (getSilk(stack) >= 13) {
            setSilk(stack, 0);
            user.heal(10);
            temp.getItemCooldownManager().set(stack.getItem(), 300);
            user.getWorld().playSound(null, user.getX(),user.getY(),user.getZ(),
                    SoundEvents.ENTITY_BREEZE_DEFLECT, SoundCategory.PLAYERS, 2.0f, 1.0f);
            user.getWorld().playSound(null, user.getX(),user.getY(),user.getZ(),
                    SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.PLAYERS, 1f, 0.8f);
            for (int i=0;i<16;i++) {
                world.addParticle(ParticleTypes.END_ROD, user.getX() + (world.random.nextBetween(-2, 2)), user.getY() + (world.random.nextBetweenExclusive(-2, 2)) + 1.0, user.getZ() + (world.random.nextBetweenExclusive(-2, 2)), 0.0, 0.1, 0.0);
            }
        } else {
            temp.getItemCooldownManager().set(stack.getItem(), 20);
        }
        return super.finishUsing(stack, world, user);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        ((PlayerEntity) user).experienceLevel = getSilk(stack);
        if (getSilk(stack) >= 13) {
            user.setVelocity(0f, 0f, 0f);
            user.fallDistance = 0;

            user.getWorld().playSound(null, user.getX(),user.getY(),user.getZ(),
                    SoundEvents.ENTITY_BREEZE_CHARGE, SoundCategory.PLAYERS, 0.5f, 1.0f);
            world.addParticle(ParticleTypes.SMALL_GUST, user.getX() + ((double) world.random.nextBetweenExclusive(-10, 10) / 10), user.getY() + ((double) world.random.nextBetweenExclusive(-0, 20) / 10), user.getZ() + ((double) world.random.nextBetweenExclusive(-10, 10) / 10), 0.0, 0.0, 0.0);
        }

        super.usageTick(world, user, stack, remainingUseTicks);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if(Screen.hasShiftDown()){
            tooltip.add(Text.translatable("tooltip.wind-charged-weaponry.needle.shift_down"));
        }else{
            tooltip.add(Text.translatable("tooltip.wind-charged-weaponry.longsword.shift_up"));
        }
        if(Screen.hasControlDown()){
            tooltip.add(Text.translatable("tooltip.wind-charged-weaponry.needle.ctrl_down"));
        }else{
            tooltip.add(Text.translatable("tooltip.wind-charged-weaponry.longsword.ctrl_up"));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        setSilk(stack, Math.min(getSilk(stack) + 2, 13));
        if (stack.getEnchantments().getEnchantments().contains(Functions.getEnchantmentEntry(target.getWorld(), ModEnchantments.REAPING_EFFECT))){
            setSilk(stack, Math.min(getSilk(stack) + 1, 13));
        }
        if (target instanceof SpiderEntity) {
            setSilk(stack, Math.min(getSilk(stack) + 1, 13));
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        return getSilk(stack);
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        if (getSilk(stack) == 13){
            return 0xffffff;
        } else {
            return 0xa2a2a2;
        }
    }

    public int getSilk(ItemStack stack) {
        return stack.getOrDefault(ModDataComponents.SILK, 0);
    }

    public void setSilk(ItemStack stack, int silk) {
        stack.set(ModDataComponents.SILK, silk);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        AttributeModifiersComponent component = stack.getOrDefault(DataComponentTypes.ATTRIBUTE_MODIFIERS,
                stack.getItem().getComponents().getOrDefault(DataComponentTypes.ATTRIBUTE_MODIFIERS,
                        AttributeModifiersComponent.DEFAULT));

        if (stack.getEnchantments().getEnchantments().contains(Functions.getEnchantmentEntry(world, ModEnchantments.REAPING_EFFECT))) { // THIS LINE RIGHT HERE THIS STUPID LINE IS BROKEN AAAAAAAAAAAAAHH
            component = component.with(EntityAttributes.GENERIC_ATTACK_DAMAGE,
                    new EntityAttributeModifier(Item.BASE_ATTACK_DAMAGE_MODIFIER_ID, 6F, EntityAttributeModifier.Operation.ADD_VALUE),
                    AttributeModifierSlot.MAINHAND);
            component = component.with(EntityAttributes.GENERIC_ATTACK_SPEED,
                    new EntityAttributeModifier(Item.BASE_ATTACK_SPEED_MODIFIER_ID, -2.6F, EntityAttributeModifier.Operation.ADD_VALUE),
                    AttributeModifierSlot.MAINHAND);
        } else {
            component = component.with(EntityAttributes.GENERIC_ATTACK_DAMAGE,
                    new EntityAttributeModifier(Item.BASE_ATTACK_DAMAGE_MODIFIER_ID, 5F, EntityAttributeModifier.Operation.ADD_VALUE),
                    AttributeModifierSlot.MAINHAND);
            component = component.with(EntityAttributes.GENERIC_ATTACK_SPEED,
                    new EntityAttributeModifier(Item.BASE_ATTACK_SPEED_MODIFIER_ID, -2.1F, EntityAttributeModifier.Operation.ADD_VALUE),
                    AttributeModifierSlot.MAINHAND);
        }
        component = component.with(EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE,
                new EntityAttributeModifier(Identifier.of("01760170367810"), 0.5F, EntityAttributeModifier.Operation.ADD_VALUE),
                AttributeModifierSlot.MAINHAND);
        stack.set(DataComponentTypes.ATTRIBUTE_MODIFIERS, component);
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    /*
    AttributeModifiersComponent component = stack.getOrDefault(DataComponentTypes.ATTRIBUTE_MODIFIERS,
                        stack.getItem().getComponents().getOrDefault(DataComponentTypes.ATTRIBUTE_MODIFIERS,
                                AttributeModifiersComponent.DEFAULT));
                component = component.with(EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(Item.BASE_ATTACK_DAMAGE_MODIFIER_ID, 5F, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND);
                stack.set(DataComponentTypes.ATTRIBUTE_MODIFIERS, component);
    */
}
