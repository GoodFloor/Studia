package exercises.homework_03;
public class FoodType {
    private static int counter;
    static {
        counter = 0;
    }
    private String name;
    private int protein;
    private int carbs;
    private int fat;
    public FoodType(String name, int protein, int carbs, int fat) {
        this.name = name;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        counter++;
    }
    public String getName() {
        return name;
    }
    public int getProtein() {
        return protein;
    }
    public int getCarbs() {
        return carbs;
    }
    public int getFat() {
        return fat;
    }
    public String toString() {
        return name + ": p - " + protein + "%, c - " + carbs + "%, f - " + fat + "%";
    }
    public static int getNumberOfCreatedInstances() {
        return counter;
    }
}
