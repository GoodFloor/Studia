package laboratories.lab01.task1;

public class Mover {
    public static int[] moveZeros(int[] originalArray) {
        int[] newArray = new int[originalArray.length];
        int j = 0;
        for (int i = 0; i < originalArray.length; i++) {
            if (originalArray[i] != 0) {
                newArray[j] = originalArray[i];
                j++;
            }
        }
        return newArray;
    }
}
