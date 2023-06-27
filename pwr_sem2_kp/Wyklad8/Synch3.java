// Obiekt wspolny dla dwoch watkow
class Common3
{

	// Mimo synchronizacji, nie ma gwarancji, ze wykona sie cala metoda dla jedego watku. Wait() zwalnia
    // blokady na obiekt.
    synchronized public void write(int number)
	{
		for (int i = 0; i < 3; i++)
		{
			System.out.println("Thread number" + number + " iteration:" +i);
			try{ wait(100);} catch (Exception e){}
		}
        notifyAll();
        
	}
}

class Thread3 extends Thread
{

	Common3 c;
    int number;

	Thread3(Common3 c, int number)
	{
		this.c=c;
        this.number = number;
	}

	@Override
	public void run()
	{
        c.write(number);
        c.write(number);		
	}
}

public class Synch3{
	public static void main(String[] args)
	{
		Common3 obj = new Common3();

		Thread3 t1 = new Thread3(obj,1);
		Thread3 t2 = new Thread3(obj,2);

		t1.start();
		t2.start();
	}
}
