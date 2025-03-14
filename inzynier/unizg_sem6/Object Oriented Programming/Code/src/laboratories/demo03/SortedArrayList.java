package laboratories.demo03;
public class SortedArrayList<T extends Comparable<T>> {
    private T[] arr;
    private int size;

    @SuppressWarnings("unchecked")
    public SortedArrayList() {
        arr = (T[]) new Comparable[2];		
        size = 0; // Was not necessary
    }

    // Appends the specified element to the end of this list.
    public boolean add(T o) {
        ensureCapacity(size + 1);		
        int insertId = 0;
        while (insertId < size && arr[insertId].compareTo(o) < 0) {
            insertId ++;
        }
        size++;
        for (int i = insertId; i < size; i++) {
            T temp = null;
            if (i < size) {
                temp = arr[i];
            }
            arr[i] = o;
            o = temp;
        }		
        return true;
    }

    // Returns the number of elements in this list.
    public int size() {
        return size;
    }

    // Returns the element at the specified position in this list.
    public T get(int index) {
        T found = null;
        if (index < size) {
            found = arr[index];
        }
        return found;
    }
    

    // Increases the capacity of this SimpleArrayList instance, if
    // necessary, to ensure that it can hold at least the number of elements
    // specified by the minimum capacity argument.
    @SuppressWarnings("unchecked")
    private void ensureCapacity(int minCapacity) {
        int current = arr.length;
        if (minCapacity > current) {
            // Ensure some extra space so as not to copy too many times
            Comparable<T>[] newData = new Comparable[Math.max(current * 2, minCapacity)];
            System.arraycopy(arr, 0, newData, 0, size);
            arr = (T[]) newData;
        }

    }
}
