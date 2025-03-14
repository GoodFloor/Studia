package lectures.l08;
public class Demo1 {
    public static void main(String[] args) {
        IntegerPoint ip1 = new IntegerPoint(6, 9);
        System.out.println(ip1);

/// But what if I want DoublePoint, do I need to create new class?

        ObjectPoint op1 = new ObjectPoint(4.2, "ab");
        System.out.println(op1);
        // double d1 = op1.getX();   // Error because getX() returns Object, not double

/// Better but it doesn't make sense to have a point of double and string. How to force user to usee one variable type?

        Point<Integer> p1 = new Point<Integer>(1, 2);
        Point<Double> p2 = new Point<Double>(1., 2.);
        Point<String> p3 = new Point<String>("ab", "cd");
        p2.printWith1(new Point<Double>(3.5, 4.5));
        p2.printWith2(p3);
        p2.printWith3(p1);
        p2.printWith4(p2);
        int i1 = p1.getX();
        System.out.println("p1.x = " + i1);

/// If I wanted to be able to have 2 different types I could do it

        PointDiff<Integer, String> pd1 = new PointDiff<Integer,String>(4, "ab");
        System.out.println(pd1);

/// Also there are some shortcuts

        Point<String> p4 = new Point<>("abc", "def");   // we don't need to specify Point type the second time
        var p5 = new Point<>("abc", "def");             // var automatically detects data type
        System.out.println(p4);
        System.out.println(p5);

/// Using generics is a good solution but what if I want to restrict possible types of points (e.g. only to numbers)

        // PointNum<String> pn1 = new PointNum<String>("123", "456");   // Illegal - String is not a subclass of number
        PointNum<Integer> pn2 = new PointNum<Integer>(1, 2);
        System.out.println(pn2);
        PointNum<Double> pn3 = new PointNum<Double>(1.5, 2.5);
        System.out.println(pn3);

    }
}
