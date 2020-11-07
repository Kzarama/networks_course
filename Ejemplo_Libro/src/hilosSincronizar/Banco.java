package hilosSincronizar;

public class Banco {
	
	private int[] cuentas;
	private int valorTotal = 1000000;
	private int numTransaccion = 0;
	
	public Banco() {
		cuentas = new int[10];
		for(int numCuenta = 0; numCuenta < 10; numCuenta++)
			cuentas[numCuenta] =100000;
	}
	
	public int getNumTransaccion() {
		return numTransaccion;
	}
	
	public int getValorCuenta(int numCuenta) {
		return cuentas[numCuenta];
	}
	
	public int getValorTotal() {
		return valorTotal;
	}
	
	public void setNumTransaccion() {
		numTransaccion++;
	}
	
	public synchronized void setValorCuenta(int numCuenta, int valor) {
		cuentas[numCuenta] += valor;
		valorTotal += valor;
	}
	
}
