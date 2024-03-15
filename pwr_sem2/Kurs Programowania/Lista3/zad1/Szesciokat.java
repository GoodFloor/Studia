public class Szesciokat extends Figura
{
    private double a;

    Szesciokat(double bok)
    {
        a = bok;
    }
    public double liczPole()
    {
        return ((a * a * 3) / 2) * Math.sqrt(3);
    }
    public double liczObwod()
    {
        return 6 * a;
    }
}
