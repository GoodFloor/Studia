public class Test {
    public static void main(String[] args) {
        if(args.length == 0)
            return;
        try {
            int n = Integer.parseInt(args[0]);
            if(n < 0 || n > 33) {
                System.out.println(args[0] + " - nieprawidłowy numer wiersza");
                return;
            }
            WierszTrojkataPascala wierszN = new WierszTrojkataPascala(n);
            for(int i = 1; i < args.length; i++) {
                try {
                    int k = Integer.parseInt(args[i]);
                    if(k < 0 || k >= wierszN.wspolczynniki.length) {
                        System.out.println(args[i] + " - liczba spoza zakresu");
                    }
                    else {
                        System.out.println(args[i] + " - " + wierszN.wspolczynnik(k));
                    }
                }
                catch(NumberFormatException ex) {
                    System.out.println(args[i] + " - nieprawidłowa dana");
                }
            }

        }
        catch(NumberFormatException ex) {
            System.out.println(args[0] + " - nieprawidłowa dana");
        }
    }
}
