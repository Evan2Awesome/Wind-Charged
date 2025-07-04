package net.wind_weaponry.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.wind_weaponry.WindChargedWeaponry;
import net.wind_weaponry.item.ModItems;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.WIND_LONGSWORD)
                .pattern("  B")
                .pattern("CB ")
                .pattern("NC ")
                .input('B', Items.BREEZE_ROD)
                .input('C', Items.COPPER_INGOT)
                .input('N', Items.NETHERITE_INGOT)
                .criterion(hasItem(Items.NETHERITE_INGOT), conditionsFromItem(Items.NETHERITE_INGOT))
                .offerTo(recipeExporter, Identifier.of(WindChargedWeaponry.MOD_ID, "wind_longsword"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.WIND_GAUNTLET)
                .pattern("LNW")
                .pattern("LCW")
                .pattern("LNW")
                .input('W', Items.WIND_CHARGE)
                .input('C', Items.COPPER_BLOCK)
                .input('N', Items.NETHERITE_SCRAP)
                .input('L', Items.LEATHER)
                .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
                .offerTo(recipeExporter, Identifier.of(WindChargedWeaponry.MOD_ID, "wind_gauntlet"));

    }
}
