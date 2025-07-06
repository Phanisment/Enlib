package io.phanisment.enlib.item;

import net.minecraft.item.Item;
import net.minecraft.util.TypedActionResult;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3f;
import io.phanisment.enlib.particle.FlatParticleType;
import io.phanisment.enlib.particle.EnlibParticleTypes;

public class CustomItem extends Item {
	public CustomItem() {
		super(new Item.Settings());
	}
	
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		if (!world.isClient()) {
			Vec3d pos = user.getPos();
			world.addParticle(new FlatParticleType(new Vector3f(90.0F, 0.0F, 0.0F), 1.0F), pos.getX(), pos.getY() + 0.5D, pos.getZ(), 0, 0, 0);
		}
		
		ItemStack itemStack = user.getStackInHand(hand);
		return TypedActionResult.success(itemStack, world.isClient());
	}
}