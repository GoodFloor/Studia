// Przyklad 1
class Car  extends Vehicle {
    // Klasa wewnetrzna
    class MyActionListener extends ActionListener{
       public void actionPerformed(ActionEvent e)  {
          if (getState() != MOVING) fuelTimer.stop();
          else  {
            fuel -= 1;
            System.out.println("Fuel "+ fuel);
            if (fuel == 0) stop();
          }
       }
    };

    // Obiekt klasy wewnetrznej
    private ActionListener fuelConsumer  = new MyActionListener(); 
    // Obiekt wykorzystuje obiekt klasy wewnetrznej
    private Timer fuelTimer = new Timer(1000, fuelConsumer);
....
}

// Przyklad 2
class Car  extends Vehicle {

 // Tworzenie obiektu klasy wewnetrznej bez jawnego specyfikowania klasy wewnetrznej   
 private ActionListener fuelConsumer  = new ActionListener()    {
       public void actionPerformed(ActionEvent e)  {
          if (getState() != MOVING) fuelTimer.stop();
          else  {
            fuel -= 1;
            System.out.println("Fuel "+ fuel);
            if (fuel == 0) stop();
          }
       }
    };

 // Obiekt wykorzystuje obiekt klasy wewnetrznej
private Timer fuelTimer = new Timer(1000, fuelConsumer);
....
}


// Przyklad 3 Prosciej
class Car  extends Vehicle {

 // Obiekt wykorzystuje klase anonimowa
 // Jest jeden egzemplarz calej klasy (definiowany w konstruktorze Timer
private Timer fuelTimer = new Timer(1000,  new ActionListener() {

       public void actionPerformed(ActionEvent e)  {
          if (getState() != MOVING) fuelTimer.stop();
          else  {
            fuel -= 1;
           if (fuel == 0) stop();
          }
       }

    }   // nawias zamykajacy definicje klasy wewnetrznej
);      // nawias zamykajacy  new Timer(...),
        // i srednik konczacy instrukcje deklaracyjna

...
}