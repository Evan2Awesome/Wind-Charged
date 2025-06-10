package net.wind_weaponry.item;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.wind_weaponry.WindChargedWeaponry;
import net.minecraft.block.Block;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.wind_weaponry.item.custom.GaunletItem;
import net.wind_weaponry.item.custom.LongswordItem;

import java.util.ArrayList;
import java.util.List;

public class ModItems {
    //copy line below to add items
    //make sure to update en_us.json, textures/item/<item_name.png>, and add it to itemgroup
    //public static final Item EXAMPLE = registerItem("example", new Item(new Item.Settings()));
    public static final Item WIND_HAMMER = registerItem("wind_hammer", new MaceItem(new Item.Settings()
            .attributeModifiers(new AttributeModifiersComponent(List.of(
                    new AttributeModifiersComponent.Entry(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(
                            Item.BASE_ATTACK_DAMAGE_MODIFIER_ID,4.5F,EntityAttributeModifier.Operation.ADD_VALUE
                    ),AttributeModifierSlot.MAINHAND)),true))));

    public static final Item WIND_GAUNLET = registerItem("wind_gaunlet", new GaunletItem(new Item.Settings()
            .attributeModifiers(new AttributeModifiersComponent(List.of(
                    new AttributeModifiersComponent.Entry(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(
                            Item.BASE_ATTACK_DAMAGE_MODIFIER_ID,2,EntityAttributeModifier.Operation.ADD_VALUE
                    ),AttributeModifierSlot.MAINHAND)),true))
            .maxCount(1)));

    public static final Item WIND_LONGSWORD = registerItem("wind_longsword", new LongswordItem(ModToolMaterials.WIND_CHARGED, new Item.Settings()
            .attributeModifiers(new AttributeModifiersComponent(List.of(
                    new AttributeModifiersComponent.Entry(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(
                            Item.BASE_ATTACK_DAMAGE_MODIFIER_ID,6F,EntityAttributeModifier.Operation.ADD_VALUE
                    ),AttributeModifierSlot.MAINHAND),
                    new AttributeModifiersComponent.Entry(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(
                            Item.BASE_ATTACK_SPEED_MODIFIER_ID,-2.2F,EntityAttributeModifier.Operation.ADD_VALUE
                    ),AttributeModifierSlot.MAINHAND),
                    new AttributeModifiersComponent.Entry(EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE, new EntityAttributeModifier(
                            Identifier.of("2906341806710"),0.5F,EntityAttributeModifier.Operation.ADD_VALUE
                    ),AttributeModifierSlot.MAINHAND)),true))

    ));


    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(WindChargedWeaponry.MOD_ID,name),item);
    }

    public static void registerModItems(){
        WindChargedWeaponry.LOGGER.info("Registering mod items for " + WindChargedWeaponry.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SEARCH).register(fabricItemGroupEntries -> {
            //fabricItemGroupEntries.add(EXAMPLE);
            fabricItemGroupEntries.add(WIND_LONGSWORD);
            fabricItemGroupEntries.add(WIND_GAUNLET);
            fabricItemGroupEntries.add(WIND_HAMMER);
        });
    }
}
