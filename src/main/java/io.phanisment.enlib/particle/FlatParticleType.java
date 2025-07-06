package io.phanisment.enlib.particle;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.dynamic.Codecs;
import org.joml.Vector3f;

public class FlatParticleType implements ParticleEffect {
	public static final MapCodec<FlatParticleType> CODEC;
	public static final PacketCodec<RegistryByteBuf, FlatParticleType> PACKET_CODEC;
	
	private final Vector3f rotation;
	private final float scale;
	
	public FlatParticleType(Vector3f rotation, float scale) {
		this.rotation = rotation;
		this.scale = scale;
	}
	
	public Vector3f getRotation() {
		return this.rotation;
	}
	
	public float getScale() {
		return this.scale;
	}
	
	@Override
	public ParticleType<FlatParticleType> getType() {
		return EnlibParticleTypes.FLAT;
	}
	
	static {
		CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
			Codecs.VECTOR_3F.fieldOf("rotation").forGetter(FlatParticleType::getRotation),
			Codec.FLOAT.fieldOf("size").forGetter(FlatParticleType::getScale)
		).apply(i, FlatParticleType::new));
		
		PACKET_CODEC = PacketCodec.tuple(
			PacketCodecs.VECTOR3F, FlatParticleType::getRotation,
			PacketCodecs.FLOAT, FlatParticleType::getScale,
			FlatParticleType::new
		);
	}
}