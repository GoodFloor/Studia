package hr.fer.oop;

public class Demo {
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle("vehicle1");
		Vehicle vehicle2 = new Vehicle("vehicle2");
		
		Car car = new Car("car1 the best ever", 79);
		Car car2 = new Car("car2", 80);
		
		RacingCar racingCar = new RacingCar("racingCarMegaLong", 80, 58);
		RacingCar racingCar2 = new RacingCar("racingCar2", 99, 58);
		
		
		Competition<Vehicle, RacingCar> vehicleCompetition = new Competition<>();
		System.out.println("------VEHICLE COMPETITION----------");
		System.out.printf("%s rating: %.2f\n", vehicle, vehicleCompetition.rateA(vehicle));
		System.out.printf("%s rating: %.2f\n", racingCar, vehicleCompetition.rateB(racingCar));
		System.out.printf("Who won? %s\n", vehicleCompetition.battle(vehicle, racingCar));
		System.out.println();
		System.out.printf("%s rating: %.2f\n", vehicle2, vehicleCompetition.rateA(vehicle2));
		System.out.printf("%s rating: %.2f\n", racingCar, vehicleCompetition.rateB(racingCar));
		System.out.printf("Who won? %s\n", vehicleCompetition.battle(vehicle2, racingCar));
		System.out.println();
		System.out.printf("%s rating: %.2f\n", car2, vehicleCompetition.rateA(car2));
		System.out.printf("%s rating: %.2f\n", racingCar, vehicleCompetition.rateB(racingCar));
		System.out.printf("Who won? %s\n", vehicleCompetition.battle(car2, racingCar));
		System.out.println();
		System.out.printf("%s rating: %.2f\n", car, vehicleCompetition.rateA(car));
		System.out.printf("%s rating: %.2f\n", racingCar2, vehicleCompetition.rateB(racingCar2));
		System.out.printf("Who won? %s\n", vehicleCompetition.battle(car, racingCar2));
		System.out.println();
		System.out.printf("%s rating: %.2f\n", racingCar, vehicleCompetition.rateA(racingCar));
		System.out.printf("%s rating: %.2f\n", racingCar2, vehicleCompetition.rateB(racingCar2));
		System.out.printf("Who won? %s\n", vehicleCompetition.battle(racingCar, racingCar2));
		System.out.println();
		System.out.printf("%s rating: %.2f\n", racingCar, vehicleCompetition.rateA(racingCar));
		System.out.printf("%s rating: %.2f\n", racingCar2, vehicleCompetition.rateB(racingCar2));
		System.out.printf("Who won? %s\n", vehicleCompetition.battle(racingCar, racingCar2));
		System.out.println();
		
		RacingCarCompetition racingCarCompetition = new RacingCarCompetition();
		System.out.println("------RACING CAR COMPETITION----------");
		System.out.printf("%s rating: %.2f\n", racingCar, racingCarCompetition.rateA(racingCar));
		System.out.printf("%s rating: %.2f\n", racingCar2, racingCarCompetition.rateB(racingCar2));
		System.out.printf("Who won? %s\n", racingCarCompetition.battle(racingCar, racingCar2));
		System.out.println();
		
		Competition<RacingCar,RacingCar> racingCarCompetitionParametrized = new Competition<>();
		System.out.println("-----RACING CAR COMPETITION PARAMETRIZED--------");
		System.out.println("COMPETITION");
		System.out.printf("%s rating: %.2f\n", racingCar, racingCarCompetitionParametrized.rateA(racingCar));
		System.out.printf("%s rating: %.2f\n", racingCar2, racingCarCompetitionParametrized.rateB(racingCar2));
		System.out.printf("Who won? %s\n", racingCarCompetitionParametrized.battle(racingCar, racingCar2));
		
    }    
}
