package vehicle.vehicle.vehicles;

import org.bukkit.Material;
import org.bukkit.Sound;

import vehicle.vehicle.RelativeBlock;
import vehicle.vehicle.Vehicle;

public class TestB extends Vehicle {

	public TestB() {
		super("TestB");
//		super.addRelativeBlocks(new RelativeBlock(1.8, -1.2, 0, Material.GOLD_BLOCK, 10, 10));
	}

	@Override
	public Sound getSound() {
		return Sound.BLOCK_ANVIL_BREAK;
	}

	@Override
	public double getMaxSpeed() {
		return .2;
	}

}
