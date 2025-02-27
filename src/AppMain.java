import java.util.ArrayList;
import java.util.Scanner;
import Model.Vehiculo;
import Model.Aparcamiento;
import tools.Menu;

public class AppMain {

	
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
				"Listar Vehículo",
				"Estadísticas"
		};
		
		init();
		cuentaColores(apm.getListaVehiculos());
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
				estadisticas();
				break;
			case 0: 
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
			apm.getListaVehiculos().add(new Vehiculo(matricula,color,fecha));
		}
		
		
	};
	public void deleteVehiculo() {
		
		String matricula = pedirMatricula();
		Integer valor = comprobarMatricula(matricula);
		if (valor == -1) {
			System.out.println("No existe un vehículo en el aparcamiento");
		}
		else {
			// Añadirlo a la lista de vehiculos
			apm.getListaVehiculos().remove(valor);
		}
		
		
	};
	public void listVehiculos() {};
	public void estadisticas() {};
	
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
		this.apm = new Aparcamiento();
		this.apm.setNombre("GOYA");
		for (int i=0;i<listaEjemplo.length;i++ ) {
			this.apm.getListaVehiculos().add(listaEjemplo[i]);
		}
	}

}
