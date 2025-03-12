package view;


import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


/**
 * Clase PanelLed, genera una ventana donde se visualizan los elementos 
 * de un array bidimensional.
 * Cada elemento se implementa como un botón que se puede cambiar de color.
 * Se ofrecen 3 métodos básicos:
 * - Constructor , especificando ancho y alto
 * - Fijar color de un elemento a negro
 * - Fijar color de un elemento a blanco
 * - Fijar color entre la paleta de colores disponible en SWing
 * @author root
 *
 */
public class PanelLed extends JFrame {
 
	private int posLed=1;
	private int mode=-1;			// Modo de interacción con los botones  led
									// -1 Desactivada
									// 0 Click : on-off
									// 1 Click : añade etiqueta (valor posicion edición)
	
	public int getPosLed() {		return posLed;	}
	public void setPosLed(int posLed) {		this.posLed = posLed;	}
	public int getMode() {		return mode;	}
	public void setMode(int mode) {		this.mode = mode;	}

	/**
	 * Constante para el ajuste de la ventana donde se ubica el visor
	 */
	private final int btn_cteX = 1;

	/**
	 * Constante para el ajuste de la ventana donde se ubica el visor
	 */
	private final int btn_cteY = 1;

	/**
	 * Constante para el ajuste de la ventana donde se ubica el visor
	 */
	private final int off_x = 8;

	/**
	 * Constante para el ajuste de la ventana donde se ubica el visor
	 */
	private final int off_y = 8;
    /**
     * Ancho del boton que simula un elemento del array
     */
	private final int btn_w = 32;
	/**
     * Ancho del boton que simula un elemento del array
     */
	private final int btn_h = 32;
	/**
	 * Estructura de datos para el visor
	 */
	private JButton[][] tablero;
	/**
	 * ancho del array
	 */
	private int ancho=0; 
	/**
	 * alto del array
	 */
	private int alto=0;
	
	/**
	 * Reservada
	 */
	private int posx;
	/**
	 * Reservada
	 */
	private int posy;
   
	public final Color rojo = Color.RED;
	public final Color azul = Color.BLUE;
	public final Color verde = Color.GREEN;
	public final Color amarillo = Color.YELLOW;
	public final Color negro = Color.BLACK;
	public final Color blanco = Color.WHITE;
	public final Color cyan = Color.CYAN;
	public final Color magenta = Color.MAGENTA;
	public final Color naranja = Color.ORANGE;
	public final Color gris = Color.LIGHT_GRAY;
	
	/**
	 * Bitmap virtual, enlazado con las tiras de led
	 */
	private BitMap[][] matrix;	// Matriz para integración con la clase Tira
   /**
    * Constructor principal, incializa pero no visualiza
    * @param ancho	Número de elementos de ancho
    * @param alto	Número de elementos de alto
    */
   public PanelLed(int ancho, int alto){

    // FlowLayout(int align, int hgap, int vgap)
//  Construye un nuevo FlowLayout con la alineación indicada
//  y los espacios horizontales y verticales indicados    
        if (ancho <3) ancho = 3;
        if (alto <= 0) alto = 1;
        if (ancho>48) ancho = 48;
        if (alto>24) alto = 24;
        this.tablero = new JButton[alto][ancho];
        matrix = new BitMap[alto][ancho];			// Creación del BitMap de leds
        this.ancho=ancho;
        this.alto=alto;
        setLayout(null);

        setTitle("Visualiza Panel");
        int win_w =(btn_w+ btn_cteX) * ancho; //off_x*2 + 
        int win_h =  (btn_h +btn_cteY) * alto;  //off_y*2 +
        setSize(win_w+off_x*2,win_h+btn_h+off_y*2);
        //setSize(win_w-btn_cteX,win_h+btn_cteY);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Font bFont=new  Font(Font.SERIF, Font.PLAIN, 10);
        this.mode = -1;								// Deshabilitado en el momento de la creación
        //Set JButton font using new created font
    	
                for (int j=0;j<alto;j++){
                    for (int i=0;i<ancho;i++){
                        this.tablero[j][i] = new JButton();
                        this.tablero[j][i].setMargin(new Insets(5, 5, 5, 5));
                        this.tablero[j][i].setFont(bFont);	
                        this.tablero[j][i].setBackground(this.blanco);
                        this.tablero[j][i].setOpaque(true);
                        this.tablero[j][i].setBounds( 32*i+off_y,32*j+off_x, btn_w, btn_h);

                        this.tablero[j][i].addActionListener(new ActionListener(){  
                            public void actionPerformed(ActionEvent e){  
                            	Object o = e.getSource();
                            	boolean salir = false;
                            	switch(getMode()) {
                            	
                            	case 0:    // invierte fondo del botón
                            	{
                            		 for (int j=0;j<getAlto() && !salir;j++){
 	            	                    for (int i=0;i<getAncho() && !salir;i++){
 	            	                    	if (o.equals(tablero[j][i])) {
 	            	                    		Color _c = tablero[j][i].getBackground();
 	            	                    		if (_c == Color.BLACK)
 	            	                    			tablero[j][i].setBackground(Color.WHITE);
 	            	                    		else 
 	            	                    			tablero[j][i].setBackground(Color.BLACK);
 	            	                    		salir = true;
 	            	                    	} //encontrada celda
 	            	                    } //i
 	            	                } // j
                            		break;
                            	}	
                            	case 1:		// Añade etiqueta a botón ( 1 caracter )
                            	{
                            		 for (int j=0;j<getAlto() && !salir;j++){
 	            	                    for (int i=0;i<getAncho() && !salir;i++){
 	            	                    	if (o.equals(tablero[j][i])) {
 	            	                    		System.out.print("{"+j+","+i+"},");
 	            	                    		tablero[j][i].setText(""+getPosLed());
 	            	                    		setPosLed(getPosLed()+1);
 	            	                    		salir = true;
 	            	                    	} //encontrada celda
 	            	                    } //i
 	            	                } // j
                            		break; 
                            	}
                            	default:	// Desactivado
                            		break;
                            	} // switch
                            }}); 
                        
                        add(this.tablero[j][i]);
                    } // alto
                } // ancho
                showPanel(false);
   }//PanelLed(int ancho, int alto)
   
public int getAncho() {	return ancho;}
public void setAncho(int ancho) {	this.ancho = ancho;}
public int getAlto() {	return alto;}
public void setAlto(int alto) {	this.alto = alto;}

/**
 * Hace visible u oculta la ventana del panel
 * @param flag true = visible , false = oculto
 */
public void showPanel(boolean flag) {    setVisible(flag);}
/**
    * Fija fondo de un determinado color para el elemento
    * @param i Fila del array
    * @param j Columna del array
    * @param c Color del elemento
    */
    public void Item_Color(int i,int j,Color c){
        this.tablero[i][j].setBackground(c);
    }
     
