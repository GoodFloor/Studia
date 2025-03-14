package lectures.l04;
public class GlassOfWater {
    private int volume;
    private int water;
    private int cubes;
    private static final int CUBE_VOLUME = 50;

    public GlassOfWater(int volume) {
        this.volume = volume;
    }
    public void addWater(int waterAmount) {
        state("Before adding " + waterAmount + " ml of water");
        water += waterAmount;
        handleOverflow();
    }
    public void addIceCubes(int numberOfIceCubes) {
        state("Before adding " + numberOfIceCubes + " cubes");
        cubes += numberOfIceCubes;
        handleOverflow();
    }
    private void handleOverflow() {
        int overflow = water + cubes * CUBE_VOLUME - volume;
        if (overflow > 0) {
            if (water > 0 && overflow <= water) {
                System.out.printf("Removing %d ml of water%n", overflow);
                water -= overflow;
            } else {
                if (water > 0) {
                    System.out.printf("Removing %d ml of water%n", water);
                    overflow -= water;
                    water = 0;
                }
                int cubeOverflow = overflow / CUBE_VOLUME;
                cubes -= cubeOverflow;
                System.out.printf("Removing %d ice cubes%n", cubeOverflow);
            }
        }
        state("After");
    }
    private void state(String when) {
        System.out.printf("%s glass contains %d ml of water and %d ice cubes%n", when, water, cubes);
    }
    
}
