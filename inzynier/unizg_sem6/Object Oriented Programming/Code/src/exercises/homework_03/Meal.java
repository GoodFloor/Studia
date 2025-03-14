package exercises.homework_03;
public class Meal {
    private Food[] ingredients;
    public Meal(Food... ingredients) {
        this.ingredients = new Food[ingredients.length];
        for (int i = 0; i < ingredients.length; i++) {
            this.ingredients[i] = ingredients[i];
        }
    }
    public Meal(Meal meal, int weight) {
        int n = meal.getNumberOfIngredients();
        this.ingredients = new Food[n];
        int ratio = weight / meal.getWeight();
        for (int i = 0; i < n; i++) {
            Food food = new Food(meal.getIngredient(i).getType(), meal.getIngredient(i).getWeight() * ratio);
            this.ingredients[i] = food;
        }
    }
    public void printIngredients() {
        for (int i = 0; i < ingredients.length; i++) {
            System.out.printf("Ingredient[%d] = %s%n", i, ingredients[i].toStringInGrams());
        }
    }
    public int getNumberOfIngredients() {
        return ingredients.length;
    }
    public Food getIngredient(int index) {
        if (index < 0 || index >= ingredients.length) {
            return null;
        }
        return ingredients[index];
    }
    public double compare(Meal secondMeal) {
        int n1 = ingredients.length;
        int n2 = secondMeal.getNumberOfIngredients();
        int w1 = this.getWeight();
        int w2 = secondMeal.getWeight();
        double similarity = 0.;
        for (int i = 0; i < n1; i++) {
            double ratio1 = ingredients[i].getWeight() * 1. / w1;
            for (int j = 0; j < n2; j++) {
                if (ingredients[i].getType() == secondMeal.getIngredient(j).getType()) {
                    double ratio2 = secondMeal.getIngredient(j).getWeight() * 1. / w2;
                    if (ratio1 < ratio2) {
                        similarity += ratio1;
                    } else {
                        similarity += ratio2;
                    }
                }
            }
        }
        return similarity;
    }
    private int getWeight() {
        int sum = 0;
        for (Food food : ingredients) {
            sum += food.getWeight();
        }
        return sum;
    }
}
