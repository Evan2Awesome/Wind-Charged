package net.wind_weaponry.item.custom;

import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.wind_weaponry.enchantment.ModEnchantments;
import net.wind_weaponry.item.ModItems;
import org.xml.sax.ext.Attributes2;

import java.util.*;
import java.util.function.BiConsumer;

public class LongswordItem extends SwordItem{

    public static int getBreakTime(LivingEntity livingEntity){//added this for compatibility with NTRDeal's Items
        return 50;
    }

    public LongswordItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    public static float getModifiedDamage(ItemStack stack, LivingEntity entity){
        float amount = 0;

        if (stack.isOf(ModItems.WIND_LONGSWORD)){
            if (!entity.getOffHandStack().isOf(ModItems.WIND_GAUNTLET) && !entity.getOffHandStack().isEmpty()) amount -= 1;
        }

        return amount;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);
        if (!user.getStackInHand(Hand.OFF_HAND).isOf(Items.SHIELD))
            user.setCurrentHand(hand);
        if (user.getStackInHand(Hand.OFF_HAND).isOf(ModItems.WIND_GAUNTLET)) {
            user.getWorld().playSound(user, user.getX(),user.getY(),user.getZ(),
                    SoundEvents.ENTITY_BREEZE_CHARGE, SoundCategory.PLAYERS, 1.0f, 1.0f);
            for (int i=0;i<16;i++) {
                world.addParticle(ParticleTypes.POOF, user.getX() + (world.random.nextBetween(-3, 3)), user.getY() + (world.random.nextBetweenExclusive(-3, 3)) + 1.0, user.getZ() + (world.random.nextBetweenExclusive(-3, 3)), 0.0, 0.1, 0.0);
            }
        }
        return TypedActionResult.consume(itemStack);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (user.getStackInHand(Hand.OFF_HAND).isOf(ModItems.WIND_GAUNTLET) && !Objects.requireNonNull(user.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)).hasModifier(Identifier.of("105316153205"))) {
            Objects.requireNonNull(user.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)).addTemporaryModifier(new EntityAttributeModifier(
                    Identifier.of("105316153205"),0.2F,EntityAttributeModifier.Operation.ADD_VALUE));
        }
        super.usageTick(world, user, stack, remainingUseTicks);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BLOCK;
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        Objects.requireNonNull(user.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)).removeModifier(Identifier.of("105316153205"));
        PlayerEntity temp = (PlayerEntity) user;
        if (user.getStackInHand(Hand.OFF_HAND).isOf(ModItems.WIND_GAUNTLET))
            temp.getItemCooldownManager().set(this, 20);
        else
            temp.getItemCooldownManager().set(this, 100);
        super.onStoppedUsing(stack, world, user, remainingUseTicks);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        Objects.requireNonNull(user.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)).removeModifier(Identifier.of("105316153205"));
        PlayerEntity temp = (PlayerEntity) user;
        temp.getItemCooldownManager().set(this, 100);
        return super.finishUsing(stack, world, user);
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        if (user.getStackInHand(Hand.OFF_HAND).isOf(ModItems.WIND_GAUNTLET))
            return 72000;
        else
            return 30;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (stack.isOf(ModItems.WIND_LONGSWORD)){
            if(Screen.hasShiftDown()){
                tooltip.add(Text.translatable("tooltip.wind-charged-weaponry.longsword.shift_down"));
            }else{
                tooltip.add(Text.translatable("tooltip.wind-charged-weaponry.longsword.shift_up"));
            }
        } else if (stack.isOf(ModItems.NEEDLE)) {

        }
        if(Screen.hasControlDown()){
            tooltip.add(Text.empty());
            tooltip.add(Text.translatable("tooltip.wind-charged-weaponry.longsword.ctrl_down"));
        }else{
            tooltip.add(Text.translatable("tooltip.wind-charged-weaponry.longsword.ctrl_up"));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity instanceof LivingEntity user){
            if (!user.isBlocking() && user.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED) instanceof EntityAttributeInstance instance){
                instance.removeModifier(Identifier.of("105316153205"));
            }
            AttributeModifiersComponent component = stack.getOrDefault(DataComponentTypes.ATTRIBUTE_MODIFIERS,
                    stack.getItem().getComponents().getOrDefault(DataComponentTypes.ATTRIBUTE_MODIFIERS,
                            AttributeModifiersComponent.DEFAULT));
            if (!user.getOffHandStack().isOf(ModItems.WIND_GAUNTLET) && !user.getOffHandStack().isEmpty()){
                component = component.with(EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(Item.BASE_ATTACK_DAMAGE_MODIFIER_ID, 5F, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND);
            } else {
                component = component.with(EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(Item.BASE_ATTACK_DAMAGE_MODIFIER_ID, 6F, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND);
            }
            component = component.with(EntityAttributes.GENERIC_ATTACK_SPEED,
                    new EntityAttributeModifier(Item.BASE_ATTACK_SPEED_MODIFIER_ID, -2.6F, EntityAttributeModifier.Operation.ADD_VALUE),
                    AttributeModifierSlot.MAINHAND);
            component = component.with(EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE,
                    new EntityAttributeModifier(Identifier.of("2906341806710"), 0.5F, EntityAttributeModifier.Operation.ADD_VALUE),
                    AttributeModifierSlot.MAINHAND);
            stack.set(DataComponentTypes.ATTRIBUTE_MODIFIERS, component);
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }
}
