package hilosJoinGroup;

import java.util.Scanner;

public class MuestraMensajePrincipalGroup {
	
	public static void main(String[] args) {
		ThreadGroup grupoHilos = new ThreadGroup("Grupo Hilos");
		Scanner lector = new Scanner(System.in);
		System.out.println("¿Numero de hilos a crear?");
		int numeroHilos = lector.nextInt();
		Thread[] arregloHilos = new Thread[numeroHilos];
		for(int indice = 0; indice < arregloHilos.length; indice++)
			arregloHilos[indice] = new MuestraMensaje(grupoHilos, "hilo" + (indice+1));
		for(int indice = 0; indice < arregloHilos.length; indice++)
			arregloHilos[indice].start();
		while(grupoHilos.activeCount() == arregloHilos.length) {
			
		}
		grupoHilos.interrupt();
	}
	
}
