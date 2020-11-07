package hilosCarreraPrioridad;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaCarrera extends javax.swing.JFrame {
	
	private JPanel canvas;
	private Competidor hilos[] = new Competidor[3];
	private Color colores[] = {Color.blue, Color.red, Color.green};
	private JLabel etPrioridad1, etPrioridad2, etPrioridad3 = null;
	private JTextField prioridad1, prioridad2, prioridad3 = null;
	
	public VentanaCarrera() {
		setSize(600, 300);
		setTitle("Carrera");
		//System.exit(0);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		canvas = new JPanel();
		contentPane.add(canvas, "Center");
		
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout());
		addCompetidores(p);
		contentPane.add(p, "North");
		
		JPanel p1 = new JPanel();
		addButton(p1, "iniciarCompetencia", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hilos[0] = new Competidor(canvas, colores[0], 1);
				hilos[0].setPriority(Integer.parseInt(prioridad1.getText()));
				
				hilos[1] = new Competidor(canvas, colores[1], 2);
				hilos[1].setPriority(Integer.parseInt(prioridad2.getText()));
				
				hilos[2] = new Competidor(canvas, colores[2], 3);
				hilos[2].setPriority(Integer.parseInt(prioridad3.getText()));
				
				for(int contHilos = 0; contHilos < 3; contHilos++)
					//hilos[contHilos].start();
					hilos[contHilos].run();
				
			}
		});
		
		addButton(p1, "Terminar", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.setVisible(false);
				System.exit(0);
			}
		});
		contentPane.add(p1, "South");
	}

	private void addButton(Container c, String title, ActionListener a) {
		JButton b = new JButton(title);
		c.add(b);
		b.addActionListener(a);
	}

	private void addCompetidores(Container c) {
		etPrioridad1 = new JLabel("Prioridad Competidor 1:");
		prioridad1 = new JTextField(2);
		c.add(etPrioridad1);
		c.add(prioridad1);
		
		etPrioridad2 = new JLabel("Prioridad Competidor 2:");
		prioridad2 = new JTextField(2);
		c.add(etPrioridad2);
		c.add(prioridad2);
		
		etPrioridad3 = new JLabel("Prioridad Competidor 3:");
		prioridad3 = new JTextField(2);
		c.add(etPrioridad3);
		c.add(prioridad3);
	}

}
