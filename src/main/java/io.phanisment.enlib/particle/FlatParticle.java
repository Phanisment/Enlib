package io.phanisment.enlib.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.Camera;

@Environment(EnvType.CLIENT)
public class FlatParticle extends SpriteBillboardParticle {
	
	public TestParticle(ClientWorld world, double x, double y, double z) {
		super(world, x, y, z, 0.0D, 0.0D, 0.0D);
		this.scale = 1f;
		this.maxAge = 40;
	}
	
	@Override
	public void tick() {
		super.tick();
	}
	
	@Override
	public ParticleTextureSheet getType() {
		return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
	}
	
	@Override
	public void buildGeometry(VertexConsumer vertexConsumer, Camera camera, float ticks) {
		float size = this.getSize(ticks) * scale;
		float px = (float)(this.x - camera.getPos().x);
		float py = (float)(this.y - camera.getPos().y);
		float pz = (float)(this.z - camera.getPos().z);
		
		float u0 = this.getMinU();
		float u1 = this.getMaxU();
		float v0 = this.getMinV();
		float v1 = this.getMaxV();
		
		int light = this.getBrightness(ticks);
		
		vertexConsumer.vertex(px - size, py, pz - size).texture(u0, v1).color(red, green, blue, alpha).light(light);
		vertexConsumer.vertex(px + size, py, pz - size).texture(u1, v1).color(red, green, blue, alpha).light(light);
		vertexConsumer.vertex(px + size, py, pz + size).texture(u1, v0).color(red, green, blue, alpha).light(light);
		vertexConsumer.vertex(px - size, py, pz + size).texture(u0, v0).color(red, green, blue, alpha).light(light);
		
		vertexConsumer.vertex(px - size, py, pz + size).texture(u0, v0).color(red, green, blue, alpha).light(light);
		vertexConsumer.vertex(px + size, py, pz + size).texture(u1, v0).color(red, green, blue, alpha).light(light);
		vertexConsumer.vertex(px + size, py, pz - size).texture(u1, v1).color(red, green, blue, alpha).light(light);
		vertexConsumer.vertex(px - size, py, pz - size).texture(u0, v1).color(red, green, blue, alpha).light(light);
	}
	
	public static class Factory implements ParticleFactory<SimpleParticleType> {
		private final SpriteProvider spriteProvider;
		
		public Factory(SpriteProvider spriteProvider) {
			this.spriteProvider = spriteProvider;
		}
		
		@Override
		public Particle createParticle(SimpleParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
			TestParticle p = new TestParticle(world, x, y, z);
			p.setAlpha(1.0f);
			p.setSpriteForAge(spriteProvider);
			return p;
		}
	}
}