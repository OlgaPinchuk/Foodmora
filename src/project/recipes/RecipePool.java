package project.recipes;

import project.recipes.records.Recipe;

public class RecipePool {
    public RecipePool() {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);

    }

}
