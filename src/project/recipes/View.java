package project.recipes;

import project.recipes.records.Ingredient;
import project.recipes.records.Recipe;
import project.utils.Utils;

public class View {
    public void viewRecipe(Recipe recipe) {
        String[] ingredients = recipe.ingredients().stream().map(Ingredient::toString).toArray(String[]::new);
        String[] steps = recipe.steps().toArray(String[]:: new);
        Utils.printList(ingredients, "Ingredients");
        Utils.printList(steps, "Steps");
    }


}
