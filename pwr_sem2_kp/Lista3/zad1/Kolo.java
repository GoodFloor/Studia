public class Kolo extends Figura
{
    private double r;

    Kolo(double promien)
    {
        r = promien;
    }

    public double liczPole()
    {
        return Math.PI * r * r;
    }
    public double liczObwod()
    {
        return Math.PI * 2 * r;
    }
}