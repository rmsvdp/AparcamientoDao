package Model;

import java.util.ArrayList;

public class DaoAparcamientoList implements DaoList<Aparcamiento>{

	public ArrayList<Aparcamiento> listaAparcamientos = new ArrayList<Aparcamiento>();
	
	public DaoAparcamientoList() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Aparcamiento> findAll(){
		
		return listaAparcamientos;
	}
	public Aparcamiento findOne(String key) {
		// TODO implementar
		return null;
	}
	public boolean insertOne(Aparcamiento ap) {
		
		listaAparcamientos.add(ap);
		return true;
	}
	public boolean deleteOne(String key) {
		// TODO implementar
		return true;
	}
	public boolean updateOne(String key,Aparcamiento t) {
		// TODO implementar
		return true;
	}
	
}
