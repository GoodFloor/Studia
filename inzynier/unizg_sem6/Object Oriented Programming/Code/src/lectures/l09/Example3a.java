package lectures.l09;

public class Example3a {
    public static void main(String[] args) {
        String[] names = {"Boris", "Anna", "Boris", "Wang", "Anna", "Li", "Chen", "Li"};

        writeInReverse(names);
    }
    private static void writeInReverse(String[] names) {
        for (int i = names.length - 1; i >= 0; i--) {
            if (!isPresentInRange(names[i], names, i + 1, names.length)) {
                System.out.println(names[i]);
            }
        }
    }
    private static boolean isPresentInRange(String string, String[] array, int fromIndex, int toIndex) {
        for (int i = fromIndex; i < toIndex; i++) {
            if (array[i].equals(string)) {
                return true;
            }
        }
        return false;
    }
    
}
