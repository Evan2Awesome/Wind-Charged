package net.wind_weaponry.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.wind_weaponry.WindChargedWeaponry;
import net.wind_weaponry.entity.custom.WindGolemEntity;

public class ModEntities {

    public static final EntityType<WindGolemEntity> WIND_GOLEM_ENTITY_ENTITY_TYPE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(WindChargedWeaponry.MOD_ID, "wind_golem"),
            EntityType.Builder.create(WindGolemEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f,1.4f).build());

    public static void registerModEntities() {
        WindChargedWeaponry.LOGGER.info("Registering Mod Entities for" + WindChargedWeaponry.MOD_ID);
    }
}
