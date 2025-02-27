package Model;

import java.util.HashMap;

public class DaoVehiculoMap implements DaoMap<Vehiculo>{

	public HashMap<Integer,Vehiculo> listaVehiculos = new HashMap<Integer,Vehiculo> ();
	private static Integer id=1;
	public DaoVehiculoMap() {
		// TODO Auto-generated constructor stub
	}
	
	
	public HashMap<Integer,Vehiculo> findAll() {
		// -------
		return this.listaVehiculos;
	}
	public Vehiculo findOne(Integer key) {
		
		Vehiculo result = null;
		result = listaVehiculos.get(key);
		return result;
	}
	public boolean insertOne(Vehiculo v) {
		
		boolean result = false;
		// ----
		listaVehiculos.put(id,v);
		id++;
		
		return result;
	}
	public boolean deleteOne(Integer key){
		
		boolean result = false;
		
		// ----
		listaVehiculos.remove(key);
		
		return result;
	}
	
	public boolean updateOne(Integer key,Vehiculo v){
		
		boolean result = false;
		
		//--- 
		if (listaVehiculos.containsKey(key)) {
			listaVehiculos.put(key, v);
			result = true;
		}
		return result;
	}

}
