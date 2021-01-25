package juego;

import java.awt.Canvas;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.glass.events.KeyEvent;

public class ventana extends JFrame implements Runnable{
	


private Point snake;
private Point comidaPoint;
private imaSnake ImaSnake;
	
private int ancho = 800;
private int alto = 400;

private Thread thread;
private volatile boolean Activado = false;

long frecuencia = 100000000;
int whidthSnake = 20;
int heigthSnake = 20;
int Comidaancho = 15;
int Comidaalto = 15;
int direccion = KeyEvent.VK_LEFT;

	public ventana () {
		

		
		teclas teclas = new teclas();
		
		
		setSize(ancho, alto);
		setTitle("Snakegame");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		addKeyListener(teclas);
		
		gamestar();
		
		
		ImaSnake = new imaSnake();
		this.getContentPane().add(ImaSnake);

			
	}
	

	public static void main(String[] args) {
		ventana v = new ventana();
		v.Inciar();
		
	}
	
	
	// panel donde se dibuja el juego
	public class imaSnake extends JPanel{
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			g.setColor(Color.BLUE);
			g.fillRect(snake.x, snake.y, whidthSnake, heigthSnake);
			
			g.setColor(Color.RED);
			g.fillRoundRect(comidaPoint.x, comidaPoint.y, Comidaancho, Comidaalto, 20, 20);
		}
	}
	public void actualizar() {
		ImaSnake.repaint();
		
		if (snake.x == comidaPoint.x && snake.y == comidaPoint.y) {
			comidarandom();
		}
	}
	
	
	// clase para detectar teclas
	public class teclas extends KeyAdapter{
		@Override
		public void keyPressed(java.awt.event.KeyEvent e) {
			super.keyPressed(e);
			
			if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE) {
				System.exit(0);
			}else if (e.getKeyCode() == java.awt.event.KeyEvent.VK_UP) {
				if (direccion != KeyEvent.VK_DOWN) {
					direccion = java.awt.event.KeyEvent.VK_UP;
				}
			}
			else if (e.getKeyCode() == java.awt.event.KeyEvent.VK_DOWN) {
				if (direccion != KeyEvent.VK_UP) {
					direccion = java.awt.event.KeyEvent.VK_DOWN;
				}
			}
			else if (e.getKeyCode() == java.awt.event.KeyEvent.VK_LEFT) {
				if (direccion != KeyEvent.VK_RIGHT) {
					direccion = java.awt.event.KeyEvent.VK_LEFT;
				}
			}
			else if (e.getKeyCode() == java.awt.event.KeyEvent.VK_RIGHT) {
				if (direccion != KeyEvent.VK_LEFT) {
					direccion = java.awt.event.KeyEvent.VK_RIGHT;
				}
			}
		}
		
	}
	//
	public synchronized void Inciar() {
		Activado = true;
		thread = new Thread(this);// se tiene que desir endonde se ejecutara el Hilo
		thread.start();
	}
	// metodo que instancia las variables point para snake y la cominda
	public void gamestar() {
		snake = new Point(ancho/2, alto/2);
		comidaPoint =  new Point(20,20);
		comidarandom();
	}
	
	public void comidarandom() {
		Random rdomRandom = new Random();
		comidaPoint.x = rdomRandom.nextInt(ancho);
		
		if ((comidaPoint.x % 5) > 0) {
			comidaPoint.x = comidaPoint.x - (comidaPoint.x % 5) ;
		}
		if ((comidaPoint.x < 5) ) {
			comidaPoint.x = comidaPoint.x +10;
		}
		
		comidaPoint.y = rdomRandom.nextInt(alto);
		if ((comidaPoint.y % 5) > 0) {
			comidaPoint.y = comidaPoint.y - (comidaPoint.y % 5) ;
		}
		if ((comidaPoint.y < 5) ) {
			comidaPoint.y = comidaPoint.y +10;
		}
		
	}
	
	public synchronized void Terminar() {
		Activado = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


//metodo run usado como Hilo para el movimiento de la snake
	@Override
	public void run() {
		long ultimo = 0;
		
		while (Activado) {
			
			if (System.nanoTime() - ultimo > frecuencia) {
				
				if (direccion == java.awt.event.KeyEvent.VK_UP) {
					snake.y = snake.y - heigthSnake;
					if (snake.y <0) {
						snake.y = alto - heigthSnake;
					}
					if (snake.y > alto) {
						snake.y = 0;
					}
				}
				
				if (direccion == java.awt.event.KeyEvent.VK_DOWN) {
					snake.y = snake.y + heigthSnake;
					if (snake.y >alto) {
						snake.y = alto + heigthSnake;
					}
					if (snake.y > alto) {
						snake.y = 0;
					}
				}
				
				if (direccion == java.awt.event.KeyEvent.VK_LEFT) {
					snake.x = snake.x - whidthSnake;
					if (snake.x <0) {
						snake.x = ancho - whidthSnake;
					}
					if (snake.x > ancho) {
						snake.x = 0;
					}
				}
				
				if (direccion == java.awt.event.KeyEvent.VK_RIGHT) {
					snake.x = snake.x + whidthSnake;
					if (snake.x >ancho) {
						snake.x = ancho + whidthSnake;
					}
					if (snake.x > ancho) {
						snake.x = 0;
					}
				}
				
				
				actualizar();
				
				ultimo = System.nanoTime();
			}
			
		}
	}
		


}

