package vehicle.vehicle;

import java.util.ArrayList;
import java.util.List;

import vehicle.vehicle.vehicles.Test;
import vehicle.vehicle.vehicles.TestB;

public class VehicleManager {

	private List<Vehicle> vehicles = new ArrayList<>();
	
	public VehicleManager(){
		loadVehicles();
	}
	
	private void loadVehicles(){
		this.vehicles.add(new Test());
		this.vehicles.add(new TestB());
	}
	
	public Vehicle getVehicle(String name){
		for(Vehicle vehicle : this.vehicles){
			if(vehicle.getName().equalsIgnoreCase(name)){
				return vehicle;
			}
		}
		return null;
	}
	
}
