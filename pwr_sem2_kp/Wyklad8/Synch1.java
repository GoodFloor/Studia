// Obiekt wspolny dla dwoch watkow
class Common1
{

	// Brak synchronizacji, nie ma gwarancji, ze jeden watek wykona cala metode
	public void write(int number)
	{
		for (int i = 0; i < 3; i++)
		{
			System.out.println("Thread number" + number + " iteration:" +i);
			try{Thread.sleep(400);} catch (Exception e){}
		}
	}
}

class Thread1 extends Thread
{

	Common1 c;
    int number;

	Thread1(Common1 c, int number)
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

public class Synch1{
	public static void main(String[] args)
	{
		Common1 obj = new Common1();

		Thread1 t1 = new Thread1(obj,1);
		Thread1 t2 = new Thread1(obj,2);

		t1.start();
		t2.start();
	}
}
