package project;

public class MainMenu {
    public MainMenu() {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);

        controller.requestUserRole();
    }
}
