package lectures.l03;

import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Rectangle r = new Rectangle();
        // r.setWidth(sc.nextInt());
        // r.setHeight(sc.nextInt());
        // System.out.println(r.area());

        Point p1 = new Point();
        p1.setX(0.52);
        p1.setY(3.15);
        Point p2 = new Point();
        p2.setX(0.52);
        p2.setY(3.15);
        Point p3 = new Point(p1.getX(), p1.getY());
        Point p4 = new Point(p1);
        Point p5 = p1;
        // if (p1 == p2) {
        //     System.out.println("p1 == p2");
        // } else {
        //     System.out.println("p1 != p2");
        // }
        // if (p1.isEqualTo(p2)) {
        //     p1.print();
        //     System.out.print(" is equal to ");
        //     p2.print();
        // } else {
        //     p1.print();
        //     System.out.print(" is NOT equal to ");
        //     p2.print();
        // }
        // System.out.println();
        System.out.println("p1\t\tp2\t\tp3\t\tp4\t\tp5");
        p1.print();
        System.out.printf("\t");
        p2.print();
        System.out.printf("\t");
        p3.print();
        System.out.printf("\t");
        p4.print();
        System.out.printf("\t");
        p5.print();
        System.out.printf("\n");
        p1.setX(10);
        p2.setX(20);
        p3.setX(30);
        p4.setX(40);
        p5.setX(50);
        p1.print();
        System.out.printf("\t");
        p2.print();
        System.out.printf("\t");
        p3.print();
        System.out.printf("\t");
        p4.print();
        System.out.printf("\t");
        p5.print();
        System.out.printf("\n\n");
        Point p6 = Point.randomPoint();
        Point p7 = Point.randomPoint();
        Point p8 = Point.randomPoint();
        p6.print();
        p7.print();
        p8.print();
        System.out.print("\nCenter1: ");
        Point.centerOf(p6, p7, p8).print();
        System.out.println();
        // Point[] points = new Point[] {p6, p7, p8};
        // System.out.print("\nCenter2: ");
        // Point.centerOf(points).print();
        System.out.println();

        System.out.println(p6.distanceToPoint(p7));
        Point.getPointMostDistantFromOrigin(p6, p7, p8).print();
        System.out.println();

        // Vector v = new Vector(14, 13);
        // v.print();

        // System.out.println(StringLab.stringFloss(args));
        // System.out.println(StringLab.upsideDown("aAbB%DFG&ghi"));
        sc.close();
    }
}
