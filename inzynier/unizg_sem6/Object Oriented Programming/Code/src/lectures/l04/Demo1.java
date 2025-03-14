package lectures.l04;
public class Demo1 {
    public static void main(String[] args) {
        GlassOfWater glass = new GlassOfWater(250);
        glass.addWater(200);
        System.out.println();
        glass.addIceCubes(3);
        System.out.println();
        glass.addIceCubes(4);
        System.out.println();
        glass.addWater(100);
        
    }
}
