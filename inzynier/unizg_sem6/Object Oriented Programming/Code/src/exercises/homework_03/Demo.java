package exercises.homework_03;
public class Demo {
    public static void main(String[] args) {
        // FoodType banana = new FoodType("banana", 4, 93, 3);
        // Food oneBanana = new Food(banana, 110);
        // FoodType grapes = new FoodType("grapes", 4, 94, 2);
        // Food grapeCluster = new Food(grapes, 151);
        // Meal fruitSalad = new Meal(oneBanana, grapeCluster);
        // fruitSalad.printIngredients();
        // System.out.println("*** printing ingredients");
        // for (int i = -1; i <= fruitSalad.getNumberOfIngredients(); i++) {
        //     System.out.println("ingredient[" + i + "] = " + fruitSalad.getIngredient(i));
        // }

        // FoodType banana = new FoodType("banana", 4, 93, 3);
        // Food oneBanana = new Food(banana, 110);
        // FoodType grapes = new FoodType("grapes", 4, 94, 2);
        // Food grapeCluster = new Food(grapes, 151);
        // Meal fruitSalad = new Meal(oneBanana, grapeCluster);
        // System.out.println("* original meal:");
        // fruitSalad.printIngredients();
        // System.out.println("* new meal:");
        // Meal doubleFruitSalad = new Meal(fruitSalad, 522);
        // doubleFruitSalad.printIngredients();

        FoodType milk = new FoodType("milk", 20, 2, 5);
        FoodType polenta = new FoodType("polenta", 30, 20, 2);
        FoodType cornflakes = new FoodType("cornflakes", 50, 30, 0);
        Meal firstMeal = new Meal(new Food(milk, 700), new Food(polenta, 200));
        Meal secondMeal = new Meal(new Food(milk, 200), new Food(cornflakes, 100));
        System.out.printf("Similarity: %.1f%%", firstMeal.compare(secondMeal) * 100);
    }
}