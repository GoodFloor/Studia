/**
 * Mersenne Twister RNG
 */
public class GoodRNG implements RNG {
    private static int w;
    private static int n;
    private static int m;
    private static int r;
    private static int a;
    private static int u;
    private static int d;
    private static int s;
    private static int b;
    private static int t;
    private static int c;
    private static int l;
    private static int f;
    private int[] MT;
    private int index;
    private static int lower_mask;
    private static int upper_mask;

    
    public GoodRNG(int seed) {
        w = 32;
        n = 624;
        m = 397;
        r = 31;
        a = 0x9908B0DF;
        u = 11;
        d = 0xFFFFFFFF;
        s = 7;
        b = 0x9D2C5680;
        t = 15;
        c = 0xEFC60000;
        l = 18;
        f = 1812433253;
        MT = new int[n];
        index = n + 1;
        lower_mask = (1 << r) - 1;
        upper_mask = (~lower_mask) & 0xFFFFFFFF;
        seed_mt(seed);
    }
    public GoodRNG() {
        this(5489);
    }
    private void seed_mt(int seed) {
        index = n;
        MT[0] = seed;
        for(int i = 1; i < n; i++) {
            int temp = f * (MT[i-1] ^ (MT[i-1] >> (w-2))) + i;
            MT[i] = temp & 0xFFFFFFFF;
        }
    }
    private int extract_number() throws Exception{
        if(index >= n) {
            if(index > n) {
                throw new Exception();
            }
            twist();
        }
        int y = MT[index];
        y = y ^ ((y >> u) & d);
        y = y ^ ((y << s) & b);
        y = y ^ ((y << t) & c);
        y = y ^ (y >> l);

        index++;
        return y & 0xFFFFFFFF;

    }
    private void twist() {
        for (int i = 0; i < n; i++) {
            int x = (MT[i] & upper_mask) | (MT[(i+1)%n] & lower_mask);
            int xA = x >> 1;
            if((x % 2) != 0) {
                xA = xA ^ a;
            }
            MT[i] = MT[(i+m)%n] ^ xA;
        }
        index = 0;
    }
    public int getInt(int start, int end) {
        try {
            return start + extract_number() % (end - start + 1);
        } catch (Exception e) {
            return -1;
        }
    }
}
