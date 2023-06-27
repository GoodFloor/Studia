import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Program dziala na dwoch watkach. Pierwszy watek wrzuca porcje 
// danych do obiektu (PipedOutputStream). Drugi odczytuje ja.
public class Pipe {
   final static PipedOutputStream pipedOut = new PipedOutputStream();
   final static PipedInputStream pipedIn = new PipedInputStream();
   
   class PipedOutputThread implements Runnable{
	@Override
	public void run() {
                    
    for (int j=1; j<=5; j++){
		for(int i=1;i<=5;i++){
            try 
            {
                pipedOut.write(("Message "+i+" "+ j + "\n").getBytes());					
            } catch (IOException e) {
                e.printStackTrace();
            }
		}
        try
        {
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
	}
   }
   
   class PipedInputThread implements Runnable{
	@Override
	public void run() {
        try {
            int i=0;
		    while((i=pipedIn.read())!=-1){
                System.out.println((char)i);}
            } catch (IOException e) {
		        e.printStackTrace();
            }
	    }
    }
   
    public static void main(String[] args) {
    	try {
            pipedOut.connect(pipedIn);
        } 
        catch (IOException e) {
		    e.printStackTrace();
        }
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.execute(new Pipe().new PipedOutputThread());
        service.execute(new Pipe().new PipedInputThread());
   }
} 