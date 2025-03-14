package lectures.l08;
public class PointDiff<T, U> {
    private T x;
    private U y;

    public PointDiff(T x, U y) {
        this.x = x;
        this.y = y;
    }
    
    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }

    public U getY() {
        return y;
    }

    public void setY(U y) {
        this.y = y;
    }
    
    @Override
    public String toString() {
        return "PointDiff [x=" + x + ", y=" + y + "]";
    }
    
}
