public class TemperaturaTest {  
	public static void main( String args[] ) 
	{
		Temperatura a;    
		Temperatura b = new Temperatura();   
		Temperatura c = new Temperatura(100.0);


		a = b;

		System.out.println( "C:"+a+"; K:"+a.Kelvin()+"; F:"+a.Fahrenheit()+";");
		System.out.println( "C:"+b+"; K:"+b.Kelvin()+"; F:"+b.Fahrenheit()+";");
		System.out.println( "C:"+c+"; K:"+c.Kelvin()+"; F:"+c.Fahrenheit()+";");
    
		c = b;
		System.out.println( "C:"+c+"; K:"+c.Kelvin()+"; F:"+c.Fahrenheit()+";");
	
		b.setF( 10.0 );		
		System.out.println( "C:"+b+"; K:"+b.Kelvin()+"; F:"+b.Fahrenheit()+";");
		System.out.println( "C:"+c+"; K:"+c.Kelvin()+"; F:"+c.Fahrenheit()+";");
		System.out.println( "C:"+a+"; K:"+a.Kelvin()+"; F:"+a.Fahrenheit()+";");
    
		c.setF( 0.0 );
		//System.out.println( "C:"+b+"; K:"+b.Kelvin()+"; F:"+b.Fahrenheit()+";");
		//System.out.println( "C:"+c+"; K:"+c.Kelvin()+"; F:"+c.Fahrenheit()+";");
		//System.out.println( "C:"+a+"; K:"+a.Kelvin()+"; F:"+a.Fahrenheit()+";");
	}
}
