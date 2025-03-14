package exercises.homework_04;
public class Dessert {
    protected String name;
    protected double weight;
    protected int calories;

    public Dessert(String name, double weight, int calories) {
        this.name = name;
        this.weight = weight;
        this.calories = calories;
    }

    public String getDessertType() {
        return "dessert";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "Dessert: name=" + name + ", weight=" + weight + ", calories=" + calories;
    }
    
    
}
