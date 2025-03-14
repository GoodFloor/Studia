package lectures.l12;

// import java.util.ArrayList;
// import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CompositeComparator<T> implements Comparator<T> {

    private List<Comparator<T>> comparators;

    // public CompositeComparator(Comparator<T>... comparators) {
    //     this.comparators = new ArrayList<>(comparators.length);
    //     Collections.addAll(this.comparators, comparators);
    // }

    public CompositeComparator(List<Comparator<T>> comparators) {
        this.comparators = comparators;
    }

    @Override
    public int compare(T o1, T o2) {
        for (Comparator<T> comparator : comparators) {
            int r = comparator.compare(o1, o2);
            if (r != 0) {
                return r;
            }
        }
        return 0;
    }
    
}
