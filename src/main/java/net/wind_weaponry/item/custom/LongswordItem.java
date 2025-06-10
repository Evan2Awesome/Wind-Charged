package net.wind_weaponry.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class LongswordItem extends SwordItem {
    public LongswordItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if(Screen.hasShiftDown()){
            tooltip.add(Text.translatable("tooltip.wind-charged-weaponry.longsword.shift_down"));
        }else{
            tooltip.add(Text.translatable("tooltip.wind-charged-weaponry.longsword"));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }
}
