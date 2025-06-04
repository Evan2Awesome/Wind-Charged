package net.wind_weaponry.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.wind_weaponry.entity.custom.WindGolemEntity;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderer;

public class WindGolemRenderer extends GeoEntityRenderer<WindGolemEntity> {
    public WindGolemRenderer(EntityRendererFactory.Context context) {
        super(context, new WindGolemModel());
    }
}