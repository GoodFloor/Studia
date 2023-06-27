//Przerwany program dziala normalnie.

class ChildThread1 extends Thread {
	public void run()
	{
		for (int i = 0; i < 5; i++) {
			System.out.println("Wywolanie watku dziecka");
		}
	}
}

class Interrupt1 {
	public static void main(String[] args)
			throws InterruptedException
	{
		ChildThread1 thread = new ChildThread1();
		thread.start();

		// Glowny watek wywoluje przerwanie watku dziecka
		thread.interrupt();

		System.out.println("Glowny watek konczy");
	}
}
