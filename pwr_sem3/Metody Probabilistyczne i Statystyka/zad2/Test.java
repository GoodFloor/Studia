public class Test {
    public static void main(String[] args) {
        Randomizer rand = new Randomizer();
        int size = 100;
        int[] tab = new int[size];
        int n = 100000;
        for (int i = 0; i < tab.length; i++) {
            tab[i] = 0;
        }
        for(int i = 0; i < n; i++) {
            int t = rand.getInt(0, size - 1);
            tab[t]++;
        }
        for(int i = 0; i < tab.length; i++) {
            System.out.println(i + "\t" + ((tab[i] * 1.0) / (1.0 * n) * 100.0) + "%");
        }
    }
}
