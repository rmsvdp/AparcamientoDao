package controller;

import java.util.Scanner;
import view.PanelLed;
import tools.Menu;

/**
 * Controlador de Panel de Leds    
 * Versión : BETA 3
 * TODO     No está terminada completamente la gestión de las posiciones
 * 			- eliminar y reasignar posición
 * 			- escritura a fichero para edición más cómoda
 *          - Optimizar gestión del panel
 * @author root
 * FECHA : 04/03/2025
 */

public class Controlador {
	
    public PanelLed av;
	public Controlador(int filas, int columnas) {
		av = new PanelLed(columnas, filas);
		av.setMode(-1);
        av.turnOff();
   }

	/**
	 * Enciende / apaga led del panel en función de que
	 * la plaza esté ocupada o vacía
	 * @param plazas array de plazas del objeto aparcamiento
	 */
	public void updatePanel(String[][] plazas) {
		for (int i=0; i< plazas.length;i++) {
			for (int j=0; j< plazas[0].length;j++) {
				if (plazas[i][j].equals(""))
					av.Item_Off(i, j);
				else
					av.Item_On(i, j);
			} // col
		} // fil
	} // updatePanel
}
