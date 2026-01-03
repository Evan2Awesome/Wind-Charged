package net.wind_weaponry.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.wind_weaponry.WindChargedWeaponry;

public class ModParticles {

    public static final SimpleParticleType BUSTER_CHARGING_PARTICLE =
            registerParticle("buster_charging_particle", FabricParticleTypes.simple(true));

    private static SimpleParticleType registerParticle(String name, SimpleParticleType particleType) {
        return Registry.register(Registries.PARTICLE_TYPE, Identifier.of(WindChargedWeaponry.MOD_ID, name), particleType);
    }

    public static void registerParticles() {
        WindChargedWeaponry.LOGGER.info("Registering Particles for" + WindChargedWeaponry.MOD_ID);
    }
}
