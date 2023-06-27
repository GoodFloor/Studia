// Obiekt wspolny dla dwoch watkow
class Common5
{
	// Mimo synchronizacji na metodzie i w run() nie ma gwarancji, ze sie wykona w calosci dla danego watku
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

class Thread5 extends Thread
{
	Common5 c;
    int number;

	Thread5(Common5 c, int number)
	{
		this.c=c;
        this.number = number;
	}

	@Override
	public void run()
	{
        synchronized(c) 
        { 
            c.write(number); 
            c.write(number);              
        }		
	}
}

public class Synch5{
	public static void main(String[] args)
	{
		Common5 obj = new Common5();

		Thread5 t1 = new Thread5(obj,1);
		Thread5 t2 = new Thread5(obj,2);

		t1.start();
		t2.start();
	}
}
