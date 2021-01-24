package juego;

import java.awt.Canvas;
import java.awt.*;
import java.awt.event.KeyAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.glass.events.KeyEvent;

public class ventana extends JFrame{
	


Point snake;
int snakeancho = 50;
int snakealto = 10;
imaSnake ImaSnake;
	
private int ancho = 800;
private int alto = 400;

	public ventana () {
		

		
		teclas teclas = new teclas();
		
		
		setSize(ancho, alto);
		setTitle("Snakegame");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		addKeyListener(teclas);
		
		
		snake = new Point(ancho/2, alto/2);
		
		ImaSnake = new imaSnake();
		this.getContentPane().add(ImaSnake);

		
	
		

			
	}
	

	public static void main(String[] args) {
		ventana v = new ventana();
		
		
	}
	
	
	// panel donde se dibuja el juego
	public class imaSnake extends JPanel{
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			g.setColor(Color.BLUE);
			g.fillRect(ancho/2, alto/2, 20, 20);
		}
	}
	
	
	
	
	public class teclas extends KeyAdapter{
		@Override
		public void keyPressed(java.awt.event.KeyEvent e) {
			super.keyPressed(e);
			
			if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE) {
				System.exit(0);
			}
		}
		
	}
		


}

