package Model;

import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.DeserializationFeature;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class DaoAparcamientoList implements DaoList<Aparcamiento>{

	public ArrayList<Aparcamiento> listaAparcamientos = new ArrayList<Aparcamiento>();
	private final String DAT_AP ="DatosAparcamientos.json";
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
		boolean result;
		listaAparcamientos.add(ap);
		return saveJson();					// Almacena en disco

	}
	public boolean deleteOne(String key) {
		// TODO implementar
		return true;
	}
	public boolean updateOne(String key,Aparcamiento t) {
		// TODO implementar
		return true;
	}
	
	public boolean loadJson() {
		
		boolean result = false;		
		 ObjectMapper objectMapper = new ObjectMapper();
		 objectMapper.registerModule(new JavaTimeModule());
		 objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	        
         try {// Leer el archivo JSON y mapearlo a una lista de objetos Aparcamiento
				listaAparcamientos = objectMapper.readValue(
				        new File(DAT_AP),
				        new TypeReference<ArrayList<Aparcamiento>>() {}
				);
				result = true;
         } catch (IOException e) {
				e.printStackTrace();
			}
		return result;
	}
	public boolean saveJson() {
		String cadena = "";
		boolean result = false;
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(DAT_AP));
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);    
            cadena = objectMapper.writeValueAsString(listaAparcamientos);
			bw.write(cadena);
			bw.close();
			result = true;
		} catch (IOException e) {
			e.printStackTrace();			// TODO Auto-generated catch block
			result = false;
		}
	return result;
	} 
}
