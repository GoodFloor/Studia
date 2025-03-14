package laboratories.lab02.task2;

/**
* The interface specifies a patient tracking system for one hospital.
* It must have a constructor that takes one argument: the number of beds which defines the hospital's capacity (int). This number must be greater than zero, otherwise it should have a value of 1.
*/
interface Hospital {
   /**
    * Patient's arrival at the hospital. Before occupying a bed in the hospital, the patient needs to be tested for the virus.
    * If the virus test results are positive and if there is an available bed, the patient needs to be committed at the hospital. If there aren't any available beds for the patient, he needs to be put in a queue.
    * Else, if the test results are negative, the patient is healthy and free to go home.
    * Make sure to keep count of all the patients arriving at the hospital, as well as to keep count of the patients who tested positive for the virus.
    * @param isSick contains the test results for each patient. If the parameter is true then the patient tested positive for the virus, else the patient's test results were negative.
    */
   public void patientArrival(boolean isSick);
   /**
    * Patient's departure (after the patient is cured). The number of occupied beds decreases by one. If there is a patient in the queue then the patient occupies the free bed and the number of patients in the queue decreases by one.  
    * Additionally, make sure to keep count of all the patients who have been cured. 
    */
   public void patientDeparture();
   /**
    *@return the total number of beds in the hospital. This number is defined during the hospital initialization.  
    */
   public int getHospitalSize();
   /**
    * @return the number of currently occupied beds. The initial value is zero. 
    */
   public int getOccupiedBeds();
   /**
    * @return the number of (sick) patients in the queue (waiting for a bed to be free to use). The initial value is zero. 
    */
   public int getQueueLength();
   /**
    * @return the total number of patients that have arrived at the hospital (both sick and healthy). 
    */
   public int getTotalPatients();
   /**
    * @return the percentage of cured patients ((the number of cured patients/ the number of patients who tested positive for the virus)*100)
    */
   public float getStatistics();
   
}
