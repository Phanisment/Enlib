package io.phanisment.enlib;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

import io.phanisment.enlib.particle.EnlibParticleTypes;
import io.phanisment.enlib.particle.FlatParticleEffect;

public class Client implements ClientModInitializer {
	
	@Override
	public void onInitializeClient() {
		ParticleFactoryRegistry.getInstance().register(EnlibParticleTypes.FLAT, FlatParticleEffect.Factory::new);
	}
}