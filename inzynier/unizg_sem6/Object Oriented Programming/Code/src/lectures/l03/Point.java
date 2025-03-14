package lectures.l03;
public class Point {
    private double x;
    private double y;
    public Point() {}
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Point(Point p) {
        this(p.x, p.y);
    }
    public static Point randomPoint() {
        return new Point(Math.random(), Math.random());
    }
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    public void print() {
        System.out.printf("(%.2f; %.2f)", x, y);
    }
    public boolean isEqualTo(Point other) {
        return x == other.x && y == other.y;
    }
    //This is preferred rather than centerOf(a, b, ...) for 3 arguments
    public static Point centerOf(Point a, Point b, Point c) {
        return new Point((a.x + b.x + c.x) / 3., (a.y + b.y + c.y) / 3.);
    }
    // public static Point centerOf(Point[] points) {
    //     double x = 0.;
    //     double y = 0.;
    //     for (Point point : points) {
    //         x += point.x;
    //         y += point.y; 
    //     }
    //     return new Point(x / points.length, y / points.length);
    // }
    public static Point centerOf(Point a, Point b/*<- so it's at least 2 points*/, Point...points) {
        double x = 0.;
        double y = 0.;
        for (Point point : points) {
            x += point.x;
            y += point.y;
        }
        return new Point(x / points.length, y / points.length);
    }
    public double distanceToPoint(Point other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }
    public static Point getPointMostDistantFromOrigin(Point...points) {
        if (points.length == 0) {
            return null;
        }
        Point pos = points[0];
        double max = 0.;
        Point origin = new Point(0, 0);
        for (Point p : points) {
            double curr = origin.distanceToPoint(p);
            if (curr > max) {
                max = curr;
                pos = p;
            }
        }
        return pos;
    }
}
