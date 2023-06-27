public class Pieciokat extends Figura
{
    private double a;

    Pieciokat(double bok)
    {
        a = bok;
    }
    public double liczPole()
    {
        return ((a * a) / 4) * Math.sqrt(25 + (10 * Math.sqrt(5)));
    }
    public double liczObwod()
    {
        return 5 * a;
    }
}