package project.recipes.records;

import java.util.List;

public record
Recipe(String name, List<Ingredient> ingredients, List<String> steps) {
}
