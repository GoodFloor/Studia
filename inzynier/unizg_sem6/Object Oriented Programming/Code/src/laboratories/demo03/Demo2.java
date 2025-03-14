package laboratories.demo03;
public class Demo2 {
    private static void printList(SortedArrayList<?> sal) {
        System.out.println("------>");
        for (int i = 0; i < sal.size(); i++) {
            System.out.println(sal.get(i));
        }
    }
    public static void main(String[] args) {
        SortedArrayList<Integer> sal = new SortedArrayList<Integer>();
        sal.add(5);
        printList(sal);
        sal.add(1);
        printList(sal);
        sal.add(10);
        printList(sal);
        sal.add(3);
        printList(sal);
        sal.add(7);
        printList(sal);


    }
}
