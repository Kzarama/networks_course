package hilosCarreraPrioridad;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Competidor extends Thread {
	
	private JPanel box;
	private int x = 40;
	private int y = 40;
	private Color color;
	private int puesto;
	
	public Competidor(JPanel b, Color c, int p) {
		box = b;
		color = c;
		puesto = p;
	}
	
	public void draw() {
		Graphics g = box.getGraphics();
		g.setColor(color);
		g.drawString("" + puesto + "-" +this.getPriority(), 10, puesto * 40);
		g.drawLine(x, puesto * y, x + 5, puesto * y);
		g.dispose();
	}
	
	public void move() {
		if(!box.isVisible())
			return;
		Graphics g = box.getGraphics();
		g.setXORMode(box.getBackground());
		g.setColor(color);
		while(x <= (box.getSize().width - 40)) {
			g.drawLine(x, puesto * y, x + 5, puesto * y);
			x += 5;
			try {
				Thread.sleep(50);
			} catch(InterruptedException e) {
				System.out.println("Error en el hilo");
			}
		}
		g.drawString("T", box.getSize().width - 40, puesto * y);
		g.dispose();
	}
	
}
