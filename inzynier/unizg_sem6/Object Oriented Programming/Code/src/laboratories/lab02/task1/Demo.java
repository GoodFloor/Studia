package laboratories.lab02.task1;

public class Demo {
    public static void main(String[] args) {
        Knight someKnight = new Knight(3, 4, FigureColor.BLACK);
        System.out.println(someKnight);

        // Can't move a knight for that number of steps
        someKnight.move(4, -4);
        System.out.println(someKnight);

        Figure someOtherKnight = new Knight(2, 2, FigureColor.WHITE);
        System.out.println(someOtherKnight);

        // A knight can move to that position
        someOtherKnight.move(1, 2);
        System.out.println(someOtherKnight);

        System.out.format("%d %d\n", someKnight.getPosX(), someKnight.getPosY());
    }
}
