package laboratories.demo03;
public class Demo1 {
    public static void main(String[] args) {
        Calculating c = new Calculator();
        // System.out.println(c.getResult()); //Calculator exception
        System.out.println(c.pressKey('5')); //prints 5
        System.out.println(c.pressKey('1')); //prints 51
        System.out.println(c.pressKey('5')); //prints 515
        System.out.println(c.pressKey('3')); //prints 5153
        // System.out.println(c.getResult()); //Calculator exception
        System.out.println(c.pressKey('-')); //prints -
        System.out.println(c.pressKey('+')); //prints +
        // System.out.println(c.getResult()); //Calculator exception
        System.out.println(c.pressKey('1')); //prints 1
        System.out.println(c.pressKey('3')); //prints 13
        // System.out.println(c.pressKey('+')); //Calculator exception
        //assuming we didn't call this + in the previous line and we type further:
        System.out.println(c.pressKey('3')); //prints 133
        System.out.println(c.pressKey('7')); //prints 1337
        System.out.println(c.getResult());   //prints 6490
        c.clear();
        // System.out.println(c.getResult()); //Calculator exception
        // System.out.println(c.pressKey('a')); //Calculator exception
    }
}
