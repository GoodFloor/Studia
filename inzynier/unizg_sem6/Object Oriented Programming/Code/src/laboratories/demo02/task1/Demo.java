package laboratories.demo02.task1;
public class Demo {
    public static void main(String[] args) {
        Button lampBtn = new Button(new LampCapability(new Lamp()));
        lampBtn.click();
        lampBtn.click();

        Button heatingBtn = new Button(new HeatingCapability(new Heater()));
        heatingBtn.click();
        heatingBtn.click();
    }
}
