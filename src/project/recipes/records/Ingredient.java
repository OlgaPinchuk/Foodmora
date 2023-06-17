package project.recipes.records;

public record Ingredient(String name, Measurements measure, double amount) {
    @Override
    public String toString() {
        return name + " - " + amount + measure.description;
    }
}
