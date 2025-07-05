package io.phanisment.enlib;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.fabricmc.api.ModInitializer;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Enlib implements ModInitializer {
	public static final String id = "enlib";
	public static final Logger logger = LoggerFactory.getLogger(id);
	public static final SimpleParticleType FLAT = FabricParticleTypes.simple();
	
	@Override
	public void onInitialize() {
		Registry.register(Registries.PARTICLE_TYPE, Identifier.of(id, "flat"), FLAT);
	}
}