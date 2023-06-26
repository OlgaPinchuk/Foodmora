package project.recipes.records;

import java.io.Serializable;

public record Ingredient(String name, MeasureType measure, double amount) implements Serializable {
    @Override
    public String toString() {
        return name + " - " + amount + measure.getAbbreviation();
    }
}
