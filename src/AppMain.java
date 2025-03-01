import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import Model.Vehiculo;
import Model.Aparcamiento;

import tools.Menu;

public class AppMain {

	public static final String STORAGE = "Aparcamiento.dat";
	public Aparcamiento apm;	// = new Aparcamiento("GOYA");
	
	public static Vehiculo[] listaEjemplo =  {
		new Vehiculo("4444-ABC","ROJO",1900),
		new Vehiculo("5555-ABC","AZUL",1901),
		new Vehiculo("6666-ABC","ROJO",1902),
		new Vehiculo("7777-ABC","AMARILLO",1903),
		new Vehiculo("8888-ABC","ROJO",1904),
		new Vehiculo("9999-ABC","AMARILLO",1905),
		new Vehiculo("0000-ABC","ROJO",1906),
	};
	
	public AppMain() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AppMain ap = new AppMain();
		ap.run();
		
	} // end Main

	public void run() {
		
		Menu m = new Menu();
		Boolean salir= false;
		Integer opcion =0;
		String[] opc = {
				"Añadir Vehículo",
				"Eliminar Vehículo",
				"Listar listVehiculos",
				"Listar mapVehiculos",
				"Estadísticas"
		};
		
		init();

		m.setTitulo("APARCAMIENTO : " + apm.getNombre());
		m.setOpciones(opc);
		while (!salir) {
			
			m.mostrar();// Mostrar el menú
			opcion = m.seleccionaOpc();
			
			switch(opcion) {
			
			case 1:
				addVehiculo();
				break;
			case 2:
				deleteVehiculo();
				break;
			case 3:
				listVehiculos();
				break;
			case 4:
				mapVehiculos();
				break;
			case 5:
				estadisticas();
				break;
			case 0: 
				save();
				salir = true;
			} // opciones			
			
		} // bucle principal de la aplicación
		
	} // end run


	public void addVehiculo() {
		
		String matricula = pedirMatricula();
		Integer valor = comprobarMatricula(matricula);
		if (valor != -1) {
			System.out.println("Ya existe un vehículo en el aparcamiento");
		}
		else {
			// Pedir el resto de datos
			Scanner sc = new Scanner(System.in);
			System.out.print("Dime el color :");
			String color = sc.nextLine().toUpperCase();
			System.out.print("Dime el año :");
			Integer fecha = sc.nextInt();
			// Añadirlo a la lista de vehiculos
			Vehiculo v = new Vehiculo(matricula,color,fecha);
			apm.getListaVehiculos().add(v);
			// Añadir usando DaoVehiculoMap , uso persistenica de forma transparente
			apm.mapVehiculos.insertOne(v);
			
		}
		
		
	};
	public void deleteVehiculo() {
		
		String matricula = pedirMatricula();

		int valor = comprobarMatricula(matricula); // Comprobación dependiente del ArrayList
		if (valor == -1) {
			System.out.println("No existe un vehículo en el aparcamiento");
		}
		else {
			// Eliminarlo de la lista de vehiculos
			apm.getListaVehiculos().remove(valor);
			// Añadir usando DaoVehiculoMap , uso persistenica de forma transparente
		}
		// Usando el objeto Dao. Ya no lo implemento en el main
		apm.mapVehiculos.deleteOne(apm.mapVehiculos.findKeyByMatricula(matricula));
		valor = valor;
	};
	public void listVehiculos() {
		
		System.out.println("\n" + justifica("MATRICULA",10)+justifica("COLOR",10)+justifica("AÑO",5));
		System.out.println("-".repeat(25));
		for (Vehiculo v : apm.getListaVehiculos()) {
			System.out.println(	justifica(v.getMatricula(),10)+
								justifica(v.getColor(),10)+
								justifica(""+ v.getFecha(),5));
			
		}
		System.out.println();
	};
	public void estadisticas() {
		cuentaColores(apm.getListaVehiculos());
		
	};
	
	public void mapVehiculos() {
		
		System.out.println("\n" + justifica("MATRICULA",10)+justifica("COLOR",10)+justifica("AÑO",5));
		System.out.println("-".repeat(25));
		for (Vehiculo v : apm.mapVehiculos.findAll().values()) {
			System.out.println(	justifica(v.getMatricula(),10)+
								justifica(v.getColor(),10)+
								justifica(""+ v.getFecha(),5));
			
		}
		System.out.println();
	};

	
	/**
	 * Devuelve un array con los colores distintos que existen en el aparcamiento
	 * @param alv Array de vehículos
	 * @return lista de colores distintos
	 */
	public ArrayList<String> distinctColor(ArrayList<Vehiculo> alv){
		ArrayList<String> als = new ArrayList<String>();
				for (int i=0; i < alv.size();i++) {
					String color = alv.get(i).getColor();
					if (!als.contains(color))
					{ als.add(color);}					
				}
		return als;
		
	}
	
	public void cuentaColores(ArrayList<Vehiculo> alv) {
		ArrayList<String> als = distinctColor(alv);
		Integer[] numero = new Integer[als.size()];
		// Hay que inicializarlos a cero
		for (int j=0; j< numero.length;j++) {
			 numero [j]=0;
		}
		for (int i = 0;i<alv.size();i++) {
			String color = alv.get(i).getColor();
			int pos = als.indexOf(color);
			numero[pos]++;
		}
		for (int j=0; j< numero.length;j++) {
			System.out.println(als.get(j) + " : " + numero [j]);
		}
	}
	
	public String pedirMatricula() {
		Scanner sc = new Scanner(System.in);
			System.out.println("Introduce Matricula");
			String matricula = sc.nextLine();
		return matricula.toUpperCase();
	}
	
	public int comprobarMatricula(String matricula) {
		int result =  -1;
		ArrayList<Vehiculo> al;
		al = apm.getListaVehiculos();
		for (int i = 0; i < al.size();i++) {
			Vehiculo v1 = al.get(i);
			if (v1.getMatricula().equals(matricula)) {
				result = i;
				break;
			}
		} // recorrer listaVehiculos
		
		return result;
		
	}
	public void init() {
	      File file = new File(STORAGE); // Verifica si el archivo existe
	      if (file.exists()) {
	        	
	        	System.out.println("Recuperando información almacenada");
	            load();			// Método simple no recupera información DAO
	            // Regenera DAO
				for (int i=0;i<apm.getListaVehiculos().size();i++ ) {
					this.apm.mapVehiculos.insertOne(apm.getListaVehiculos().get(i));
			 }
	        }
	        else {
	        		System.out.println("Generando datos de prueba");
					this.apm = new Aparcamiento();
					this.apm.setNombre("GOYA");
					for (int i=0;i<listaEjemplo.length;i++ ) {
						this.apm.getListaVehiculos().add(listaEjemplo[i]);
						// Usar objeto Dao
						this.apm.mapVehiculos.insertOne(listaEjemplo[i]);
				 }
	        }
	} // init()
	/**
	 * Hace una copia completa del contenido del objeto Aparcamiento
	 * que utiliza la aplicación. Se salvan también todos los Vehículos registrados
	 * 
	 * @return operación realizada con éxito true, false en caso contrario
	 */
	public boolean save() {
		boolean result = false;
	       try 	{
			   	  ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(STORAGE)); 
			               oos.writeObject(apm);
			               result = true;
			   	} catch (IOException e) {
			   		result = false;
			   		e.printStackTrace();
			   		}  
				return result;
	} // save
	
	/**
	 * Recupera la copia del último contenido del objeto Aparcamiento
	 * almacenado. Se salvan también todos los Vehículos registrados
	 * 
	 * @return operación realizada con éxito true, false en caso contrario
	 */
	public boolean load() {
		boolean result = false;
        try
        { 
     		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(STORAGE)); 
        		apm = (Aparcamiento) ois.readObject();
        		result = true;
        } catch (IOException | ClassNotFoundException e) { 
		result = false;
		//e.printStackTrace();
		} 	
		
		return result;
		
	}
	
	/**
	 * Función auxiliar para justificar con espacios el contenido del string
	 * @param cad Cadena original 
	 * @param numero anchura total
	 * @return cadena rellenada con espacios justificada a la izquierda.
	 */
	private String justifica(String cad,int numero) {	
		
		return String.format("%1$-" + numero + "s", cad); // justifica a la izq , añadir %1$- para dcha
	}
	
}
