package laboratories.lab02.task2;

public class Demo {
    public static void main(String[] args) {
        HospitalImplementation hi = new HospitalImplementation(-5);
                
        System.out.println(hi.getHospitalSize()); //returns 1
        System.out.println(hi.getOccupiedBeds()); //returns 0
        System.out.println(hi.getQueueLength()); //returns 0

        hi.patientArrival(true);
        System.out.println(hi.getOccupiedBeds()); //returns 1
        System.out.println(hi.getQueueLength()); //returns 0

        hi.patientArrival(true);
        System.out.println(hi.getOccupiedBeds()); //returns 1
        System.out.println(hi.getQueueLength()); //returns 1

        hi.patientArrival(false);
        System.out.println(hi.getTotalPatients()); //returns 3

        hi.patientArrival(false);
        System.out.println(hi.getTotalPatients()); //returns 4

        hi.patientArrival(true);
        System.out.println(hi.getOccupiedBeds()); //returns 1
        System.out.println(hi.getQueueLength()); //returns 2

        hi.patientDeparture();
        System.out.println(hi.getOccupiedBeds()); //returns 1
        System.out.println(hi.getQueueLength()); //returns 1
        hi.patientDeparture();
        System.out.println(hi.getOccupiedBeds()); //returns 1
        System.out.println(hi.getQueueLength()); //returns 0

        hi.patientArrival(true);
        System.out.println(hi.getStatistics()); //returns 50

        hi.patientDeparture();
        System.out.println(hi.getStatistics()); //returns 75
        
        hi.patientDeparture();
        hi.patientDeparture();
        System.out.println(hi.getOccupiedBeds()); //returns 0
        System.out.println(hi.getQueueLength()); //returns 0
    }
}
