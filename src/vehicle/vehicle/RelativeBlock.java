package vehicle.vehicle;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class RelativeBlock {

	private double distance;
	private float relativeYaw, relativePitch, pitch, yaw;
	
	private ItemStack item;
	
	public RelativeBlock(float relativeYaw, float relativePitch, double distance, ItemStack item){
		this.relativeYaw = relativeYaw;
		this.distance = distance;
		this.item = item;
		this.relativePitch = relativePitch;
	}
	
	public RelativeBlock(float relativeYaw, float relativePitch, double distance, Material item){
		this(relativeYaw, relativePitch, distance, new ItemStack(item));
	}
	
	public RelativeBlock(float relativeYaw, float relativePitch, double distance, ItemStack item, float pitch, float yaw){
		this(relativeYaw, relativePitch, distance, item);
		this.pitch = pitch;
		this.yaw = yaw;
	}
	
	public RelativeBlock(float relativeYaw, float relativePitch, double distance, Material item, float pitch, float yaw){
		this(relativeYaw, relativePitch, distance, new ItemStack(item), pitch, yaw);
	}
	
	@Deprecated
	public RelativeBlock(double x, double y, double z, Material item, float pitch, float yaw){
		double distance = Math.sqrt(Math.pow(x, 2) + Math.pow(z, 2) + Math.pow(z, 2));
		double xDist = Math.sqrt(Math.pow(x, 2) + Math.pow(z, 2));
		double relativeYaw = Math.atan2(x, z);
		double relativePitch = Math.atan2(y, xDist);
		this.relativeYaw = (float)relativeYaw;
		this.relativePitch = (float)relativePitch;
		this.distance = distance;
		this.item = new ItemStack(item);
		this.pitch = pitch;
		this.yaw = yaw;
	}
	
	public double getDistance(){
		return distance;
	}
	
	public float getRelativeYaw(){
		return relativeYaw;
	}
	
	public float getRelativePitch(){
		return relativePitch;
	}
	
	public float getPitch(){
		return pitch;
	}
	
	public float getYaw(){
		return yaw;
	}
	
	public ItemStack getItem(){
		return item;
	}
	
}
