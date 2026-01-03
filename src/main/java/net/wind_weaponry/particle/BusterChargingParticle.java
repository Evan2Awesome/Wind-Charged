package net.wind_weaponry.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;

public class BusterChargingParticle extends SpriteBillboardParticle {
    private final SpriteProvider spriteProvider;

    public BusterChargingParticle(ClientWorld clientWorld, double x, double y, double z,
                                  SpriteProvider spriteProvider, double xSpeed, double ySpeed, double zSpeed) {
        super(clientWorld, x, y, z, xSpeed, ySpeed, zSpeed);

        this.maxAge = 8;

        this.spacingXZ = 0;
        this.spacingY = 0;

        this.spriteProvider = spriteProvider;
        this.setSpriteForAge(spriteProvider);
        this.gravityStrength = (float) -0.5;


        this.collidesWithWorld = false;
    }

    @Override
    public void tick() {
        this.setSpriteForAge(this.spriteProvider);
        super.tick();
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_LIT;
    }

    @Override
    public int getBrightness(float tint) {
        return 15728880;
    }

    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public @Nullable Particle createParticle(SimpleParticleType parameters, ClientWorld world, double x, double y, double z,
                                                 double velocityX, double velocityY, double velocityZ) {
            return new BusterChargingParticle(world, x, y, z, this.spriteProvider, velocityX, velocityY, velocityZ);
        }
    }
}
