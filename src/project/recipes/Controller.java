package project.recipes;

import project.recipes.records.Recipe;

import java.util.Scanner;

public class Controller {
    private final Model model;
    private final View view;
//    private final Scanner scanner;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
//        this.scanner = new Scanner(System.in);
    }

}