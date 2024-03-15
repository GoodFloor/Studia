interface MetodyJednoarg
{
    public double liczPole(double a);
    public double liczObwod(double a);
}
interface MetodyDwuarg
{
    public double liczPole(double a, double b);
    public double liczObwod(double a, double b);
}

public class Figura
{
    public enum Figura1 implements MetodyJednoarg
    {
        KOLO
        {
            public double liczPole(double a)
            {
                return Math.PI * a * a;
            }
            public double liczObwod(double a)
            {
                return Math.PI * 2 * a;
            }
        }, 
        KWADRAT
        {
            public double liczPole(double a)
            {
                return a * a;
            }
            public double liczObwod(double a)
            {
                return 4 * a;
            }
        }, 
        PIECIOKAT
        {
            public double liczPole(double a)
            {
                return ((a * a) / 4) * Math.sqrt(25 + (10 * Math.sqrt(5)));
            }
            public double liczObwod(double a)
            {
                return 5 * a;
            }
        },
        SZESCIOKAT
        {
            public double liczPole(double a)
            {
                return ((a * a * 3) / 2) * Math.sqrt(3);
            }
            public double liczObwod(double a)
            {
                return 6 * a;
            }
        };
        public abstract double liczPole(double a);
        public abstract double liczObwod(double a);
    }
    public enum Figura2 implements MetodyDwuarg
    {
        PROSTOKAT
        {
            public double liczPole(double a, double b)
            {
                return a * b;
            }
            public double liczObwod(double a, double b)
            {
                return (2.0 * a + 2.0 * b);
            }
        },
        ROMB
        {
            public double liczPole(double a, double b)
            {
                return a * a * Math.sin(b / 180.0 * Math.PI);
            }
            public double liczObwod(double a, double b)
            {
                return 4 * a;
            }
        };
        public abstract double liczPole(double a, double b);
        public abstract double liczObwod(double a, double b);
    }
}