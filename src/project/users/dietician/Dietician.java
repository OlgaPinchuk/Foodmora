package project.users.dietician;

import project.recipes.records.Recipe;
import project.users.User;
import project.users.UserRole;
import project.utils.Utils;

import java.util.ArrayList;

public class Dietician extends User {
    private ArrayList<Recipe> recipes;

    private final String[] mainMenuOptions = {"List all recipes", "Create a new recipe", "Switch a role", "Save and quit"};

    public Dietician() {
        // get recipe pool
    }
    public void showMainMenu() {
        greetUser();
        showUserMenu();
    }
    private void greetUser() {
        System.out.println();
        System.out.println("Welcome to FOODMORA!");
        System.out.println();
        System.out.println("You've logged in as a " + UserRole.DIETICIAN.description);
        Utils.clearConsole();
    };
    private void showUserMenu() {
        System.out.println("Please choose one of the following operations:");
        Utils.printList(mainMenuOptions);
    }

    private void showRecipes() {
        System.out.println("Recipes:");
        System.out.println();
    }

    private void printEmptyListMessage() {
        System.out.println("No items");
    }
}
