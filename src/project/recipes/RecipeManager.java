package project.recipes;

import project.recipes.records.*;
import project.utils.Menu;
import project.utils.Utils;

import java.util.List;
import java.util.Scanner;

public class RecipeManager {
    private final RecipePool recipePool;
    private final List<Recipe> recipes;
    private final Scanner scanner;

    public RecipeManager(RecipePool recipePool) {
        this.recipePool = recipePool;
        this.recipes = recipePool.getRecipes();
        this.scanner = new Scanner(System.in);
    }

    public void listRecipes() {
        if(recipes.isEmpty()) {
            Menu.printWarningMessage("No recipes found. Please add some");
            return;
        }
        viewAllRecipes();

        System.out.println("Enter the number of the recipe to view its details");
        System.out.println("Press 'c' to go to the previous menu");

        int chosenRecipeIndex = -1;
        boolean inputValid = false;

        while(!inputValid) {
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("c")) {
                System.out.println("Returning to previous menu...");
                return;
            }

            try {
                chosenRecipeIndex = Integer.parseInt(userInput);
                if (chosenRecipeIndex >= 0 && chosenRecipeIndex <= recipes.size()) {
                    inputValid = true;
                }
                else {
                    handleInvalidInput();
                }
            }
                catch(NumberFormatException e) {
                    handleInvalidInput();
                }
            }
            viewRecipeDetails(chosenRecipeIndex);
        }

    public void createRecipe() {
        String recipeName = addRecipeName();
        Recipe recipe = new Recipe(recipeName);

        boolean hasIngredients = addIngredients(recipe);
        boolean hasSteps = addSteps(recipe);

        if (!hasIngredients && !hasSteps) {
            Menu.printWarningMessage("Recipe creation canceled. No ingredients or steps provided.");
            return;
        }

        recipePool.addRecipe(recipe);
        Menu.printSuccessMessage("The recipe has been added successfully!");
    }

    public void editRecipe() {
        viewAllRecipes();
        System.out.println("Enter the recipe number to edit:");

        String userInput = scanner.nextLine().trim();
        int recipeNumber;

        try {
            recipeNumber = Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            handleInvalidInput();
            return;
        }

        Recipe recipe = recipePool.findRecipeByNumber(recipeNumber);

        if (recipe == null) {
            Menu.printWarningMessage("Recipe not found.");
            return;
        }

        System.out.println("Editing recipe: " + recipe.name());
        Recipe updatedRecipe = new Recipe(recipe.name());

        boolean hasUpdatedIngredients = addIngredients(updatedRecipe);

        boolean hasUpdatedSteps = addSteps(updatedRecipe);

        if (!hasUpdatedIngredients && !hasUpdatedSteps) {
            Menu.printWarningMessage("No changes made to the recipe.");
            return;
        }

        recipePool.updateRecipe(recipeNumber, updatedRecipe);
        Menu.printSuccessMessage("Recipe updated successfully.");
    }

    private String addRecipeName() {
        System.out.println("Enter the recipe name:");

        return scanner.nextLine().trim();
    }

    private void viewAllRecipes() {
        String[] recipeNames = recipePool.getRecipeNames();
        Utils.printList(recipeNames, "Recipes List");
    }

    private void viewRecipeDetails(int chosenRecipe) {
        if(chosenRecipe == 0) return;

        Recipe selectedRecipe = recipes.get(chosenRecipe - 1);

        System.out.println("Recipe details:");
        System.out.println(selectedRecipe.toString());

        System.out.println("Enter 'c' to go back to previous menu");

        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("c")) {
            listRecipes();
        }

    }

    private boolean addIngredients(Recipe recipe) {
        System.out.println("Enter the ingredients (ingredient, measure type, amount), one per line:");
        System.out.println("Valid measure types: pc, l or kg");
        System.out.println("Press Enter without any input to finish entering ingredients.");

        boolean hasIngredients = false;

        while (true) {
            String input = scanner.nextLine();

            if (input.isEmpty()) {
                break;
            }
            String[] lines = input.split(",");
            if (lines.length != 3) {
                handleInvalidInput();
                continue;
            }

            String ingredient = lines[0].trim();
            String measureTypeAbbreviation = lines[1].trim();
            MeasureType measureType = MeasureType.fromAbbreviation(measureTypeAbbreviation);

            if (measureType == null) {
                Menu.printWarningMessage("Invalid measure type. Please try again.");
                continue;
            }

            double amount;
            try {
                amount = Double.parseDouble(lines[2].trim());
            } catch (NumberFormatException e) {
                handleInvalidInput();
                continue;
            }

            Ingredient newIngredient = new Ingredient(ingredient, measureType, amount);
            recipe.addIngredient(newIngredient);
            hasIngredients = true;
        }
        return hasIngredients;
    }

    private boolean addSteps(Recipe recipe) {
        System.out.println("Enter the steps to complete the recipe, one per line:");
        System.out.println("Press Enter without any input to finish entering steps.");

        boolean hasSteps = false;

        while (true) {
            String step = scanner.nextLine();

            if (step.isEmpty()) {
                break;
            }

            recipe.addStep(step);
            hasSteps = true;

        }
        if (!hasSteps) {
            Menu.printWarningMessage("No steps provided.");
        }
        return hasSteps;
    }

    private void handleInvalidInput() {
        Menu.printWarningMessage("Invalid input. Please try again.");
    }
}