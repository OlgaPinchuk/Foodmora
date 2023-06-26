package project.users;

import project.MainMenu;
import project.recipe_week.RecipeWeekManager;
import project.utils.Menu;

import java.util.Scanner;

public class Patient extends User {
    private  final RecipeWeekManager recipeWeekManager;
    private final Scanner scanner;

    public Patient() {
        this.recipeWeekManager = new RecipeWeekManager();
        this.scanner = new Scanner(System.in);
        Menu.greetUser(UserRole.PATIENT.description);
        userMainMenu();
    }

    public void userMainMenu() {
        String[] mainMenuOptions = {
                "List my weeks",
                "List recipes",
                "View current week",
                "Generate a new week",
                "Switch a role",
                "Save and quit"
        };

       Menu.showUserMenu(mainMenuOptions);
       requestMenuInput();
    }

    private void requestMenuInput() {
        while (true) {
            String input = scanner.nextLine();

            try {
                int selectedOption = Integer.parseInt(input);
                handleMenuOption(selectedOption);
                break;
            } catch (NumberFormatException | IndexOutOfBoundsException exception) {
                System.out.println("Invalid input format or menu option. Please enter a valid number.");
                requestMenuInput();
            }
        }
    }

    private void handleMenuOption(int selectedOption) {
        switch (selectedOption) {
            case 1 -> {
                recipeWeekManager.listWeeks();
                Menu.backToMenu(this::userMainMenu);
            }
            case 2 -> {
                recipeWeekManager.listWeekRecipes();
                Menu.backToMenu(this::userMainMenu);
            }
            case 3 -> {
                recipeWeekManager.viewCurrentWeek();
                Menu.backToMenu(this::userMainMenu);
            }
            case 4 -> {
                recipeWeekManager.generateNewWeek();
                Menu.backToMenu(this::userMainMenu);
            }
            case 5 -> switchRole();
            case 6 -> Menu.exit();
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
