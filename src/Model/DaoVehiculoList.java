package Model;


import java.util.ArrayList;

public class DaoVehiculoList implements DaoList<Vehiculo>{

	public ArrayList<Vehiculo> listaVehiculos = new ArrayList<Vehiculo>();
	private static Integer id=1;
	public DaoVehiculoList() {
		// TODO Auto-generated constructor stub
	}
	
	
	public ArrayList<Vehiculo> findAll() {
		// -------
		return null;
	}
	public Vehiculo findOne(Integer key) {
		
		Vehiculo result = null;

		return result;
	}
	public boolean insertOne(Vehiculo v) {
		
		boolean result = false;

		return result;
	}
	public boolean deleteOne(Integer key){
		
		boolean result = false;
		

		return result;
	}
	
	public boolean updateOne(Integer key,Vehiculo v){
		
		boolean result = false;
		
		//--- 


		return result;
	}

}
