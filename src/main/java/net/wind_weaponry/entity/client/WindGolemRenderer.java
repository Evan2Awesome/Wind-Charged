package net.wind_weaponry.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.wind_weaponry.entity.custom.WindGolemEntity;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class WindGolemRenderer extends GeoEntityRenderer<WindGolemEntity> {
    public WindGolemRenderer(EntityRendererFactory.Context context) {
        super(context, new WindGolemModel());
        addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }

    @Override
    protected float getDeathMaxRotation(WindGolemEntity animatable) {
        return 0F;
    }
}