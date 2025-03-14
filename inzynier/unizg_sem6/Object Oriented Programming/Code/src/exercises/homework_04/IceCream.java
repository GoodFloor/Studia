package exercises.homework_04;
public class IceCream extends Dessert {
    private String flavour;
    private String color;
    
    public IceCream(String name, double weight, int calories, String flavour, String color) {
        super(name, weight, calories);
        this.flavour = flavour;
        this.color = color;
    }
    @Override
    public String getDessertType() {
        return "ice cream";
    }
    public String getFlavour() {
        return flavour;
    }
    public void setFlavour(String flavour) {
        this.flavour = flavour;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    @Override
    public String toString() {
        return super.toString() + ", flavour=" + flavour + ", color=" + color;
    }
    
}
