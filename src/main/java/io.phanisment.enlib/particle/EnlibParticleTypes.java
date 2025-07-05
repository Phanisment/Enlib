package io.phanisment.enlib.particle;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import java.util.function.Function;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleEffect;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;

public class EnlibParticleTypes {
	public static final ParticleType<FlatParticleType> FLAT = FabricParticleTypes.complex(false, FlatParticleType.CODEC, FlatParticleType.PACKET_CODEC);
	
	public EnlibParticleTypes() {
		Registry.register(Registries.PARTICLE_TYPE, "flat", FLAT);
	}
}