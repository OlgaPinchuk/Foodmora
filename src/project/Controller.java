package project;

import project.users.*;

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
                default -> view.showInvalidOptionMessage();
            }
        }
        catch (NumberFormatException e) {
            view.showNumericErrorMessage();
            view.printMessage("Please try again");
            requestUserRole();
        }
        catch(ArrayIndexOutOfBoundsException exception) {
            view.showInvalidOptionMessage();
            view.printMessage("Please try again");
            requestUserRole();
        }
    }
}

