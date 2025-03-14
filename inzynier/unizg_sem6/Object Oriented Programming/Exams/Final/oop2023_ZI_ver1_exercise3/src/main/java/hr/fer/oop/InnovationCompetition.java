package hr.fer.oop;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class InnovationCompetition {
    private List<Employee> employees;
    
    public InnovationCompetition() {
        employees = new LinkedList<>();
    }

    public void addEmployee(String firstName, String lastName, int proposedInnovations, int implementedInnovations) {
        employees.add(new Employee(firstName, lastName, proposedInnovations, implementedInnovations));
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void removeEmployees(List<String> removalNames) {
        employees.removeIf(e -> removalNames.contains(e.getLastName()));

    }

    public void rankEmployees() {
        employees.sort(new Ranker());
    }

    private class Ranker implements Comparator<Employee> {

        @Override
        public int compare(Employee e1, Employee e2) {
            if (e1.getImplementedInnovations() != e2.getImplementedInnovations()) {
                return e2.getImplementedInnovations() - e1.getImplementedInnovations();
            }
            if (e1.getProposedInnovations() != e2.getProposedInnovations()) {
                return e2.getProposedInnovations() - e1.getProposedInnovations();
            }
            return e1.getLastName().compareTo(e2.getLastName());
        }
    
    }
}
