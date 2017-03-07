package vehicle.vehicle.entities;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.util.Vector;

import vehicle.core.VehicleCore;
import vehicle.vehicle.Vehicle;

public class VehicleEntityManager implements Listener {

	private List<VehicleEntity> activeEntities = new ArrayList<>();
	private int vehicleCheckTimer;
	
	public VehicleEntityManager(){
		this.startTimer();
	}
	
	public void createVehicle(Location location, Vehicle vehicle){
		VehicleEntity ve = new VehicleEntity(vehicle);
		ve.spawn(location);
		this.activeEntities.add(ve);
	}
	
	public void removeVehicle(VehicleEntity vehicleEntity){
		this.activeEntities.remove(vehicleEntity);
	}
	
	public List<VehicleEntity> getVehicleEntities(){
		return activeEntities;
	}
	
	private void startTimer(){
		vehicleCheckTimer = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(VehicleCore.getInstance(), new Runnable(){

			@Override
			public void run() {
				for(VehicleEntity ve : activeEntities){
					if(ve.hasPassenger()){
						if(ve.getFish().getPassenger() instanceof Player){
							Player player = (Player) ve.getFish().getPassenger();
//							if(player.){
								ve.move(player);
//							}else{
//								ve.getFish().setVelocity(new Vector(0, 0, 0));
//							}
						}
					}else{
						ve.getFish().setVelocity(new Vector(0, 0, 0));
					}
				}
			}
			
		}, 0, 0);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		Bukkit.getScheduler().scheduleSyncDelayedTask(VehicleCore.getInstance(), new Runnable(){

			@Override
			public void run() {
				createVehicle(event.getPlayer().getLocation().clone(), VehicleCore.getInstance().getVehicleManager().getVehicle("test"));				
			}
			
		}, 20);
	}
	
	@EventHandler
	public void onClick(PlayerInteractAtEntityEvent event){
		boolean cancel = false;
		if(event.getRightClicked() instanceof ArmorStand){
			ArmorStand stand = (ArmorStand) event.getRightClicked();
			for(VehicleEntity ve : this.activeEntities){
				for(ArmorStand vStand : ve.getStands()){
					if(vStand.getLocation().equals(stand.getLocation())){
						ve.mount(event.getPlayer());
						cancel = true;
						break;
					}
				}
			}
		}
		if(cancel){
			event.setCancelled(true);
		}
	}
	
}
