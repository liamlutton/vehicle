package vehicle.vehicle.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.entity.Silverfish;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import vehicle.core.VehicleCore;
import vehicle.vehicle.RelativeBlock;
import vehicle.vehicle.Vehicle;

public class VehicleEntity {

	private double speed = 0;
	
	private Vehicle vehicle;
	private Location location;
	
	private int timer;
	
	private Silverfish fish;
	private HashMap<RelativeBlock, ArmorStand> stands = new HashMap<>();
	
	public VehicleEntity(Vehicle vehicle){
		this.vehicle = vehicle;
		speed = vehicle.getMaxSpeed();
	}
	
	public void move(Player player){
		fish.setVelocity(location.getDirection().normalize().multiply(speed));
	}
	
	public void spawn(Location location){
		this.location = location;
		this.spawnVehicle();
	}
	
	public void mount(Player player){
		if(!this.hasPassenger()){
			this.fish.setPassenger(player);
		}else{
			player.sendMessage(ChatColor.RED + "This vehicle is already being ridden!");
		}
	}
	
	private void spawnVehicle(){
		location.setPitch(0);
		this.fish = location.getWorld().spawn(location, Silverfish.class);
		fish.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, false, false));
		fish.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 100, false, false));
		fish.setInvulnerable(true);
		
		
		for(RelativeBlock block : vehicle.getRelativeBlocks()){
			Location loc = fish.getLocation().clone().add(0,-.5,0);
			loc.setYaw(block.getRelativeYaw());
			loc.setPitch(block.getRelativePitch());
			Location f = loc.clone().add(loc.getDirection().normalize().multiply(block.getDistance()).toLocation(loc.getWorld()));
			
			f.setPitch(block.getPitch());
			f.setYaw(block.getYaw());
			ArmorStand stand = fish.getWorld().spawn(f, ArmorStand.class);
			stand.setVisible(false);
			stand.setHelmet(block.getItem());
			stand.setAI(false);
			stand.setGravity(false);
			stand.setInvulnerable(true);
						
			this.stands.put(block, stand);
		}
		startTimer();
	}
	
	private void startTimer(){
		timer = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(VehicleCore.getInstance(), new Runnable(){

			@Override
			public void run() {
				updateLocations();
			}
			
		}, 1, 1);
	}
	
	private void updateLocations(){
		this.location.setX(this.fish.getLocation().getX());
		this.location.setY(this.fish.getLocation().getY()-.5);
		this.location.setZ(this.fish.getLocation().getZ());
		for(RelativeBlock rb : stands.keySet()){
			Location loc = location.clone();
			loc.setYaw(rb.getRelativeYaw() + loc.getYaw());
			loc.setPitch(rb.getRelativePitch());
			
			Location f = loc.clone().add(loc.getDirection().normalize().multiply(rb.getDistance()).toLocation(loc.getWorld()));

			ArmorStand stand = stands.get(rb);
			f.setPitch(location.getPitch() + rb.getPitch());
			f.setYaw(location.getYaw() + rb.getYaw());
			stand.teleport(f);
		}
		
		if(!this.hasPassenger() || !(this.getFish().getPassenger() instanceof Player)){
			return;
		}
		Player player = (Player)this.getFish().getPassenger();
		if(fish.getLocation().getYaw() != player.getLocation().getYaw()){
			if(Math.abs(location.getYaw() - player.getLocation().getYaw()) < 2){
//				location.setYaw(player.getLocation().getYaw());
			}else{
				if(player.getLocation().getYaw() > location.getYaw()){
					location.setYaw(location.getYaw() + 2);
				}else{
					location.setYaw(location.getYaw() - 2);
				}
			}
//			fish.eject();
			fish.teleport(location);
//			fish.setPassenger(player);
		}
	}
	
	public void despawn(){
		VehicleCore.getInstance().getVehicleEntityManager().removeVehicle(this);
		Bukkit.getServer().getScheduler().cancelTask(this.timer);
		for(RelativeBlock rb : stands.keySet()){
			stands.get(rb).remove();
		}
		stands.clear();
		fish.remove();
	}
	
	public Vehicle getVehicle(){
		return this.vehicle;
	}
	
	public Location getLocation(){
		return this.location;
	}
	
	public boolean hasPassenger(){
		return fish.getPassenger() != null;
	}
	
	public Silverfish getFish(){
		return fish;
	}
	
	public List<ArmorStand> getStands(){
		List<ArmorStand> armorStands = new ArrayList<ArmorStand>();
		for(RelativeBlock rb : stands.keySet()){
			armorStands.add(stands.get(rb));
		}
		return armorStands;
	}
	
}