   /**
    * Fija fondo de color negro para el elemento
    * @param i Fila del array
    * @param j Columna del array
    */
    public void Item_Off(int i,int j){
        this.tablero[i][j].setBackground(this.negro);

    }
    /**
	    * Fija fondo de color blanco para el elemento
	    * @param i Fila del array
	    * @param j Columna del array
	    */ 
        public void Item_On(int i,int j){
        this.tablero[i][j].setBackground(this.blanco);

    }
        /**
         * Aplica la función Item_On a todo el tablero
         */
        public void turnOff() {
        	   for (int i=0;i<this.alto;i++){
	                for (int j=0;j<this.ancho;j++){
	                	Item_Off(i,j);
	                }
        	   }
        	
        }
     
        /**
         * Vuelca en el tablero el contenido de una tira de leds
         * @param tl Tira de leds seleccionada
         
        public void refresh(TiraLed tl) {
        	
        	for (int i = 0; i < tl.getnumLeds();i++) {
        		
        		this.tablero[tl.getLed(i).getPos_tab_fila()]
        				    [tl.getLed(i).getPos_tab_columna()].
        				    setBackground(tl.getLed(i).getC());
        	}
        }
        */
        
        
        /**
 	    * Demostración de relleno secuencias fila-columna del tablero
 	    */
        public void fillDemo(){
        
            for (int i=0;i<this.alto;i++){
                for (int j=0;j<this.ancho;j++){
                    
                    System.out.println("Fila, Columna : " + i+ ", "+j);
                      this.Item_On(i,j);
                      try {
                          Thread.sleep( 500);
                          } catch (InterruptedException ie) {
                              Thread.currentThread().interrupt();
                      }
                } // columnas
              } // filas
        }

        /**
         * Espera un número determinado de milisegundos
         * @param msec milisegundos de espera
         */
        public void delay(int msec) {
        	
            try {
                Thread.sleep(msec);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
            }
        }
/**
 * Demostración de uso de las nueva funciones que habilitan
 * colores (Item_Color).Escritura directa en el tablero        
 * @param c Color de cada celda
 * @param td Tiempo de espera en milisegundos
 */
        public void squareDemo(Color c,int td) {
        	
     
              for (int j=0;j<this.ancho;j++){
                    

                      this.Item_Color(0,j,c);
                      try {
                          Thread.sleep( td);
                          } catch (InterruptedException ie) {
                              Thread.currentThread().interrupt();
                      }
                } // primera fila
              
              for (int j=0;j<this.alto;j++){
                    
                      this.Item_Color(j,this.ancho-1,c);
                      try {
                    	  Thread.sleep( td);
                          } catch (InterruptedException ie) {
                              Thread.currentThread().interrupt();
                      }
                } // columna dcha
              
              
              for (int j=this.ancho-2;j>=0;j--){
                    
                      this.Item_Color(this.alto-1,j,c);
                      try {
                    	  Thread.sleep( td);
                          } catch (InterruptedException ie) {
                              Thread.currentThread().interrupt();
                      }
                } // última fila
              
              for (int j=this.alto-2;j>0;j--){
                  

                  this.Item_Color(j,0,c);
                  try {
                	  Thread.sleep( td);
                      } catch (InterruptedException ie) {
                          Thread.currentThread().interrupt();
                  }
            } // columna izq
        }

        /**
         * Inner Class. Reservada para futuros usos
         */
private class BitMap{
	int fila=0;
	int columna=0;
	Color _color=Color.BLACK;
	
} // inner Class bitmap

	
} // PanelLed Class
