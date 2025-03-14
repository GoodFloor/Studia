package laboratories.lab02.task2;

class HospitalImplementation implements Hospital {
    private int numberOfBeds;
    private int patientsCured;
    private int totalPatientsArrived;
    private int sickPatientsArrived;
    private int bedsAvailable;
    private int queueSize;

    HospitalImplementation(int beds) {
        if (beds < 1) {
            numberOfBeds = 1;
        } else {
            numberOfBeds = beds;
        }
        bedsAvailable = numberOfBeds;
    }

    @Override
    public void patientArrival(boolean isSick) {
        if (isSick) {
            if (bedsAvailable > 0) {
                bedsAvailable--;
            } else {
                queueSize++;
            }
            sickPatientsArrived++;
        }
        totalPatientsArrived++;
    }

    @Override
    public void patientDeparture() {
        if (bedsAvailable < numberOfBeds) {
            if (queueSize > 0) {
                queueSize--;
            } else {
                bedsAvailable++;
            }
            patientsCured++;
        }
    }

    @Override
    public int getHospitalSize() {
        return numberOfBeds;
    }

    @Override
    public int getOccupiedBeds() {
        return numberOfBeds - bedsAvailable;
    }

    @Override
    public int getQueueLength() {
        return queueSize;
    }

    @Override
    public int getTotalPatients() {
        return totalPatientsArrived;
    }

    @Override
    public float getStatistics() {
        return 100 * patientsCured / sickPatientsArrived;
    }
    
}
