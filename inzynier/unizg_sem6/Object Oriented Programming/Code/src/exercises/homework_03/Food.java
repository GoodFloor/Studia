package exercises.homework_03;
public class Food {
    private FoodType type;
    private int weight;
    public Food(FoodType type, int weight) {
        this.type = type;
        this.weight = weight;
    }
    public FoodType getType() {
        return type;
    }
    public int getWeight() {
        return weight;
    }
    public String toString() {
        return type.toString() + ", w - " + weight + "g";
    }
    public double getProtein() {
        return weight * type.getProtein() / 100.;
    }
    public double getCarbs() {
        return weight * type.getCarbs() / 100.;
    }
    public double getFat() {
        return weight * type.getFat() / 100.;
    }
    public String toStringInGrams() {
        return String.format("%s: p - %.1fg, c - %.1fg, f - %.1fg, w - %dg", type.getName(), getProtein(), getCarbs(), getFat(), weight);
    }
}
