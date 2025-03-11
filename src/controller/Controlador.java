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

}
