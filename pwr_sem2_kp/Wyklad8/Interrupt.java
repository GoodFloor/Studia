// Watek dziecka jest przewane w watku glownym. Watek glowyny nie jest przerwany.

class ChildThread extends Thread {
	public void run()
	{
		try {
			for (int i = 0; i < 5; i++) {
				System.out.println("Wywolanie watku dziecka");

				// Usypianie watku
				Thread.sleep(1000);
			}
		}
		catch (InterruptedException e) {
			System.out.println("InterruptedException");
		}
	}
}

class Interrupt {
	public static void main(String[] args)
			throws InterruptedException
	{
		ChildThread thread = new ChildThread();
		thread.start();

		// Glowny watek wywoluje przerwanie watku dziecka
		thread.interrupt();

		System.out.println("Glowny watek konczy");
	}
}
