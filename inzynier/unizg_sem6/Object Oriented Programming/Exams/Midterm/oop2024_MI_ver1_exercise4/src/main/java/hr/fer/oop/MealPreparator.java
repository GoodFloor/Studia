package hr.fer.oop;

public class MealPreparator<A extends Nutritional, B extends Nutritional> implements Preparator<A, B, Meal> {

    @Override
    public Meal prepare(A ingredientA, B ingredientB) {
        return new Meal(ingredientA.getProtein() + ingredientB.getProtein(), ingredientA.getCarbs() + ingredientB.getCarbs(), ingredientA.getFat() + ingredientB.getFat());
    }
    
}
