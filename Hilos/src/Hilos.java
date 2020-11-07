import java.util.ArrayList;

public class Hilos {
	
	private static int global = 0;
	
	private static ArrayList<Thread> threads = new ArrayList<Thread>();
	
	public static void init() {
		for (int i = 0; i < 4; i++) {
			Thread thread = new Thread(new Runnable() {		
				@Override
				public void run() {
					System.out.println("Init Thread number: " + global);
					switch (global) {
					case 0:
						for (int j = 0; j < 10; j++) {
							System.out.println("Thread Number: " + global + " say: " + j);
						}
						break;
					case 1:
						for (int j = 0; j < 10; j++) {
							if(j%2==0) {
								System.out.println("Thread Number: " + global + " say: " + j);
							}
						}
						break;
					case 2:
						for (int j = 0; j < 10; j++) {
							if(j%2!=0) {
								System.out.println("Thread Number: " + global + " say: " + j);
							}
						}
						break;
					case 3:
						for (int j = 0; j < 10; j++) {
							System.out.println("Thread Number: " + global + " say: " + j*3);
						}
						break;
					default:
						break;
					}
					global++;
				}
			});
			threads.add(thread);
		}
		runThreads();
		
	}

	private static void runThreads() {
		for (int i = 0; i < threads.size(); i++) {
			threads.get(i).start();
			try {
				threads.get(i).join();
				threads.get(i).sleep(0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		init();
	}

}
