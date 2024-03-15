public class Romb extends Czworokat
{
    double k;
    Romb(double bok1, double kat)
    {
        super(bok1, bok1);
        k = kat;
    }
    public double liczPole()
    {
        return a * a * Math.sin(k / 180.0 * Math.PI);
    }
    public double liczObwod()
    {
        return 4 * a;
    }
}