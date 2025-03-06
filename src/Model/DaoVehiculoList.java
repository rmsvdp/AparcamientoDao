package Model;


import java.util.ArrayList;

public class DaoVehiculoList implements DaoList<Vehiculo>{

	public ArrayList<Vehiculo> listaVehiculos = new ArrayList<Vehiculo>();
	
	public DaoVehiculoList() {
		
	}
	
	
	public ArrayList<Vehiculo> findAll() {
		// -------
		// ----- devolver una copia
		//return new ArrayList<Vehiculo>(listaVehiculos);
		//  --- Referencia
		return listaVehiculos;
	}
	public Vehiculo findOne(String key) {
		
		Vehiculo result = null;
		
		for(Vehiculo v : listaVehiculos) {
			
			if ( v.getMatricula().equals(key)) return v;
		}

		return result;
	}
	public boolean insertOne(Vehiculo v) {
		
		boolean result = false;
		listaVehiculos.add(v);
		result = true;

		return result;
	}
	public boolean deleteOne(String key){
		
		boolean result = false;
		
			for(Vehiculo v : listaVehiculos) {
				
				if ( v.getMatricula().equals(key)) {
					listaVehiculos.remove(v);
					// return true;  // esto sería otra forma alternativa
					result= true;
					break;
					
				}
			} // iterar
		
		return result;
	}
	
	public boolean updateOne(String key,Vehiculo v){
		
		boolean result = false;		
		//--- 
		for(Vehiculo v1 : listaVehiculos) {
			
			if ( v1.getMatricula().equals(key)) {
				listaVehiculos.set(listaVehiculos.indexOf(v1), v);
				// return true;  // esto sería otra forma alternativa
				result= true;
				break;
			}
		} // iterar
		
		return result;
	}
	
	

}
