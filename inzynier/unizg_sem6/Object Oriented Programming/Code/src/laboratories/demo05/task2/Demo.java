package laboratories.demo05.task2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        Fruit apple = new Fruit("Apple", FruitColor.RED);
        Fruit tangerine = new Fruit("Tangerine", FruitColor.ORANGE);
    
        List<Fruit> fruits = new ArrayList<>(Arrays.asList(apple, tangerine));
        List<Fruit> actual1 = fruits.stream()
            .filter(FruitFinder.allFruitThatMatchColorAndPattern(FruitColor.RED, "pp"))
            .collect(
                Collectors.toList());
    
        System.out.println(actual1); // [Fruit{name='Apple'}]
    
        List<Fruit> actual2 = fruits.stream()
            .filter(
                FruitFinder
                    .allFruitThatDoesNotMatchColorAndDoesNotContainPattern(FruitColor.RED, "pp")
            )
            .collect(
                Collectors.toList());
        
        System.out.println(actual2); // [Fruit{name='Tangerine'}]
    }
}
