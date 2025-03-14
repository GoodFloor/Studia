package exercises.homework_10;

public class Pair<T, U> {
    T x;
    U y;
    public Pair(T x, U y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public String toString() {
        return x + " " + y;
    }
}
