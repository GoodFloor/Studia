import java.io.BufferedWriter;
import java.io.FileWriter;

public class Generator {
    public static void main(String[] args) {
        int nMax = 100000;
        Randomizer rand = new Randomizer();
        BufferedWriter output;
        try {
            output = new BufferedWriter(new FileWriter("wyniki.txt"));
            output.append("n;Bn;Un;Ln;Cn;Dn;Dn-Cn");
        } catch (Exception e) {
            System.out.println("Błąd otwarcia pliku do zapisu");
            return;
        }
        //System.out.println("n;Bn;Un;Ln;Cn;Dn;Dn-Cn");
        for(int n = 1000; n <= nMax; n+= 1000) {
            for(int k = 0; k < 50; k++) {
                int[] bins = new int[n];
                for(int i = 0; i < n; i++) {
                    bins[i] = 0;
                }
                int firstBallToNotEmptyBin = -1;
                int emptyBins = n;
                int maxAmountOfBalls = 0;
                int twoBalledBins = 0;
                int iterator = 1;
                int un = 0;
                int cn = 0;
                while(twoBalledBins < n) {
                    int i = rand.getInt(0, n - 1);
                    bins[i]++;
                    if(bins[i] == 2) {
                        twoBalledBins++;
                        if(firstBallToNotEmptyBin == -1) {
                            firstBallToNotEmptyBin = iterator;
                        }
                    }
                    else if(bins[i] == 1) {
                        emptyBins--;
                        if(emptyBins == 0) {
                            cn = iterator;
                        }
                    }
                    if(iterator <= n && bins[i] > maxAmountOfBalls) {
                        maxAmountOfBalls = bins[i];
                    }
                    if(iterator == n) {
                        un = emptyBins;
                    }
                    iterator++;
                }
                try {
                    output.append(n + ";" + firstBallToNotEmptyBin + ";" + un + ";" + maxAmountOfBalls + ";" + cn + ";" + (iterator - 1) + ";" + (iterator - 1 - cn) + "\n");
                } catch (Exception e) {
                    System.out.println("Błąd zapisu pliku");
                    return;
                }
                //System.out.println(n + ";" + firstBallToNotEmptyBin + ";" + un + ";" + maxAmountOfBalls + ";" + cn + ";" + (iterator - 1) + ";" + (iterator - 1 - cn));
            }

        }
        try {
            output.close();
        } catch (Exception e) {
            System.out.println("Błąd zamknięcia pliku");
        }
    }
}