package project.utils;

import java.util.Scanner;

public class Menu {

    public static void greetUser(String userRole) {
        System.out.println();
        System.out.println("Welcome to FOODMORA!");
        System.out.println();
        System.out.println("You've logged in as a " + userRole);
        Utils.clearConsole();
    };

    public static void showUserMenu(String[] options) {
        System.out.println("Please choose one of the following operations:");
        Utils.printList(options);
    }

    public static void printEmptyList() {
        System.out.println("The list is empty yet.");
    }

    public static void printSuccessMessage(String message) {
        System.out.println("\u001B[32m" + message + "\u001B[0m");
    }

    public static void printWarningMessage(String message) {
        System.out.println("\u001B[31m" + message + "\u001B[0m");
    }

    public static void backToMenu(Runnable menuCallback) {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("Press 'm' to go back to the menu...");

        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("m")) {
            Utils.clearConsole();
            menuCallback.run();
        } else {
            Menu.printWarningMessage("Invalid input");
            backToMenu(menuCallback);
        }
    }

    public static void exit() {
        System.out.println("Saving data and quitting...");
        System.exit(0);
    }
}
