package project.users;

import project.MainMenu;
import project.recipes.RecipeManager;
import project.recipes.RecipePool;
import project.utils.Menu;

import java.util.Scanner;

public class Dietician extends User  {
    private RecipeManager recipeManager;
    private Scanner scanner;
    public Dietician() {
        RecipePool recipePool = new RecipePool();
        this.recipeManager = new RecipeManager(recipePool);
        this.scanner = new Scanner(System.in);
        Menu.greetUser(UserRole.DIETICIAN.description);
        userMainMenu();
    }

    public void userMainMenu() {
        String[] mainMenuOptions = {
                "List all recipes",
                "Create a new recipe",
                "Edit a recipe",
                "Switch a role",
                "Save and quit"
        };

        Menu.showUserMenu(mainMenuOptions);
        requestMenuInput();
    }

    private void requestMenuInput() {
        String input = scanner.nextLine();

        try {
            int selectedOption = Integer.parseInt(input);
            handleMenuOption(selectedOption);
        }
        catch (NumberFormatException exception) {
            System.out.println("Invalid input format. Please enter a valid number.");
            requestMenuInput();
        }
        catch (IndexOutOfBoundsException exception) {
            System.out.println("Invalid menu option. Please select a valid option.");
            requestMenuInput();
        }
    }

    private void handleMenuOption(int selectedOption) {
        switch (selectedOption) {
            case 1 -> {
                recipeManager.listRecipes();
                Menu.backToMenu(this::userMainMenu);
            }
            case 2 -> {
                recipeManager.createRecipe();
                Menu.backToMenu(this::userMainMenu);
            }
            case 3 -> {
                recipeManager.editRecipe();
                Menu.backToMenu(this::userMainMenu);
            }
            case 4 -> switchRole();
            case 5 -> Menu.exit();
            default -> {
                System.out.println("Invalid menu option. Please select a valid option.");
                requestMenuInput();
            }
        }
    }

    private void switchRole () {
        new MainMenu();
    }
}
