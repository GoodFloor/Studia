package laboratories.demo05.task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Demo {
    public static void main(String[] args) {
        Plant parsely = new Plant("Parsely", PlantType.HERB);
        Plant sage = new Plant("Sage", PlantType.HERB);
        Plant laurel = new Plant("Laurel", PlantType.BUSH);
        List<Plant> plants = new ArrayList<>(Arrays.asList(parsely, sage, laurel));
        Map<PlantType, Long> plantMap = Herbarium.getDistinctPlantsFilterAndCountByType(plants);

        System.out.println(plantMap); // {BUSH=1, HERB=2}
    }
}
