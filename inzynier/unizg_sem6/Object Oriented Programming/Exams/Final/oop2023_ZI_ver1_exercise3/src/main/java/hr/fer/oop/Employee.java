package hr.fer.oop;

public class Employee {
    private String firstName;
    private String lastName;
    private int proposedInnovations;
    private int implementedInnovations;

    public Employee(String firstName, String lastName, int proposedInnovations, int implementedInnovations) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.proposedInnovations = proposedInnovations;
        this.implementedInnovations = implementedInnovations;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getProposedInnovations() {
        return proposedInnovations;
    }

    public int getImplementedInnovations() {
        return implementedInnovations;
    }

    @Override
    public String toString() {
        return String.format("%s %s [proposed: %d, implemented: %d]", firstName, lastName, proposedInnovations, implementedInnovations);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Employee other = (Employee) obj;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        return true;
    }

}
