package hr.fer.oop;

public class Director implements Person {
    private String name;
    protected int birthYear;
    private boolean retired;

    public Director(String name, int birthYear, boolean retired) {
        this.name = name;
        this.birthYear = birthYear;
        this.retired = retired;
    }
    
    @Override
    public String toString() {
        return "Director '" + name + "' (" + birthYear + ", retired: " + retired + ")";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getBirthYear() {
        return birthYear;
    }

    public boolean isRetired() {
        return retired;
    }

}
