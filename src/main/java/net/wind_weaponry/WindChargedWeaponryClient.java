package net.wind_weaponry;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.wind_weaponry.entity.ModEntities;
import net.wind_weaponry.entity.client.WindGolemModel;
import net.wind_weaponry.entity.client.WindGolemRenderer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Entity;
import net.wind_weaponry.entity.custom.WindGolemEntity;

import java.util.function.Supplier;

public class WindChargedWeaponryClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.WIND_GOLEM_ENTITY_ENTITY_TYPE, WindGolemRenderer::new);
    }
}
