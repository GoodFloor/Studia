package hr.fer.oop;

public class FruitSaladPreparator extends MealPreparator<Fruit, Fruit> {
    @Override
    public Meal prepare(Fruit ingredientA, Fruit ingredientB) {
        Meal salad = new Meal("FRUIT SALAD with " + ingredientA.getName() + " and " + ingredientB.getName(), ingredientA.getProtein() + ingredientB.getProtein(), ingredientA.getCarbs() + ingredientB.getCarbs(), ingredientA.getFat() + ingredientB.getFat());

        return salad;
    }
}
