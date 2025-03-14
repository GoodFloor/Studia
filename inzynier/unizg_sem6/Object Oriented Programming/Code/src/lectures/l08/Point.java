package lectures.l08;
public class Point<Type> {
    private Type x, y;

    public Point(Type x, Type y) {
        this.x = x;
        this.y = y;
    }

    public Type getX() {
        return x;
    }

    public void setX(Type x) {
        this.x = x;
    }

    public Type getY() {
        return y;
    }

    public void setY(Type y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point [x=" + x + ", y=" + y + "]";
    }

    public void printWith1(Point<Type> p) {
        System.out.println(this.toString() + " <-> " + p.toString());
    }

    public <U> void printWith2(Point<U> p) {
        System.out.println(this.toString() + " <-> " + p.toString());
    }

    public void printWith3(Point<Integer> p) {
        System.out.println(this.toString() + " <-> " + p.toString());
    }

    public void printWith4(Point<?> p) {
        System.out.println(this.toString() + " <-> " + p.toString());
    }
}
