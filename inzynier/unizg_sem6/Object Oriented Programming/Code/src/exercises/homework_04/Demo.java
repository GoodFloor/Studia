package exercises.homework_04;
public class Demo {
    public static void main(String[] args) {
        // Cake c = new Cake("Chockolate cake", 100, 1000, true, "chocolate");
        // IceCream ic = new IceCream("Grycan Vanilla", 500, 350, "vanilla", "white");
        // System.out.println(c);
        // System.out.println(ic);
        // System.out.println(c.getDessertType());

        Person[] personArr = {
            new Teacher("Jacek", "Cichon", 75, "jacek.cichon@pwr.edu.pl", "Wstep do Kombinatoryki", 20000),
            new Student("Lukasz", "Machnik", 22, "268456", (short)3),
            new Teacher("Zbigniew", "Golebiewski", 37, "zbigniew.golebiewski@pwr.edu.pl", "Algorytmy i Struktury Danych", 15000),
            new Student("Zofia", "Wiora", 22, "268436", (short)3),
            new Teacher("Maciek", "Gebala", 44, "maciej.gebala@pwr.edu.pl", "Jezyki Formalne i Techniki Translacji", 12000)
        };
        double avgSalary = 0.;
        int nOfTeachers = 0;
        for (Person person : personArr) {
            System.out.println(person.getName() + " " + person.getSurname());
            if (person.getClass() == Teacher.class) {
                avgSalary += ((Teacher)person).getSalary();
                nOfTeachers++;
            }
        }
        avgSalary /= nOfTeachers;
        System.out.println("Average salary: " + avgSalary);


        Person p1 = new Person("Ivo","Ivic", 20);
        Person p2 = new Person("Ivo","Ivic", 20);
        Person p3 = new Student("Ivo","Ivic", 20, "0036312123", (short)3);
        Person p4 = new Student("Marko","Marić", 25, "0036312123", (short)5);
        System.out.println(p1.equals(p2));
        System.out.println(p1.equals(p3));
        System.out.println(p3.equals(p4));

    }
}
