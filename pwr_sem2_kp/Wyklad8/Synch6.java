// Obiekt wspolny dla dwoch watkow
class Common6
{
	// Deadlock
	synchronized public void write(int number)
	{
		for (int i = 0; i < 3; i++)
		{
			System.out.println("Thread number" + number + " iteration:" +i);
			try{ wait();} catch (Exception e){}
		}
        notifyAll();
	}
}

class Thread6 extends Thread
{

	Common6 c;
    int number;

	Thread6(Common6 c, int number)
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

public class Synch6{
	public static void main(String[] args)
	{
		Common6 obj = new Common6();

		Thread6 t1 = new Thread6(obj,1);
		Thread6 t2 = new Thread6(obj,2);

		t1.start();
		t2.start();
	}
}
