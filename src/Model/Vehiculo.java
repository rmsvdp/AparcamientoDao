package Model;

public class Vehiculo {

	private String matricula;
	private String color;
	private int fecha;
	
	public Vehiculo() {
		// TODO Auto-generated constructor stub
	}

	
	public Vehiculo(String matricula, String color, int fecha) {
		super();
		this.matricula = matricula;
		this.color = color;
		this.fecha = fecha;
	}


	public String getMatricula() {		return matricula;	}
	public void setMatricula(String matricula) {		this.matricula = matricula;	}
	public String getColor() {		return color;	}
	public void setColor(String color) {		this.color = color;	}
	public int getFecha() {		return fecha;	}
	public void setFecha(int fecha) {		this.fecha = fecha;	}

	
}
