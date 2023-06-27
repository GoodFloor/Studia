interface Pole
{
    public double liczPole();
}
interface Obwod
{
    public double liczObwod();
}

public abstract class Figura implements Pole, Obwod
{
    /*public abstract double liczPole();
    public abstract double liczObwod();*/
}
