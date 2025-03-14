package lectures.l04;
public class Demo2 {
    public static void main(String[] args) {
        Point p = new Point(3, 4);
        Object o = p; //UPCAST
        Point p2 = (Point) o; // DOWNCAST
        System.out.println(o.toString());
        System.out.println(p2.toString());
        System.out.println(((Point)o).toString());
        System.out.println(o.getClass() == Point.class);
    }
}
