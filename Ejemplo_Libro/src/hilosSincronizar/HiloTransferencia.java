package hilosSincronizar;

import java.util.Random;

public class HiloTransferencia extends Thread {
	
	private Banco elBanco;
	private int origen;
	
	public HiloTransferencia(ThreadGroup grupo, Banco elBanco, int origen) {
		super(grupo, ""+origen);
		this.elBanco = elBanco;
		this.origen = origen;
	}
	
	public void run() {
		while(true) {
			int destino;
			do {
				destino = (int)(Math.random()*10);
			} while(destino == origen);
			
			int cantidad = (int)(Math.random()*10000);
			elBanco.setValorCuenta(origen, cantidad * -1);
			elBanco.setValorCuenta(destino, cantidad);
			elBanco.setNumTransaccion();
			int transaccion = elBanco.getNumTransaccion();
			
			if(transaccion % 100000 == 0)
				System.out.println("Transaccion= " + transaccion + " Total= " + elBanco.getValorTotal());
		}
	}
	
}
