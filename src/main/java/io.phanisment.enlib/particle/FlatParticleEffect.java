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

import org.joml.Quaternionf;
import org.joml.Vector3f;

@Environment(EnvType.CLIENT)
public class FlatParticleEffect extends SpriteBillboardParticle {
	private final Vector3f rotation;
	private final SpriteProvider sprite;
	
	public FlatParticleEffect(ClientWorld world, double x, double y, double z, Vector3f rotation, float scale, SpriteProvider sprite) {
		super(world, x, y, z, 0.0D, 0.0D, 0.0D);
		this.scale = scale;
		this.maxAge = 40;
		this.rotation = rotation;
		this.sprite = sprite;
		this.setSpriteForAge(sprite);
		this.setAlpha(1.0F);
	}
	
	@Override
	public void tick() {
		super.tick();
		this.setSpriteForAge(sprite);
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
	
	public static record Factory(SpriteProvider sprite) implements ParticleFactory<FlatParticleType> {
		@Override
		public Particle createParticle(FlatParticleType prams, ClientWorld world, double x, double y, double z, double vX, double vY, double vZ) {
			FlatParticleEffect p = new FlatParticleEffect(world, x, y, z, prams.getRotation(), prams.getScale(), this.sprite());
			return p;
		}
	}
}