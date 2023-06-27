// Obiekt wspolny dla dwoch watkow
class Common4
{

	// Brak synchonizacji na metodzie, ale jest synchronizacja na obiekt w run().
	public void write(int number)
	{
		for (int i = 0; i < 3; i++)
		{
			System.out.println("Thread number" + number + " iteration:" +i);
			try{Thread.sleep(400);} catch (Exception e){}
		}
	}
}

class Thread4 extends Thread
{
	// Reference variable of type Line.
	Common4 c;
    int number;

	Thread4(Common4 c, int number)
	{
		this.c=c;
        this.number = number;
	}

	@Override
	public void run()
	{
        // Obiekt zablokowany. Wywola sie caly kod dla danego watku.
        synchronized(c) 
        { 
            c.write(number); 
            try{Thread.sleep(400);} catch (Exception e){}
            c.write(number);          
        }
	}
}

public class Synch4{
	public static void main(String[] args)
	{
		Common4 obj = new Common4();

		// we are creating two threads which share
		// same Object.
		Thread4 t1 = new Thread4(obj,1);
		Thread4 t2 = new Thread4(obj,2);

		// both threads start executing .
		t1.start();
		t2.start();
	}
}
