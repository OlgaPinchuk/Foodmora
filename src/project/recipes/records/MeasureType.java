package project.recipes.records;

import java.util.Arrays;

public enum MeasureType {
    QUANTITY("pc"),
    LITER("l"),
    KILOGRAM("kg");

    private String abbreviation;

    MeasureType(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public static MeasureType fromAbbreviation(String abbreviation) {
        return Arrays.stream(values())
                .filter(type -> type.getAbbreviation().equalsIgnoreCase(abbreviation))
                .findFirst()
                .orElse(null);
    }
}
