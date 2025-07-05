package io.phanisment.enlib;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

import io.phanisment.enlib.particle.FlatParticle;

public class Client implements ClientModInitializer {
	
	@Override
	public void onInitializeClient() {
		ParticleFactoryRegistry.getInstance().register(Enlib.FLAT, FlatParticle.Factory::new);
	}
}