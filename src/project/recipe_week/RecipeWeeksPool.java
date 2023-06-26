package project.recipe_week;

import project.utils.FileManager;

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;

public class RecipeWeeksPool {
    private ArrayList<RecipeWeek> recipeWeeks;
    private String fileName = "src/project/data/weeks.txt";
    private FileManager fileManager;

    public RecipeWeeksPool() {
        this.fileManager = new FileManager();
        this.recipeWeeks = fileManager.readObjects(fileName);
    }

    public List<RecipeWeek> getRecipeWeeks() {
        return recipeWeeks;
    }

    public void setRecipeWeeks(List<RecipeWeek> recipeWeeks) {
        this.recipeWeeks = new ArrayList<>(recipeWeeks);
    }

    public int[] getAvailableWeekNumbers() {
        int[] weekNumbers = new int[recipeWeeks.size()];
        for (int i = 0; i < weekNumbers.length; i++) {
            weekNumbers[i] = recipeWeeks.get(i).getNumber();
        }
        return weekNumbers;
    }

    public boolean addRecipeWeek(RecipeWeek newWeek) {
        int weekNumber = newWeek.getNumber();
        boolean weekExists = recipeWeeks.stream()
                .anyMatch(week -> week.getNumber() == weekNumber);

        if (weekExists) {
            System.out.println("The week already exists. You can't overwrite it.");
            return false;
        }
        else if(!isValidYearWeek(weekNumber)) {
                System.out.println("The week number is not valid.");
                return false;
            }
         else {
            int insertIndex = (int) recipeWeeks.stream()
                    .filter(week -> week.getNumber() < weekNumber)
                    .count();

            recipeWeeks.add(insertIndex, newWeek);
            fileManager.writeObjects(fileName, recipeWeeks);
            return true;
        }
    }

    private boolean isValidYearWeek(int weekNumber) {
        int weeksOfYear = Calendar.getInstance().getActualMaximum(Calendar.WEEK_OF_YEAR);
        return weekNumber > 0 && weekNumber <= weeksOfYear;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("List of weeks").append("\n\n");
        for (RecipeWeek week : recipeWeeks) {
            int weekNumber = week.getNumber();
            sb.append("[").append(weekNumber).append("] Week ").append(weekNumber).append("\n");
        }
        return sb.toString();
    }
}