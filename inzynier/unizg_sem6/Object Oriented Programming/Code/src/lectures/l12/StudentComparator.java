package lectures.l12;

import java.util.Comparator;

public class StudentComparator implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        // return o1.getId().compareTo(o2.getId());
        // return -o1.getId().compareTo(o2.getId());
        // return o1.firstName.compareTo(o2.firstName);
        int r = -o1.firstName.compareTo(o2.firstName);
        if (r != 0) 
            return r;
        r = o1.lastName.compareTo(o2.lastName);
        if (r != 0)
            return r;
        r = o1.studentId.compareTo(o2.studentId);
        return r;
    }
    
}
