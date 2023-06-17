package project;

import project.users.dietician.Dietician;
import project.users.Patient;
import project.users.User;
import project.users.UserRole;

import java.util.Scanner;

public class Controller {
    private final Model model;
    private final View view;
    private final Scanner scanner;
    private UserRole selectedUserRole;
    private User user;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        this.scanner = new Scanner(System.in);
    }

    public void requestUserRole() {
        String input = scanner.nextLine();

        try {
            int selectedOption = Integer.parseInt(input.trim());
            selectedUserRole = UserRole.getUsers()[selectedOption-1];
            model.setUserRole(selectedUserRole);
            switch(selectedUserRole) {
                case PATIENT -> user = new Patient();
                case DIETICIAN -> user = new Dietician();
                default -> System.out.println("Please chose the right option");
            }
            user.showMainMenu();

        }
        catch (NumberFormatException e) {
            System.out.println("The error is occurred: " + e.getMessage());
            System.out.println("Please enter the numeric value");
        }

        requestUserRole();
    }
}
