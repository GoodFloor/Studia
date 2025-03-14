package hr.fer.oop;

public class Demo {

    public static void main(String[] args) {
        Individual individual = new Individual("Pero Perich", "password123", 5000, "Address 1", 1000);
        
        individual.performTransaction(-2000);
        
        System.out.println(individual.getName() + " balance: " + individual.getBalance());
        
        individual.performTransaction(-1000);
        
        System.out.println(individual.getName() + " balance: " + individual.getBalance());
        
        individual.changePassword("newp");
        
        System.out.println(individual.getPassword());
        
        individual.changePassword("properpass234243234");
        
        System.out.println(individual.getPassword());
    }
}