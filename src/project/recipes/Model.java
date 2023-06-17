package project.recipes;

import project.recipes.records.Recipe;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private List<Recipe> recipes;

    public Model() {
        this.recipes = new ArrayList<>();
    }


    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public void addRecipe(Recipe recipe) {
        this.recipes.add(recipe);
    }
    public void updateRecipe(int index, Recipe recipe) {
        this.recipes.set(index, recipe);
    }

}
