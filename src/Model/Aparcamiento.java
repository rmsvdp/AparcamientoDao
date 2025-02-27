package Model;
import java.util.ArrayList;

public class Aparcamiento {

	private String nombre;
	private ArrayList<Vehiculo> listaVehiculos = new ArrayList<Vehiculo>();
	
	
	
	public Aparcamiento() {
		
	}


	public Aparcamiento(String nombre) {
		this.nombre = nombre;
	}


	public String getNombre() 								 {	return nombre;	}
	public void setNombre(String nombre) 					 {	this.nombre = nombre;	}
	public ArrayList<Vehiculo> getListaVehiculos() 			 {	return listaVehiculos;	}
	public void setListVehiculos(ArrayList<Vehiculo> plazas) {	this.listaVehiculos = plazas;	}

	
	
}
