package net.wind_weaponry.util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;

public class Functions {
    public static RegistryEntry<Enchantment> getEnchantmentEntry(World world, RegistryKey<Enchantment> enchantmentKey){
        Registry<Enchantment> ERE = world.getRegistryManager().get(RegistryKeys.ENCHANTMENT);
        Enchantment enchantment = ERE.get(enchantmentKey.getValue());
        return ERE.getEntry(enchantment);
    }
}
