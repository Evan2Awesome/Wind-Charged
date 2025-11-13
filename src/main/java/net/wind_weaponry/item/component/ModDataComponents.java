package net.wind_weaponry.item.component;

import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;
import net.wind_weaponry.WindChargedWeaponry;

import java.util.function.UnaryOperator;

public class ModDataComponents {
    public static final ComponentType<Integer> SILK = register("silk",
            builder -> builder.codec(Codecs.NONNEGATIVE_INT).packetCodec(PacketCodecs.VAR_INT));
    public static final ComponentType<Boolean> THREAD_STORM_COMPONENT = register("thread_storm_component",
            builder -> builder.codec(Codec.BOOL));

    private static <T> ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(WindChargedWeaponry.MOD_ID, name),
                builderOperator.apply(ComponentType.builder()).build());
    }

    public static void register() {
        WindChargedWeaponry.LOGGER.info("Registering Data Component Types for " + WindChargedWeaponry.MOD_ID);
    }
}
