package hilosSincronizar;

public class ManejaBanco {
	
	public static void main(String[] args) {
		Banco elBanco = new Banco();
		System.out.println(elBanco.getValorTotal());
		
		ThreadGroup grupoHilos = new ThreadGroup("Hilos Banco");
		
		Thread[] arregloHilos = new Thread[10];
		for(int indice = 0; indice < arregloHilos.length; indice++)
			arregloHilos[indice] = new HiloTransferencia(grupoHilos, elBanco, indice);
		for(int indice = 0; indice < arregloHilos.length; indice++)
			arregloHilos[indice].start();
	}
	
}
