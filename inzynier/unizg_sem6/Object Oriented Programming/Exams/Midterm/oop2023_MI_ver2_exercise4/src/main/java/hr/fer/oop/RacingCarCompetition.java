package hr.fer.oop;

public class RacingCarCompetition extends Competition<RacingCar, RacingCar> {
    @Override
    public double rateA(RacingCar entity) {
        return rateB(entity);
    }
}
