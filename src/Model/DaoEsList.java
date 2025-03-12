package Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DaoEsList implements DaoList<Es> {

	public ArrayList<Es> listaEntSal = new ArrayList<Es>();
	private static String rutaArchivo ="LogES.csv";
	
	public DaoEsList() {
		// TODO Auto-generated constructor stub
	}

	public void setRuta(String fichero) {		rutaArchivo = fichero;	}
	/**
	 * Sin implementar
	 */
	public ArrayList<Es> findAll(){
		
		return null;
	}
	/**
	 * Sin implementar
	 */
	public Es findOne(String key) {
		
		return null;
	}
	public boolean insertOne(Es t) {
		
		boolean result = false;
		listaEntSal.add(t);		// Persitencia en memoria
		// TODO persistencia en fichero
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo,true));
			bw.write(t.toCSV());
			bw.newLine();
			bw.close();
			result = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	/**
	 * Sin implementar
	 */
	public boolean deleteOne(String key) {
		
		return true;
	}
	/**
	 * Sin implementar
	 */
	public boolean updateOne(String key,Es t) {
		
		return true;
	}
	
}
