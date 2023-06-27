// Obiekt wspolny dla dwoch watkow
class Common2
{

	// Synchronizacja. Jeden watek wykona cala metode, potem drugi watek zacznie dzialac.
	synchronized public void write(int number)
	{
		for (int i = 0; i < 3; i++)
		{
			System.out.println("Thread number" + number + " iteration:" +i);
			try{Thread.sleep(400);} catch (Exception e){}
		}
	}
}

class Thread2 extends Thread
{
	Common2 c;
    int number;

	Thread2(Common2 c, int number)
	{
		this.c=c;
        this.number = number;
	}

	@Override
	public void run()
	{
        c.write(number);
        try{Thread.sleep(400);} catch (Exception e){}
        c.write(number);
    }
}

public class Synch2{
	public static void main(String[] args)
	{
		Common2 obj = new Common2();

		Thread2 t1 = new Thread2(obj,1);
		Thread2 t2 = new Thread2(obj,2);

		t1.start();
		t2.start();
	}
}
