package Model;

import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		
		 ObjectMapper objectMapper = new ObjectMapper();
	       
	            // Leer el archivo JSON y mapearlo a una lista de objetos Aparcamiento
	            try {
					listaAparcamientos = objectMapper.readValue(
					        new File("personas.json"),
					        new TypeReference<ArrayList<Aparcamiento>>() {}
					);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		return listaAparcamientos;
	}
	public Aparcamiento findOne(String key) {
		// TODO implementar
		return null;
	}
	public boolean insertOne(Aparcamiento ap) {
		boolean result;
		listaAparcamientos.add(ap);
		String cadena = arrayToJson();
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(DAT_AP));
			bw.write(cadena);
			bw.close();
			result = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = false;
		}
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
	
	public String arrayToJson() {
		String jsonArray = "";
		
        try {
        	ObjectMapper objectMapper = new ObjectMapper();
            jsonArray = objectMapper.writeValueAsString(listaAparcamientos);

        }
        catch (JsonProcessingException e) {
           // e.printStackTrace();
        }
		
		return jsonArray;
		
	}
	
}
