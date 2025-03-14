package exercises.homework_11;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ImmutableContainer implements Iterable<Integer> {
    private Integer[] numbers;
    public ImmutableContainer(Integer... num) {
        numbers = new Integer[num.length];
        for (int i = 0; i < num.length; i++) {
            numbers[i] = num[i];
        }
    }
    @Override
    public Iterator<Integer> iterator() {
        return new ImmutableContainerIteratorA(this);
    }
    private static class ImmutableContainerIteratorA implements Iterator<Integer> {

        private int index;
        private ImmutableContainer collection;
        
        public ImmutableContainerIteratorA(ImmutableContainer collection) {
            this.index = collection.numbers.length - 1;
            this.collection = collection;
        }

        @Override
        public boolean hasNext() {
            return index >= 0;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more numbers in this container");
            }
            Integer result = collection.numbers[index];
            index -= 2;
            return result;
        }
    
    }

    
}
