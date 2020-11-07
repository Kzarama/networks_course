package hilosJoinGroup;

public class MuestraMensaje extends Thread {
	
	private int tiempoEspera;
	
	public MuestraMensaje(String nombre) {
		super(nombre);
		tiempoEspera = (int)(Math.random() * 1000);
	}
	
	public MuestraMensaje(ThreadGroup grupo, String nombre) {
		super(grupo, nombre);
		tiempoEspera = (int)(Math.random() * 1000);
	}
	
	public int getTiempoEspera() {
		return tiempoEspera;
	}
	
	public void run() {
		try {
			System.out.println("Mensaje inicial - " + getName());
			Thread.sleep(tiempoEspera);
			System.out.println("Mensaje final - " + getName() + ":" + getTiempoEspera());
		} catch (InterruptedException interrupcion) {
			
		}
	}
}
