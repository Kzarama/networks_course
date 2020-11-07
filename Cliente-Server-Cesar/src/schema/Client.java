package schema;

import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.awt.TextArea;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8424784243872148301L;
	
	private Socket socket;
	private Scanner in;
	private PrintWriter out;
	private int cypherKey;
	private JTextArea messageBox;
	private char[] chars;
	
	public Client() throws UnknownHostException, IOException {
		this.setLayout(new BorderLayout());
		JTextArea messageArea = new JTextArea();
		this.messageBox = messageArea;
		messageArea.setEditable(false);
		JScrollPane scroll = new JScrollPane(this.messageBox);
		JTextField textArea = new JTextField();
		textArea.setEditable(true);
		
		textArea.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				
			}
		});
		
		this.add(messageBox, BorderLayout.CENTER);
		this.add(textArea, BorderLayout.NORTH);
		this.setVisible(true);
		this.setSize(400,400);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		run();
	}
	public void run() throws UnknownHostException, IOException {
		socket = new Socket("localhost", 8000);
		in = new Scanner(socket.getInputStream());
		out = new PrintWriter(socket.getOutputStream(), true);
		
		while(true) {
			if(in.hasNext()) {
				
				String line = in.nextLine();
				if(line != null) {
					if(line.startsWith("CYPHER")) {
						this.cypherKey = Integer.parseInt(line.split(" ")[1], 16);
					} else {
						String message = decode(line, this.cypherKey);
						this.messageBox.append(message + "\n");
					}
				}
			}
			
		}
		
	}
	
	
	
	private String encode(String x, int n) {
		char[] arr = x.toCharArray();
		for(int i = 0; i < arr.length; i++) {
			arr[i] = encode(arr[i], n);
		}
		return new String(arr);
	}
	
	private String decode(String x, int n) {
		char[] arr = x.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = decode(arr[i], n);
		}
		return new String(arr);
	}
	
	private char encode(char x, int n) {
		char out = (char)(modulo(x+n,127));
		return out;
	}
	
	private int modulo(int a, int b) {
		int c = a % b;
		if(c < 0) {
			c = (b < 0) ? c-b: c+b;
		}
		return c;
	}
	
	private char decode(char x, int n) {
		char out = (char)(modulo(x-n,127));
		return out;
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		new Client();
	}
}