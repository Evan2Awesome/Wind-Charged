package net.wind_weaponry;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.util.Identifier;
import net.wind_weaponry.enchantment.ModEnchantmentEffects;
import net.wind_weaponry.enchantment.ModEnchantments;
import net.wind_weaponry.entity.ModEntities;
import net.wind_weaponry.entity.custom.WindGolemEntity;
import net.wind_weaponry.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WindChargedWeaponry implements ModInitializer {
	public static final String MOD_ID = "wind-charged-weaponry";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
		ModItems.registerModItems();
		ModGroup.registerItemGroups();
		ModEntities.registerModEntities();
		ModEnchantmentEffects.registerEnchantmentEffects();

		FabricDefaultAttributeRegistry.register(ModEntities.WIND_GOLEM_ENTITY_ENTITY_TYPE, WindGolemEntity.createAttributes());
	}

	public static Identifier id(String id) {
		return Identifier.of(MOD_ID, id);
	}
}