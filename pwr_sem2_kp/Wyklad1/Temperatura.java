/** Klasa do operacji na temperaturach */

public class Temperatura {
    
        // Pola (Atrybuty)
	private double t;  
        
        // Metody 
	public double Kelvin() { return t+273.15; }
	public double Fahrenheit() { return 1.8*t+32; }
	public double Celsjusz() { return t; }
  
	public void set(double t) { this.t = t; }
	public void setF(double t) { this.t = 5.0*(t-32)/9.0; }
	public void setK(double t) { this.t = t-273.15; }
  
	// Konstruktory
	Temperatura() { t = 0.0; }
	Temperatura(double t) { this.t = t;}
  
	// Konwersja do String
	public String toString() { return Double.toString(t); }
}
