package hr.fer.oop;

public class Competition<A extends Vehicle, B extends RacingCar> implements Duel<A, B> {

    @Override
    public double rateA(A entity) {
        return entity.getName().length();
    }

    @Override
    public double rateB(B entity) {
        return (entity.getName().length() + entity.getPower() + entity.getNitro()) / 3.;
    }

    @Override
    public String battle(A entityA, B entityB) {
        if (rateA(entityA) == rateB(entityB)) {
            return "DRAW";
        }
        if (rateA(entityA) > rateB(entityB)) {
            return entityA.toString();
        }
        return entityB.toString();
    }
}
