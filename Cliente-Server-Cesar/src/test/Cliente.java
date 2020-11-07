package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Cliente {
	
	/*
	 * Direccion local de la maquina
	 */
	public static final String LOCAL_HOST = "localhost";
	/**
	 * Puerto por donde se establecera la conexion
	 */
	public static final int PORT = 8000;
	/**
	 * Socket que permitira la conexion con el servidor
	 */
	private static Socket socket;
	
	private static ExecutorService executor = Executors.newCachedThreadPool();

	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		
		executor.execute(new Runnable() {
			@Override
			public void run() {
				DataOutputStream out;
				int i = 0;
				while(i < 2) {
					BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					String mensaje;
					
					try {
						mensaje = br.readLine();
						out = new DataOutputStream(socket.getOutputStream());
						out.writeUTF(mensaje);
						br.close();
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					i++;
				}
			}
		});
		
		DataInputStream in;
		
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			System.out.println("::Cliente disponible para ser atendido:: \nIngrese "
					+ "el mensaje::");
			socket = new Socket(LOCAL_HOST, PORT);
			in = new DataInputStream(socket.getInputStream());
			String mensajeDelServidor = in.readUTF();
			bw.write("Su encriptacion fue : " + mensajeDelServidor);
			bw.flush();
			bw.close();
			socket.close();
			in.close();
			
		} catch (Exception e) {
			
		}
	}

}
