package lectures.l08;
public class PointNum<T extends Number> {
    private T x;
    private T y;
    
    public PointNum(T x, T y) {
        this.x = x;
        this.y = y;
    }
    public T getX() {
        return x;
    }
    public void setX(T x) {
        this.x = x;
    }
    public T getY() {
        return y;
    }
    public void setY(T y) {
        this.y = y;
    }
    @Override
    public String toString() {
        return "PointNum [x=" + x + ", y=" + y + "]";
    }
    
}
