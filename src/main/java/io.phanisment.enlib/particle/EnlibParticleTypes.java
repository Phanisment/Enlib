package io.phanisment.enlib.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.particle.ParticleType;
import net.minecraft.util.Identifier;

import io.phanisment.enlib.Main;

public class EnlibParticleTypes {
	public static final ParticleType<FlatParticleType> FLAT = FabricParticleTypes.complex(false, FlatParticleType.CODEC, FlatParticleType.PACKET_CODEC);
	
	public EnlibParticleTypes() {
		Registry.register(Registries.PARTICLE_TYPE, Identifier.of(Main.id, "flat"), FLAT);
	}
}