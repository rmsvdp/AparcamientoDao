import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import Model.Vehiculo;
import Model.Aparcamiento;
import Model.Es;
import controller.Controlador;
import tools.Menu;

/**
 * Aparcamiento DAO versión 1
 * 
 * - Gestión a través de clase DAO de datos mediante ArrayList
 * - Persitencia de datos en Clase DAO en formato CSV
 * 
 * TODO
 * 
 * 1. Crear clase EsCoches para registrar entradas y salidas
 * 2. Crear objeto DaoEsCoches para encapsular los datos
 * 3. Modificar las opciones 1 y 2 del menú, para que se registren
 *    las entradas y salidas
 * 4. La escritura en archivo de entrada salida se efectuará de 
 *    forma transparente en el objeto DAO además de mantener el array
 *    en memoria.
 */
public class AppMain {

	/**
	 * Nombre del archivo de almacenamiento en disco.
	 */
	public static final String STORAGE = "Aparcamiento.dat";
	public Aparcamiento apm;	
	
	/**
	 * Datos de prueba
	 */
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

	/**
	 * Lanzador estático
	 * @param args -- Se ignoran parámetros
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AppMain ap = new AppMain();
		ap.run();
		
	} // end Main

	/**
	 * Lanzador no estático de la aplicación (Método principal)
	 */
	public void run() {
		
		Menu m = new Menu();
		Boolean salir= false;
		Integer opcion =0;
		String[] opc = {
				"Añadir Vehículo",
				"Eliminar Vehículo",
				"Listar listVehiculos",
				"Ocupación",
				"Estadísticas",
		};
		
		init();// Operaciones de inicialización del programa ---------------------------------------------------------

		m.setTitulo("APARCAMIENTO : " + apm.getNombre());
		m.setOpciones(opc);
		//Controlador c = new Controlador(10,10);
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
					System.out.println("\nOperación no Implementada...\n");
					break;
				case 5:
					estadisticas();
					break;
				case 0: 
					salir = true;
			} // opciones			
			
		} // bucle principal de la aplicación
		
		// Operaciones de cierre de la aplicación ---------------------------------------------------------
		//System.out.println("\nVolcando información a disco...\n");
		//save(STORAGE); // Guarda la información
		//c.av.showPanel(false);
		System.out.println("\nAplicación Terminada.\n");
	} // end run

	
	public void demoPlano(Controlador c) {
		
		Color co = c.av.amarillo;
		c.av.squareDemo(co, 0);
		c.av.Item_Color(3, 2, co);c.av.Item_Color(3, 7, co);c.av.Item_Color(5, 4, co);c.av.Item_Color(5, 5, co);
		c.av.Item_Color(7, 3, co);c.av.Item_Color(7, 4, co);c.av.Item_Color(7, 5, co);c.av.Item_Color(7, 6, co);
	}
/**
 * Añade un vehículo nuevo al sistema
 */
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
			Vehiculo v = new Vehiculo(matricula,color,fecha);
			apm.lstVehiculos.insertOne(v);			// Añadirlo a la lista de vehiculos
			apm.lstEs.insertOne(new Es(matricula,true,LocalDateTime.now()));
			save(STORAGE); // Guarda la información
			
		}
			
	} // addVehiculo()
	/**
	 * Elimina un vehículo a partir de su matrícula
	 */
	public void deleteVehiculo() {
		
		String matricula = pedirMatricula();

		int valor = comprobarMatricula(matricula); // Comprobación dependiente del ArrayList
		if (valor == -1) {
			System.out.println("No existe un vehículo en el aparcamiento");
		}
		else {
			apm.lstVehiculos.deleteOne(matricula);
			// Registrar entrada-salida
			apm.lstEs.insertOne(new Es(matricula,false,LocalDateTime.now()));
			save(STORAGE); // Guarda la información
		}

	}
	
	/**
	 * Genera un listado de los vehículos registrados en el aparcamiento, utilizando
	 * la colección listVehiculos
	 */
	
	public void listVehiculos() {
		
		System.out.println("\n" + justifica("MATRICULA",10)+justifica("COLOR",10)+justifica("AÑO",5));
		System.out.println("-".repeat(25));
		for (Vehiculo v : apm.lstVehiculos.findAll()) {
			System.out.println(	justifica(v.getMatricula(),10)+
								justifica(v.getColor(),10)+
								justifica(""+ v.getFecha(),5));
			
		}
		System.out.println();
	}
	
	/**
	 * Genera la estadísticas del aparcamiento
	 */
	public void estadisticas() {
		cuentaColores(apm.getListaVehiculos());
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
	
	/**
	 * Algoritmo para generar las estadísticas de colores de los vehículos registrados
	 * @param alv Colección ArrayList de vehículos
	 */
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
	
	/**
	 * Solicita la entrada de la matrícula por consola
	 * TODO:  no se hace ninguna comprobación de formato
	 * @return matrícula con los 3 caracteres de letra en mayúscula
	 * 
	 */
	public String pedirMatricula() {
		Scanner sc = new Scanner(System.in);
			System.out.println("Introduce Matricula");
			String matricula = sc.nextLine();
		return matricula.toUpperCase();
	}
	
	/**
	 * Recupera el índice del vehículo que tiene la matricula especificada
	 * de la colección listVehiculos del aparcamiento
	 * @param matricula Matrícula de búsqueda
	 * @return -1 : No encontrada >=0 : Índice dentro de la lista
	 */
	public int comprobarMatricula(String matricula) {
		int result =  -1;
		ArrayList<Vehiculo> al;
		al = apm.lstVehiculos.findAll();
		for (int i = 0; i < al.size();i++) {
			Vehiculo v1 = al.get(i);
			if (v1.getMatricula().equals(matricula)) {
				result = i;
				break;
			}
		} // recorrer listaVehiculos
		
		return result;
		
	}
	
	/**
	 * Método de inicialización de la aplicación
	 * - Recupera datos almacenados en disco o genera un set de pruebas
	 * 
	 */
	public void init() {
	      File file = new File(STORAGE); // Verifica si el archivo existe
	      if (file.exists()) {
	        	
	        	System.out.println("\nRecuperando información almacenada...\n");
	            load(STORAGE);			// Método simple recupera información
	        }
	        else {
	        		System.out.println("\nGenerando datos de prueba ...\n");
					this.apm = new Aparcamiento("GOYA");
					for (int i=0;i<listaEjemplo.length;i++ ) {
						this.apm.lstVehiculos.insertOne(listaEjemplo[i]);
						this.apm.lstEs.insertOne(new Es(listaEjemplo[i].getMatricula(),true,LocalDateTime.now()));
				 }
	        }
	} // init()
	/**
	 * Hace una copia completa del contenido del objeto Aparcamiento
	 * que utiliza la aplicación. Se salvan también todos los Vehículos registrados
	 * 
	 * @return operación realizada con éxito true, false en caso contrario
	 */
	public boolean save(String fichero) {
		boolean result = false;
		
		// TODO salvar aparcamiento completo	
		try {
			apm.lstVehiculos.saveAll(fichero);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // salva lstVehiculos;
		
		return result;
	} // save
	
	/**
	 * Recupera la copia del último contenido del objeto Aparcamiento
	 * almacenado. Se salvan también todos los Vehículos registrados
	 * 
	 * @return operación realizada con éxito true, false en caso contrario
	 */
	public boolean load(String fichero) {

		boolean result = false;
 	
		this.apm = new Aparcamiento("GOYA"); // TODO presevar objeto aparacmiento			
		apm.lstVehiculos.loadAll(fichero); // recupera lstVehiculos;	
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
