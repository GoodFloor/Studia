public abstract class Czworokat extends Figura
{
    protected double a, b;
    Czworokat(double bok1, double bok2)
    {
        a = bok1;
        b = bok2;
    }
    public abstract double liczPole();
    public double liczObwod()
    {
        return (2.0 * a + 2.0 * b);
    }
}