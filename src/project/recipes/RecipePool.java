package project.recipes;

import project.recipes.records.Recipe;
import project.utils.FileManager;

import java.util.ArrayList;

public class RecipePool {
    private ArrayList<Recipe> recipes;
    private FileManager fileManager;
    String fileName;

    public RecipePool() {
        this.fileName = "src/project/data/recipes.txt";
        this.fileManager = new FileManager();
        this.recipes = fileManager.readObjects(fileName);
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    public String[] getRecipeNames() {
        return recipes.stream().map(Recipe::name).toArray(String[]::new);
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
        saveRecipesToFile();
    }

    public void updateRecipe(int recipeNumber, Recipe updatedRecipe) {
        if (recipeNumber >= 1 && recipeNumber <= recipes.size()) {
            recipes.set(recipeNumber - 1, updatedRecipe);
            saveRecipesToFile();
        }
    }

    public Recipe findRecipeByNumber(int recipeNumber) {
        if (recipeNumber >= 1 && recipeNumber <= recipes.size()) {
            return recipes.get(recipeNumber - 1);
        }
        return null;
    }

    private void saveRecipesToFile() {
        fileManager.writeObjects(fileName, recipes);
    }

}
