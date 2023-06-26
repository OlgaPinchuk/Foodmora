package project.recipes.records;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public record Recipe(String name, List<Ingredient> ingredients, List<String> steps) implements Serializable {
    public Recipe(String name) {
        this(name, new ArrayList<>(), new ArrayList<>());
    }
    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public void addStep(String step) {
        steps.add(step);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("\n").append("\n");
        sb.append("Ingredients:\n");
        for (Ingredient ingredient : ingredients) {
            String name = ingredient.name();
            double amount = ingredient.amount();
            String measure = ingredient.measure().getAbbreviation();

            sb.append("- ").append(name).append(" - ").append(amount).append(measure).append("\n");
        }
        sb.append("\n");
        sb.append("Steps:\n");
        for (String step : steps) {
            sb.append("- ").append(step).append("\n");
        }
        return sb.toString();
    }
}
