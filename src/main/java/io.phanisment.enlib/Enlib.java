package io.phanisment.enlib;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.phanisment.enlib.particle.EnlibParticleTypes;

public class Enlib implements ModInitializer {
	public static final String id = "enlib";
	public static final Logger logger = LoggerFactory.getLogger(id);
	
	@Override
	public void onInitialize() {
		new EnlibParticleTypes();
	}
}