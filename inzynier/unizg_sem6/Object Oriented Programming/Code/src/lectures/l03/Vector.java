package lectures.l03;
public class Vector {
    public final static Vector e1 = new Vector(1, 0);
    public final static Vector e2 = new Vector(0, 1);
    public static Vector base1;
    public static Vector base2;
    static {
        base1 = e1;
        base2 = e2;
    }
    private Point p;
    public Vector(Point p) {
        this.p = new Point(p);
    }
    public Vector(double x, double y) {
        this.p = new Point(x, y);
    }
    public void print() {
        double x = 0., 
            y = 0., 
            r = p.getX(), 
            q = p.getY(), 
            a = e1.p.getX(), 
            b = e1.p.getY(), 
            c = e2.p.getX(), 
            d = e2.p.getY();
        // Pamiętaj cholero nie dziel przez zero
        if (b == 0) {
            y = q / d;
            x = (r - y * c) / a;
        } else {
            y = (r - a / b * q) / (c - a * d / b);
            x = (r - y * c) / a;
        }
        System.out.printf("(%.2f; %.2f) = %.2f(%.2f; %.2f) + %.2f(%.2f; %.2f)\n", r, q, x, a, b, y, c, d);
    }
}
