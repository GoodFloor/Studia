package laboratories.demo05.task1;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Herbarium {
    /**
     * Takes a list of plants, removes duplicates, removes plants that are of type
     * {@link PlantType#TREE}, and then counts the number of occurrences of all other plant types.
     * Internally uses streams.
     *
     * @param plants a list of {@link Plant} instances
     * @return a map containing the amount of distinct plant types
     */
    public static Map<PlantType, Long> getDistinctPlantsFilterAndCountByType(List<Plant> plants) {
        Stream<Plant> ps = plants.stream();
        Map<PlantType, Long> result = ps.filter(p -> p.getType() != PlantType.TREE)
            .distinct()
            .collect(Collectors.toMap(
                t -> t.getType(), 
                t -> 1L,
                (t, u) -> t + 1));
        return result;
    }
}
