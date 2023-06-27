// Klasa watku
class JoinTest extends Thread{  
    private int a;
    
    public JoinTest(int a)
    {this.a=a;}
    
    public void run(){  
     for(int i=1;i<=5;i++){  
      try{  
       // Usypianie watku   
       Thread.sleep(a*500);  
      }catch(Exception e){System.out.println(e);}  
       System.out.println(this.getName()+ " "+i);  
     }  
 }  
 
public static void main(String args[]){  
    
 // Tworzenie i uruchamianie watkow   
 JoinTest t1=new JoinTest(1);  
 JoinTest t2=new JoinTest(2);  
 JoinTest t3=new JoinTest(3);  
 t1.setName("Thread1");
 t2.setName("Thread2");
 t3.setName("Thread3");
 
 t1.start();  
 t2.start();

 try{     
  t1.join();   
 }catch(Exception e){System.out.println(e);}  
 
 // Watek t3 rozpocznie sie po zakonczeniu watku t1 (t1 wywoluje join).
  t3.start(); 
 }  
}  