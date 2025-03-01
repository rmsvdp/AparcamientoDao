package Model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class DaoVehiculoMap implements DaoMap<Vehiculo>, Serializable {

	public static HashMap<Integer,Vehiculo> listaVehiculos = new HashMap<Integer,Vehiculo> ();
	private static Integer id=1;
	public DaoVehiculoMap() {
		// TODO Auto-generated constructor stub
	}
	
	
	public HashMap<Integer,Vehiculo> findAll() {
		// -------
		// Devuelve una copia 
		//return new HashMap<Integer,Vehiculo>(this.listaVehiculos);
		// Devuelve la referencia
		return this.listaVehiculos;
	}
	public Vehiculo findOne(Integer key) {
		
		Vehiculo result = null;
		result = listaVehiculos.get(key);
		return result;
	}
	
	public Integer findKeyByMatricula(String Matricula) {
		
		Integer key = -1;
		for (Entry<Integer,Vehiculo> e : listaVehiculos.entrySet()) {
			if ( e.getValue().getMatricula().equals(Matricula)) {
				key = e.getKey();
				return key;
			}
		}
		return key;
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
		
		if (key != -1) {
			listaVehiculos.remove(key);
			result = true;
		}
		
		
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

	 // Método para serializar los datos a un archivo
    
    public void saveToFile(String filename) {
    	boolean result = false;
       try 
	   {
		  ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename)); 
	            oos.writeObject(listaVehiculos);
	            result = true;
		} catch (IOException e) {
			result = false;
		//	e.printStackTrace();
		}        
	
    } // saveToFile

 // Método para deserializar los datos desde un archivo
    
    
    public void loadFromFile(String filename) {
    	boolean result = false;
        try
        { 
     		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename)); 
        		listaVehiculos = (HashMap<Integer,Vehiculo>) ois.readObject();
        		result = true;
        } catch (IOException | ClassNotFoundException e) { 
		result = false;
		//e.printStackTrace();
		}    
     }

	
	
} // DaoVehiculoMap
