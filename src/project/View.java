package project;

import project.users.UserRole;
import project.utils.Menu;

public class View {
    public View() {
        showStartScreen();
        showUserRoleOptions();
    }

    public void showStartScreen(){
        printProjectTitle();
    }


    public void showUserRoleOptions() {
        System.out.println("Please choose the user type:");

        UserRole[] users = UserRole.values();
        String optionFormat = "[%d] %s";

        for (UserRole user : users) {
            System.out.printf((optionFormat) + "%n", user.ordinal()+1, user.description);
        }

        System.out.println("Selected user: ");
    }

    public void showInvalidOptionMessage() {
        Menu.printWarningMessage("Please chose the right option");
    }
    public void showNumericErrorMessage() {
        Menu.printWarningMessage("The input is not valid");
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    private void printProjectTitle() {
        System.out.println("=== Recipe Manager ==");
        System.out.println();
    }
}
