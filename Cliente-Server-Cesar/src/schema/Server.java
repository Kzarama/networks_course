package schema;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	public static void main(String[] args) throws IOException {
		System.out.println("The chat server is running");
		ExecutorService pool = Executors.newFixedThreadPool(500);
		try(ServerSocket listener = new ServerSocket(8000)){
			while(true) {
				Socket s1 = listener.accept();
				Socket s2 = listener.accept();
				pool.execute(new Handler(s1,s2,true));
				pool.execute(new Handler(s2,s1,true));
			}
		}
	}
	
	private static class Handler implements Runnable {
		private Socket s1;
		private Socket s2;
		private Scanner in1;
		private PrintWriter out1;
		private Scanner in2;
		private PrintWriter out2;
		private boolean generateCypher;
		
		public Handler(Socket s1, Socket s2, boolean generateCypher) {
			this.s1 = s1;
			this.s2 = s2;
			this.generateCypher = generateCypher;
		}
		
		public void checkClient() {
			while(in1.hasNext()) {
				String input1 = in1.nextLine();
				if(input1.toLowerCase().startsWith("/quit")) {
					return;
				} else {
					out2.println(input1);
				}
			}
		}
		
		@Override
		public void run() {
			try {
				in1 = new Scanner(s1.getInputStream());
				out1 = new PrintWriter(s1.getOutputStream(), true);
				in2 = new Scanner(s2.getInputStream());
				out2 = new PrintWriter(s2.getOutputStream(), true);
				if(this.generateCypher) {
					Random rand = new Random();
					int cypherKey = rand.nextInt(21) + 1;
					out1.println("CYPHER: " + Integer.toHexString(cypherKey));
					out2.println("CYPHER: " + Integer.toHexString(cypherKey));
				}
				while(true) {
					checkClient();
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				try {
					s1.close();
					s2.close();
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
	}
}
