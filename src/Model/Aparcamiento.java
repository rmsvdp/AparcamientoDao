package Model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Aparcamiento  implements Serializable {

	private String nombre;
	private ArrayList<Vehiculo> listVehiculos = new ArrayList<Vehiculo>();
	public DaoVehiculoMap mapVehiculos = new DaoVehiculoMap();
	public DaoVehiculoList lstVehiculos = new DaoVehiculoList();
	
	private static final long serialVersionUID = 1L;
	
	public Aparcamiento() {
		  
	}


	public Aparcamiento(String nombre) {
		this.nombre = nombre;
	}


	public String getNombre() 								 {	return nombre;	}
	public void setNombre(String nombre) 					 {	this.nombre = nombre;	}
	public ArrayList<Vehiculo> getListaVehiculos() 			 {	return listVehiculos;	}
	public void setListVehiculos(ArrayList<Vehiculo> plazas) {	this.listVehiculos = plazas;	}
	
	
	
}
