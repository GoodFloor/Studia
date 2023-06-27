public class WierszTrojkataPascala {
    //Atrybuty
    int[] wspolczynniki;

    //Konstruktor
    WierszTrojkataPascala(int n) {
        wspolczynniki = new int[n + 1];
        int temp = n / 2;
        if(n % 2 != 0)
            temp++;
        int wsp = 1;
        for(int k = 0; k <= temp; k++) {
            wspolczynniki[k] = wsp;
            wspolczynniki[n - k] = wsp;
            //wsp = (wsp * (n - k)) / (k + 1);
            wsp = (int)((wsp * 1.0) * (((n - k) * 1.0) / ((k + 1) * 1.0)));
        }
    }

    //Metoda
    int wspolczynnik(int m) {
        return wspolczynniki[m];
    }
}
