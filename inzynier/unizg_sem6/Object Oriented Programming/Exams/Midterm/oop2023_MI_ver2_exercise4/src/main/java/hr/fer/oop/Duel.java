package hr.fer.oop;

public interface Duel<A, B> {
    public double rateA(A entity);
    public double rateB(B entity);
    public String battle(A entityA, B entityB);
}
