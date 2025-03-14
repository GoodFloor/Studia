package lectures.l05;
public class Demo2 {
    public static void method1(int i) {
        System.out.println("Method 1 working...");
        if (i < 0) {
            throw new IllegalArgumentException("Number is negative!");
        }
        System.out.println("Finished working!");
    }
    public static void main(String[] args) throws Exception {
        try (Resource r = new Resource()) {
            r.test();
        } catch (Exception e) {
            System.out.println("Error");
        }
        Resource r2 = new Resource();
        r2.test2();

        method1(1);
        try {
            method1(-1);
            r2.close();
        } catch (IllegalArgumentException e) {
            throw new MyCustomException(e);
        }
    }
}
