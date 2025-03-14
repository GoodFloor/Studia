package lectures.l11;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyNumber implements Iterable<Integer> {

    private int num;

    public MyNumber(int num) {
        this.num = num;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new DigitIterator();
    }

    // Since DigitIterator is directly connected to MyNumber and has no use outside of it - it can be nested and private
    // It can be static - it means that it can be used as stand-alone class it doesn't have to be connected to particular object
    private class DigitIterator implements Iterator<Integer>{

        private int expOf10;
        private int num;

        // // Constructor for static class
        // public DigitIterator(MyNumber myNumber) {
        //     this.num = myNumber.num;    // Since it is nested class it has access to private fields
        //     expOf10 = (int) Math.pow(10, (int)Math.log10(num));
        // }

        // Constructor for non-static class
        public DigitIterator() {
            this.num = MyNumber.this.num;
            expOf10 = (int) Math.pow(10, (int)Math.log10(num));
        }

        @Override
        public boolean hasNext() {
            return expOf10 > 0;
        }

        @Override
        public Integer next() {
            if (hasNext()) {
                int digit = num / expOf10;
                num %= expOf10;
                expOf10 /= 10;
                return digit;
            } else {
                throw new NoSuchElementException("No more digits");
            }
        }

    }

    
}
