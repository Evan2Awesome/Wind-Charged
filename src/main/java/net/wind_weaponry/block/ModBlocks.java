package net.wind_weaponry.block;

import net.minecraft.block.RedstoneLampBlock;
import net.minecraft.sound.BlockSoundGroup;
import net.wind_weaponry.WindChargedWeaponry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block TORNADO_GENERATOR = registerBlock("tornado_generator",
            new Block(AbstractBlock.Settings.create()
                    .strength(-1,180000).dropsNothing().sounds(BlockSoundGroup.COPPER)
            ));

    public static final Block BLUE_LAMP = registerBlock("blue_lamp",
            new RedstoneLampBlock(AbstractBlock.Settings.create()
                    .strength(-1,180000).dropsNothing().sounds(BlockSoundGroup.GLASS)
            ));

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name,block);
        return Registry.register(Registries.BLOCK,Identifier.of(WindChargedWeaponry.MOD_ID,name), block);
    }

    private static  void registerBlockItem(String name, Block block){
        Registry.register(Registries.ITEM, Identifier.of(WindChargedWeaponry.MOD_ID, name),
            new BlockItem(block,new Item.Settings()));
    }

    public static void registerModBlocks(){
        WindChargedWeaponry.LOGGER.info("Registering mod blocks for " + WindChargedWeaponry.MOD_ID);
    }

}
