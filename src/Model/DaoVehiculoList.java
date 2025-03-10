package Model;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class DaoVehiculoList implements DaoList<Vehiculo>, Serializable {

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
	
	/**
	 * Recupera de un fichero de texto en formato csv una lsita de vehículos
	 * y los vuelca en el array List
	 * @param fichero
	 * @return true : Operación realizada con éxito , false: Error
	 */
	public boolean loadAll(String fichero) {
		boolean result = false;
		/*************** Serialización binaria java *************
		try
        { 
        	
     		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero)); 
        		listaVehiculos = (ArrayList<Vehiculo>) ois.readObject();
        		result = true;
        } catch (IOException | ClassNotFoundException e) { 
		result = false;
		//e.printStackTrace();
		}
		***************************************************************/
		return result;
	}
	
	/**
	 * Almacena en un fichero de texto en formato csv el contenido 
	 * del array List
	 * @param fichero
	 * @return true : Operación realizada con éxito , false: Error
	 */
	public boolean saveAll(String fichero) {
		boolean result = false;
		
		/*************** Serialización binaria java *************
	       try 	{
			   	  ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichero)); 
			               oos.writeObject(listaVehiculos);
			               result = true;
			   	} catch (IOException e) {
			   		result = false;
			   		//e.printStackTrace();
			   		}  
		*********************************************************/	
		return result;
	}

}
