package laboratories.lab01.task1;

public class Demo {

    public static void main(String[] args) {
        int someArray[] = new int[] {0, 67, -2, 0, -15, 0};
        int resultArray[] = Mover.moveZeros(someArray);
        for(int element: resultArray)
        System.out.print(element + " ");
    }
}