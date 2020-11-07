package hilosJoinGroup;

import java.util.Scanner;

public class MuestraMensajePrincipal {
	
	public static void main(String[] args) {
		Scanner lector = new Scanner(System.in);
		System.out.println("¿Numero de hilos a crear?");
		int numeroHilos = lector.nextInt();
		MuestraMensaje[] arregloHilos = new MuestraMensaje[numeroHilos];
		for(int indice = 0; indice < arregloHilos.length; indice++)
			arregloHilos[indice] = new MuestraMensaje("hilo"+(indice+1));
		for(int indice = 0; indice < arregloHilos.length; indice++)
			arregloHilos[indice].start();
		for(int indice = 0; indice < arregloHilos.length; indice++) {
			try {
				arregloHilos[indice].join();
			} catch(InterruptedException e) {
				
			}
		}
		int mayor = -1, posicionMayor = -1;
		for(int indice = 0; indice < arregloHilos.length; indice++) {
			if(arregloHilos[indice].getTiempoEspera() > mayor) {
				mayor = arregloHilos[indice].getTiempoEspera();
				posicionMayor = indice;
			}
		}
		System.out.println("El hilo mas demorado fue " + arregloHilos[posicionMayor].getName() + " con un tiempo de " + mayor + " milisegundos.");
	}
	
}
