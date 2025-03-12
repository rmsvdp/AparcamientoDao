package Model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Aparcamiento  implements Serializable {

	private String nombre;
	private int numFilas ;		// Filas o pasillos de aparcamiento
	private int numColumnas ;   // Plazas por fila o pasillo
	private String[][] plaza;  // Plaza de aparcamiento
	private ArrayList<Vehiculo> listVehiculos = new ArrayList<Vehiculo>();
	public DaoVehiculoMap mapVehiculos = new DaoVehiculoMap();
	public DaoVehiculoList lstVehiculos = new DaoVehiculoList();
	
	private static final long serialVersionUID = 1L;
	
	public Aparcamiento() {
		  
	}


	public Aparcamiento(String nombre) {
		this.nombre = nombre;
		this.numFilas = 10;
		this.numColumnas = 10;
		this.plaza = new String[numFilas][numColumnas];
		for (int i=0;i<numFilas;i++) {
			for (int j=0;j<numColumnas;j++) {
				plaza[i][j]="";					// Vacía
			}
		}
	}


	public String getNombre() 								 {	return nombre;	}
	public void setNombre(String nombre) 					 {	this.nombre = nombre;	}
	public ArrayList<Vehiculo> getListaVehiculos() 			 {	return listVehiculos;	}
	public void setListVehiculos(ArrayList<Vehiculo> plazas) {	this.listVehiculos = plazas;	}
	
	
	
}
