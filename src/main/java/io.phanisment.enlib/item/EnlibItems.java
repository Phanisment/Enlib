package io.phanisment.enlib.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import io.phanisment.enlib.Enlib;

public class EnlibItems {
	public static final Item test = i("test", new CustomItem());
	
	private static Item i(String name, Item item) {
		return Registry.register(Registries.ITEM, Identifier.of(Enlib.id, name), item);
	}
}