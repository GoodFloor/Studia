package laboratories.demo03;
class SimpleArrayList<T> {

    private T[] arr;
    private int size;

    @SuppressWarnings("unchecked")
    public SimpleArrayList() {
        arr = (T[]) new Object[2];		
        size = 0; // Was not necessary
    }

    // Appends the specified element to the end of this list.
    public boolean add(T o) {
        ensureCapacity(size + 1);		
        arr[size++] = o;		
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
            Object[] newData = new Object[Math.max(current * 2, minCapacity)];
            System.arraycopy(arr, 0, newData, 0, size);
            arr = (T[]) newData;
        }

    }
}