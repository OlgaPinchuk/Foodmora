package project.recipe_week;

import project.recipes.records.Recipe;
import project.recipes.RecipePool;
import project.utils.Menu;
import project.utils.Utils;

import java.util.*;
import java.util.stream.IntStream;

public class RecipeWeekManager {
    private List<RecipeWeek> recipeWeeks;
    private RecipeWeeksPool recipeWeeksPool;
    private final Scanner scanner;

    public RecipeWeekManager() {
        this.recipeWeeksPool = new RecipeWeeksPool();
        this.recipeWeeks = recipeWeeksPool.getRecipeWeeks();
        this.scanner = new Scanner(System.in);
    }

    public void listWeeks() {
        if (recipeWeeks.isEmpty()) {
            Menu.printEmptyList();
            return;
        }

        while(true) {
            try {
                System.out.println(recipeWeeksPool);
                int weekNumber = requestWeekNumber();
                printWeek(weekNumber);
                break;
            } catch (NumberFormatException e) {
                Menu.printWarningMessage("Invalid input format. Please enter a valid number.");
                listWeeks();
            }
        }
    }

    public void listWeekRecipes() {
        if (recipeWeeks.isEmpty()) {
            Menu.printEmptyList();
            return;
        }

        while (true) {
            try {
                System.out.println(recipeWeeksPool);
                int weekNumber = requestWeekNumber();
                printWeekRecipes(weekNumber);
                break;
            } catch (NumberFormatException e) {
                Menu.printWarningMessage("Invalid input format. Please enter a valid number.");
                listWeekRecipes();
            }
        }
    }

    public void viewCurrentWeek() {
        int currentWeekNumber = Utils.getCurrentWeekNumber();
        printWeekRecipes(currentWeekNumber);
    }

    public void generateNewWeek() {
        RecipePool recipePool = new RecipePool();
        ArrayList<Recipe> allRecipes = recipePool.getRecipes();
        if (allRecipes.isEmpty()) {
            Menu.printWarningMessage("No recipes available in the pool.");
            return;
        }

        while (true) {
            try {
                System.out.println("Please specify the week number");
                int weekNumber = requestWeekNumber();

                RecipeWeek newWeek = new RecipeWeek(weekNumber);

                if (recipeWeeksPool.addRecipeWeek(newWeek)) {
                    Menu.printSuccessMessage("A new week was created successfully");
                    break;
                }
            } catch (NumberFormatException e) {
                Menu.printWarningMessage("Invalid week number. Please enter a valid integer.");
            } catch (Exception e) {
                Menu.printWarningMessage("An error occurred while generating a new week: " + e.getMessage());
            }
        }
    }

    private int requestWeekNumber() {
        System.out.println("Enter a week number:");
        int weekNumber = Integer.parseInt(scanner.nextLine());
        return weekNumber;
    }

    private boolean isExistingWeek(int weekNumber) {
        int[] availableWeekNumbers = recipeWeeksPool.getAvailableWeekNumbers();
        return IntStream.of(availableWeekNumbers)
                .anyMatch(number -> number == weekNumber);
    }

    private RecipeWeek findSelectedWeek(int weekNumber) {
        RecipeWeek selectedWeek = recipeWeeks.stream()
                .filter(week -> week.getNumber() == weekNumber)
                .findFirst()
                .orElse(null);

        return selectedWeek;
    }

    private void printWeek(int weekNumber) {
        RecipeWeek selectedWeek = findSelectedWeek(weekNumber);
        if (selectedWeek != null && isExistingWeek(weekNumber)) {
            System.out.println(selectedWeek.toString());
        } else {
            Menu.printWarningMessage("The selected week does not exist.");
        }
    }

    private void printWeekRecipes(int weekNumber) {
        if (!isExistingWeek(weekNumber)) {
            Menu.printWarningMessage("The chosen week doesn't exist. Please create it or choose an available one.");
            return;
        }

        RecipeWeek selectedWeek = findSelectedWeek(weekNumber);
        if (selectedWeek != null) {
            for (WeekDay day : WeekDay.values()) {
                Recipe recipe = selectedWeek.getWeekRecipes().get(day);
                System.out.println(day + " - " + recipe.toString());
            }
        } else {
            System.out.println("The selected week does not exist.");
        }
    }
}
