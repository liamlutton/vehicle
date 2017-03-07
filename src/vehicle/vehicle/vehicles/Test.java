package vehicle.vehicle.vehicles;

import org.bukkit.Material;
import org.bukkit.Sound;

import vehicle.vehicle.RelativeBlock;
import vehicle.vehicle.Vehicle;

public class Test extends Vehicle {

	public Test() {
		super("Test", Movement.AIR);
		super.addRelativeBlocks(
				//Cockpit
				new RelativeBlock(0, 0, 1, Material.GLASS),
				new RelativeBlock(90, 0, 1, Material.IRON_BLOCK),
				new RelativeBlock(180, 0, 1, Material.IRON_BLOCK),
				new RelativeBlock(270, 0, 1, Material.IRON_BLOCK),
				
				new RelativeBlock(45, 0, .94, Material.GLASS),
				new RelativeBlock(135, 0, .94, Material.IRON_BLOCK),
				new RelativeBlock(225, 0, .94, Material.IRON_BLOCK),
				new RelativeBlock(315, 4, .94, Material.GLASS),
				
				//Bottom layer
				new RelativeBlock(0, 30, 1.16, Material.IRON_BLOCK),
				new RelativeBlock(90, 30, 1.16, Material.IRON_BLOCK),
				new RelativeBlock(180, 30, 1.16, Material.IRON_BLOCK),
				new RelativeBlock(270, 30, 1.16, Material.IRON_BLOCK),
				
				new RelativeBlock(45, 30, 1.1, Material.IRON_BLOCK),
				new RelativeBlock(135, 30, 1.1, Material.IRON_BLOCK),
				new RelativeBlock(225, 30, 1.1, Material.IRON_BLOCK),
				new RelativeBlock(315, 30, 1.1, Material.IRON_BLOCK),
				
				//Roof
				new RelativeBlock(0, -30, 1.16, Material.IRON_BLOCK),
				new RelativeBlock(90, -30, 1.16, Material.IRON_BLOCK),
				new RelativeBlock(180, -30, 1.16, Material.IRON_BLOCK),
				new RelativeBlock(270, -30, 1.16, Material.IRON_BLOCK),
				
				new RelativeBlock(45, -30, 1.1, Material.IRON_BLOCK),
				new RelativeBlock(135, -30, 1.1, Material.IRON_BLOCK),
				new RelativeBlock(225, -30, 1.1, Material.IRON_BLOCK),
				new RelativeBlock(315, -30, 1.1, Material.IRON_BLOCK),
		
				//Floor
				new RelativeBlock(0, 60, .96, Material.IRON_PLATE),
				new RelativeBlock(90, 60, .96, Material.IRON_PLATE),
				new RelativeBlock(180, 60, .96, Material.IRON_PLATE),
				new RelativeBlock(270, 60, .96, Material.IRON_PLATE),
				
				//Top
				new RelativeBlock(270, -60, 1.1, Material.IRON_BLOCK),
				new RelativeBlock(90, -60, 1.1, Material.IRON_BLOCK),
				new RelativeBlock(0, -60, 1.1, Material.IRON_BLOCK),
				new RelativeBlock(0, -90, .8, Material.IRON_BLOCK),

				//Tail
				new RelativeBlock(170, -30, 2.1, Material.BANNER, 180, 180),
				new RelativeBlock(180, -60, 1.16, Material.IRON_BLOCK),
				new RelativeBlock(180, -45, 1.6, Material.IRON_BLOCK),
				new RelativeBlock(180, -30, 2, Material.IRON_BLOCK));
	}

	@Override
	public Sound getSound() {
		return Sound.BLOCK_GRASS_STEP;
	}

	@Override
	public double getMaxSpeed() {
		return 0.5;
	}

}
