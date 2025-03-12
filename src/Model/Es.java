package Model;

import java.time.LocalDateTime;

public class Es {

	private String matricula;
	private boolean movimiento; // True => Entrada, False => Salida
	private LocalDateTime fecha; // Fecha del movimiento
	
	
	public Es() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor especial que a partir del string en formato csv genera
	 * el registro
	 * @param reg registro en formato csv
	 */
	public Es(String reg) {
		
		String[] campos = reg.split(";");
		this.matricula = campos[0];
		this.movimiento = (campos[1].equals("E"))?true:false;
		this.fecha = LocalDateTime.parse(campos[2]);
	}
	
	public Es(String matricula, boolean movimiento, LocalDateTime fecha) {
		super();
		this.matricula = matricula;
		this.movimiento = movimiento;
		this.fecha = fecha;
	}

	/**
	 * Genera un string con el formato matricula; E/S ; fecha
	 * @return string en formato csv con delimitador ;
	 */
	public String toCSV() {
		
		String mov ="";
		mov = (movimiento)?"E":"S";
		return matricula + ";" +mov +";" + fecha.toString();
		
	}

	public String getMatricula() {		return matricula;	}
	public void setMatricula(String matricula) {		this.matricula = matricula;	}
	public boolean isMovimiento() {		return movimiento;	}
	public void setMovimiento(boolean movimiento) {		this.movimiento = movimiento;	}
	public LocalDateTime getFecha() {		return fecha;	}
	public void setFecha(LocalDateTime fecha) {		this.fecha = fecha;	}

	
	
}
