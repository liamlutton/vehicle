package vehicle.vehicle;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Sound;

public abstract class Vehicle {
	
	public enum Movement {
		
		GROUND, WATER, AIR, RAIL;
		
	}
	
	private String name;
	private Movement movement = Movement.GROUND;
	List<RelativeBlock> relativeBlocks = new ArrayList<>();
	
	public Vehicle(String name){
		this.name = name;
	}
	
	public Vehicle(String name, Movement movement){
		this(name);
		this.movement = movement;
	}
	
	protected void addRelativeBlocks(RelativeBlock...blocks){
		for(RelativeBlock block : blocks){
			this.relativeBlocks.add(block);
		}
	}
	
	public String getName(){
		return name;
	}
	
	public Movement getMovement(){
		return movement;
	}
	
	public List<RelativeBlock> getRelativeBlocks(){
		return this.relativeBlocks;
	}
	
	public abstract Sound getSound();
	public abstract double getMaxSpeed();
	
}
