package exercises.homework_04;
public class Person {
    protected String name;
    protected String surname;
    protected int age;
    public Person(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public boolean equals(Person other) {
        if (this.name == other.name && this.surname == other.surname && this.age == other.age) {
            return true;
        }
        return false;
    }
}
