package net.wind_weaponry.compatability;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.ntrdeal.ntrdeals_items.entity.ModAttributes;

public class NTRDealsItems {
    public static boolean NTRDEALS_ITEMS = FabricLoader.getInstance().isModLoaded("ntrdeals-items");

    public static int getSwordBreakTime(LivingEntity user){
        if (NTRDEALS_ITEMS){
            if (user instanceof PlayerEntity player){
                return Math.round(50 * (float) player.getAttributeValue(ModAttributes.SHIELD_FRAGILITY));
            } else return 50;
        } else return 50;
    }
}
