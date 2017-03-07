package vehicle.core;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import vehicle.vehicle.VehicleManager;
import vehicle.vehicle.entities.VehicleEntity;
import vehicle.vehicle.entities.VehicleEntityManager;

public class VehicleCore extends JavaPlugin {

	private static VehicleCore instance;
	
	private VehicleManager vehicleManager;
	private VehicleEntityManager vehicleEntityManager;
	
	public void onEnable(){
		instance = this;
		vehicleManager = new VehicleManager();
		vehicleEntityManager = new VehicleEntityManager();
		
		Bukkit.getServer().getPluginManager().registerEvents(vehicleEntityManager, instance);
	}
	
	public void onDisable(){
		for(VehicleEntity ve : vehicleEntityManager.getVehicleEntities()){
			ve.despawn();
		}
	}
	
	public VehicleManager getVehicleManager(){
		return this.vehicleManager;
	}
	
	public VehicleEntityManager getVehicleEntityManager(){
		return this.vehicleEntityManager;
	}
	
	public static VehicleCore getInstance(){
		return instance;
	}
	
}
