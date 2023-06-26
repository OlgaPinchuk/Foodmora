package project.recipe_week;

import project.recipes.RecipePool;
import project.recipes.records.Recipe;
import project.utils.Menu;

import java.io.Serializable;
import java.util.*;

public class RecipeWeek implements Serializable {
    private Map<WeekDay, Recipe> weekRecipes;
    private int number;

    public RecipeWeek(int number) {
        this.number = number;
        this.weekRecipes = generateWeekRecipes();
    }

    public Map<WeekDay, Recipe> getWeekRecipes() {
        return weekRecipes;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int weekNumber) {
        this.number = weekNumber;
    }

    private Map<WeekDay, Recipe> generateWeekRecipes() {
        RecipePool recipePool = new RecipePool();
        ArrayList<Recipe> allRecipes = recipePool.getRecipes();
        int recipeCount = allRecipes.size();

        if (recipeCount == 0) {
            Menu.printWarningMessage("No recipes available in the pool. Please add recipes.");
            return null;
        }

        int daysNumber = WeekDay.values().length;

        Map<WeekDay, Recipe> weekRecipes = new HashMap<>();

        if(recipeCount < daysNumber) {
            Menu.printWarningMessage("Not enough recipes in the pool to generate a full week. Please add more recipes.");
            return null;
        }

        Random random = new Random();
        Set<Integer> selectedIndexes = new HashSet<>();

        while (selectedIndexes.size() < daysNumber) {
            int randomIndex = random.nextInt(recipeCount);

            if (!selectedIndexes.contains(randomIndex)) {
                selectedIndexes.add(randomIndex);
                weekRecipes.put(WeekDay.values()[selectedIndexes.size() - 1], allRecipes.get(randomIndex));
            }
        }
        return weekRecipes;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Recipe Week ").append(number).append("\n").append("\n");
        int index = 1;
        for (WeekDay day : WeekDay.values()) {
            Recipe recipe = weekRecipes.get(day);
            sb.append("[").append(index).append("] ").append(day).append(" - ").append(recipe.name()).append("\n");
            index++;
        }
        return sb.toString();
    }

}
