package laboratories.demo02.task2;

public class BankReceivingSystem implements ReceivingSystem {
    private int urgentListSize;
    private int nonUrgentListSize;
    private boolean employeeOccupied;
    private int totalArrived;

    @Override
    public void customerArrived(boolean urgent) {
        if (urgent) {
            urgentListSize++;
        } else {
            nonUrgentListSize++;
        }
        calculateEmployeeStatus();
        totalArrived++;
    }

    @Override
    public void customerLeft(boolean urgent) {
        if (urgent) {
            urgentListSize--;
        } else {
            nonUrgentListSize--;
        }
        calculateEmployeeStatus();
    }

    @Override
    public void calculateEmployeeStatus() {
        if (urgentListSize + nonUrgentListSize > 0) {
            employeeOccupied = true;
        } else {
            employeeOccupied = false;
        }
    }

    @Override
    public int getUrgentListSize() {
        return urgentListSize;
    }

    @Override
    public int getNonUrgentListSize() {
        return nonUrgentListSize;
    }

    @Override
    public boolean isEmployeeOccupied() {
        return employeeOccupied;
    }

    @Override
    public int getNumberOfArrivedCustomers() {
        return totalArrived;
    }
    
}
