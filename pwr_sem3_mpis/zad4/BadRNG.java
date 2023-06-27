import java.util.Random;

public class BadRNG implements RNG {
    private Random rand;

    public BadRNG() {
        rand = new Random();
    }

    @Override
    public int getInt(int start, int end) {
        int t = end - start + 1;
        return start + rand.nextInt(t);
    }
}
