package Model;
import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Aparcamiento  implements Serializable {

	private String nombre;
	private int numFilas ;		// Filas o pasillos de aparcamiento
	private int numColumnas ;   // Plazas por fila o pasillo
	private String[][] plaza;  // Plaza de aparcamiento
	private ArrayList<Vehiculo> listVehiculos = new ArrayList<Vehiculo>();
	public DaoVehiculoList lstVehiculos = new DaoVehiculoList();
	public DaoEsList lstEs = new DaoEsList();
	private static final long serialVersionUID = 1L;
	
	private int fila;
	private int columna;
	
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


	public int getNumFilas() 								 {	return numFilas;				}
	public void setNumFilas(int numFilas) 					 {	this.numFilas = numFilas;		}
	public int getNumColumnas() 							 {	return numColumnas;				}
	public void setNumColumnas(int numColumnas)  			 {	this.numColumnas = numColumnas;	}
	public String[][] getPlaza() 							 {	return plaza;					}

	public String getNombre() 								 {	return nombre;					}
	public void setNombre(String nombre) 					 {	this.nombre = nombre;			}
	public ArrayList<Vehiculo> getListaVehiculos() 			 {	return listVehiculos;			}
	public void setListVehiculos(ArrayList<Vehiculo> plazas) {	this.listVehiculos = plazas;	}
	
	/** Buscar una plaza libre y la asigna
	 * 
	 * @param matricula Matrícula del vehículo a estacionar
	 */
	public boolean aparcarVehiculo(String matricula) {
		
				for (int i=0; i<numFilas; i++) {
					for (int j=0; j<numColumnas; j++) {	
						if (plaza[i][j].equals("")) {
							plaza[i][j] = matricula;
							return true;
						}
					} // col
				} // fil
				return false;
	} // aparcarVehiculo
	
	/** Buscar la ubicación del Vehiculo y libera la plaza
	 * 
	 * @param matricula Matrícula del vehículo a estacionar
	 */
	public boolean retirarVehiculo(String matricula) {
			
		if (buscarVehiculo(matricula)) {
			plaza[fila][columna]="";
			return true;
		}
		return false;
	}
	
	/**
	 * Busca la plaza donde está estacionado el vehículo
	 * de con una matrícula específica.
	 * @param matricula matricula que se busca
	 * @return true : encontrado. Variables fila y columna actualizadas;
	 *         false: no encontrado.
	 */
	public boolean buscarVehiculo(String matricula) {
		
		for (int i=0; i<numFilas; i++) {
			for (int j=0; j<numColumnas; j++) {
				if (plaza[i][j].equals(matricula)) {
					fila = i;
					columna = j;
					return true;
				}
			} // col
		} // fil
		return false;
	} // buscarVehiculos
	
	public String toJson() {
		
		String valor ="";
		ObjectMapper mapeador= new ObjectMapper();
		
		try {
			valor = mapeador.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return valor;
	}
}
