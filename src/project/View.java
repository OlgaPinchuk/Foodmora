package project;

import project.users.UserRole;

public class View {

    public View() {
        showStartScreen();
        showUserRoleOptions();
    }

    public void showStartScreen(){
        printProjectTitle();
    }

    private void printProjectTitle() {
        System.out.println("=== Recipe Manager ==");
        System.out.println();
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
}
