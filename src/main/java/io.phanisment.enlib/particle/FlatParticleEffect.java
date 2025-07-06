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
		this.rotation = rotation;
		this.sprite = sprite;
		this.setSpriteForAge(sprite);
		this.setAlpha(1.0F);
	}
	
	@Override
	public void tick() {
		this.setSpriteForAge(sprite);
	}
	
	@Override
	public ParticleTextureSheet getType() {
		return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
	}
	
	@Override
	public void buildGeometry(VertexConsumer vertexConsumer, Camera camera, float tick) {
		float size = this.getSize(tick) * this.scale;
		
		float px = (float)(this.x - camera.getPos().x);
		float py = (float)(this.y - camera.getPos().y);
		float pz = (float)(this.z - camera.getPos().z);
		
		float u0 = this.getMinU();
		float u1 = this.getMaxU();
		float v0 = this.getMinV();
		float v1 = this.getMaxV();
		int light = this.getBrightness(tick);
		
		Quaternionf rotation = new Quaternionf().rotateXYZ(
			(float)Math.toRadians(this.rotation.x),
			(float)Math.toRadians(this.rotation.y),
			(float)Math.toRadians(this.rotation.z)
		);
		
		Vector3f[] originalCorners = new Vector3f[] {
			new Vector3f(-size, 0, -size),
			new Vector3f( size, 0, -size),
			new Vector3f( size, 0,  size),
			new Vector3f(-size, 0,  size)
		};
		
		Vector3f[] rotated = new Vector3f[4];
		for (int i = 0; i < 4; i++) {
			rotated[i] = new Vector3f(originalCorners[i]);
			rotation.transform(rotated[i]);
		}
		
		vertexConsumer.vertex(px + rotated[0].x, py + rotated[0].y, pz + rotated[0].z).texture(u0, v1).color(red, green, blue, alpha).light(light);
		vertexConsumer.vertex(px + rotated[1].x, py + rotated[1].y, pz + rotated[1].z).texture(u1, v1).color(red, green, blue, alpha).light(light);
		vertexConsumer.vertex(px + rotated[2].x, py + rotated[2].y, pz + rotated[2].z).texture(u1, v0).color(red, green, blue, alpha).light(light);
		vertexConsumer.vertex(px + rotated[3].x, py + rotated[3].y, pz + rotated[3].z).texture(u0, v0).color(red, green, blue, alpha).light(light);
		
		float offsetY = 0.001f;
		vertexConsumer.vertex(px + rotated[3].x, py + rotated[3].y + offsetY, pz + rotated[3].z).texture(u0, v0).color(red, green, blue, alpha).light(light);
		vertexConsumer.vertex(px + rotated[2].x, py + rotated[2].y + offsetY, pz + rotated[2].z).texture(u1, v0).color(red, green, blue, alpha).light(light);
		vertexConsumer.vertex(px + rotated[1].x, py + rotated[1].y + offsetY, pz + rotated[1].z).texture(u1, v1).color(red, green, blue, alpha).light(light);
		vertexConsumer.vertex(px + rotated[0].x, py + rotated[0].y + offsetY, pz + rotated[0].z).texture(u0, v1).color(red, green, blue, alpha).light(light);
	}
	
	public static record Factory(SpriteProvider sprite) implements ParticleFactory<FlatParticleType> {
		@Override
		public Particle createParticle(FlatParticleType prams, ClientWorld world, double x, double y, double z, double vX, double vY, double vZ) {
			FlatParticleEffect p = new FlatParticleEffect(world, x, y, z, prams.getRotation(), prams.getScale(), this.sprite());
			return p;
		}
	}
}