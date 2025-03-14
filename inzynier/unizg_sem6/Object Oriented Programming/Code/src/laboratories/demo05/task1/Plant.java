package laboratories.demo05.task1;

import java.util.Objects;

public class Plant {
    private final String name;
    private final PlantType type;

    public Plant(String name, PlantType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public PlantType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
        return true;
        }
        if (o == null || getClass() != o.getClass()) {
        return false;
        }
        Plant plant = (Plant) o;
        return Objects.equals(name, plant.name) &&
            type == plant.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }

}
