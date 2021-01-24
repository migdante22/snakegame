package juego;

import java.awt.Canvas;
import java.awt.Color;

import javax.swing.JFrame;

public class ventana extends JFrame{
	
Canvas panelCanvas;
	
private int ancho = 600;
private int alto = 400;

	public ventana () {
		
		panelCanvas = new Canvas();
		
		
		setSize(ancho, alto);
		setTitle("Snakegame");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		add(panelCanvas);
		
		
		
	}

	public static void main(String[] args) {
		ventana v = new ventana();
	}

}
