package hr.fer.oop;

public interface Preparator<A, B, C> {
    public C prepare(A ingredientA, B ingredientB);
}
