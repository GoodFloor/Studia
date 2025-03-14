package lectures.l12;

import java.util.Comparator;

// Decorator for another Comparator
public class ReverseComparator implements Comparator<Student> {

    Comparator<Student> otherComparator;
    
    public ReverseComparator(Comparator<Student> otherComparator) {
        this.otherComparator = otherComparator;
    }

    @Override
    public int compare(Student o1, Student o2) {
        return -otherComparator.compare(o1, o2);
    }
    
}
