package project.recipes.records;

public enum Measurements {
        QUANTITY("pc"), LITERS("l"), KILOGRAMS("kg");
    public String description;

    Measurements(String description) {
        this.description = description;
    }

}
