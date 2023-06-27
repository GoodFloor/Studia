public class Test
{
    public static void main(String[] args)
    {
        if(args.length == 0)
            return;

        String kolejnosc = args[0];
        int licznik = 0;
        
        for(int i = 0; i < kolejnosc.length(); i++)
        {
            licznik++;
            //Sprawdzanie czy jeszcze są argumenty
            if(licznik >= args.length)
            {
                System.out.println("Podano za mało argumentów!");
                return;
            }

            try{
                switch (kolejnosc.charAt(i)) {
                    case 'o':
                        double r = Double.parseDouble(args[licznik]);
                        if(r <= 0)
                        {
                            System.out.println("Długość promienia musi być dodatnia");
                            continue;
                        }
                        System.out.println(kolejnosc.charAt(i) + "." + i + ": \tPole = " + Figura.Figura1.KOLO.liczPole(r) + "; \n\tObwód = " + Figura.Figura1.KOLO.liczObwod(r));
                        break;

                    case 'c':
                        if(args.length - licznik < 5)
                        {
                            System.out.println("Podano za mało argumentów!");
                            return;
                        }
                        double bok1 = Double.parseDouble(args[licznik]);
                        licznik++;
                        double bok2 = Double.parseDouble(args[licznik]);
                        licznik++;
                        double bok3 = Double.parseDouble(args[licznik]);
                        licznik++;
                        double bok4 = Double.parseDouble(args[licznik]);
                        licznik++;
                        double kat = Double.parseDouble(args[licznik]);
                        if(bok1 <= 0 || bok2 <= 0 || bok3 <= 0 || bok4 <= 0)
                        {
                            System.out.println("Długość boku musi być dodatnia");
                            continue;
                        }
                        if(kat <= 0 || kat >= 180)
                        {
                            System.out.println("Kąt powinien być z przedziału (0; 180)");
                            continue;
                        }
                        else if(kat != 90)
                        {
                            if(bok1 == bok2 && bok1 == bok3 && bok1 == bok4)
                                System.out.println(kolejnosc.charAt(i) + "." + i + ": \tPole = " + Figura.Figura2.ROMB.liczPole(bok1, kat) + "; \n\tObwód = " + Figura.Figura2.ROMB.liczObwod(bok1, kat));
                            else 
                            {
                                System.out.println("Romb powinien mieć wszystkie boki tej samej długości");
                                continue;
                            }
                        }
                        else
                        {
                            if(bok1 == bok2 && bok1 == bok3 && bok1 == bok4)
                                System.out.println(kolejnosc.charAt(i) + "." + i + ": \tPole = " + Figura.Figura1.KWADRAT.liczPole(bok1) + "; \n\tObwód = " + Figura.Figura1.KWADRAT.liczObwod(bok1));
                            else if((bok1 == bok2 && bok3 == bok4) || (bok1 == bok3 && bok2 == bok4) || (bok1 == bok4 && bok3 == bok2))
                            {
                                if(bok1 == bok2)
                                    System.out.println(kolejnosc.charAt(i) + "." + i + ": \tPole = " + Figura.Figura2.PROSTOKAT.liczPole(bok1, bok3) + "; \n\tObwód = " + Figura.Figura2.PROSTOKAT.liczObwod(bok1, bok3));
                                else
                                    System.out.println(kolejnosc.charAt(i) + "." + i + ": \tPole = " + Figura.Figura2.PROSTOKAT.liczPole(bok1, bok2) + "; \n\tObwód = " + Figura.Figura2.PROSTOKAT.liczObwod(bok1, bok2));
                            }
                            else
                            {
                                System.out.println("Prostokąt powinien mieć dwie pary boków tej samej długości");
                                continue;
                            }
                        }
                        break;

                    case 'p':
                        double a = Double.parseDouble(args[licznik]);
                        if(a <= 0)
                        {
                            System.out.println("Długość boku musi być dodatnia");
                            continue;
                        }
                        System.out.println(kolejnosc.charAt(i) + "." + i + ": \tPole = " + Figura.Figura1.PIECIOKAT.liczPole(a) + "; \n\tObwód = " + Figura.Figura1.PIECIOKAT.liczObwod(a));
                        break;

                    case 's':
                        double b = Double.parseDouble(args[licznik]);
                        if(b <= 0)
                        {
                            System.out.println("Długość boku musi być dodatnia");
                            continue;
                        }
                        System.out.println(kolejnosc.charAt(i) + "." + i + ": \tPole = " + Figura.Figura1.SZESCIOKAT.liczPole(b) + "; \n\tObwód = " + Figura.Figura1.SZESCIOKAT.liczObwod(b));
                        break;

                    default:
                        System.out.println("Podano niewłaściwy rodzaj figury geometrycznej!");
                        continue;
                }
            }
            catch(Exception e){
                System.out.println("Podano nieprawidłowy argument!");
            }
        }
    }
}