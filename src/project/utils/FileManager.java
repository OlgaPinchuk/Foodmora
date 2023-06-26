package project.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileManager {
    public <T> ArrayList<T> readObjects(String fileName) {
        ArrayList<T> objectList = new ArrayList<>();
        try {
            Path path = Paths.get(fileName);
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
            if (Files.isReadable(path)) {
                FileInputStream file = new FileInputStream(fileName);
                ObjectInputStream stream = new ObjectInputStream(file);
                objectList = (ArrayList<T>) stream.readObject();
                stream.close();
                file.close();
            }
        } catch (IOException | ClassNotFoundException ignored) {
        }
        return objectList;
    }

    public <T> void writeObjects(String filename, ArrayList<T> objects) {
        try {
            Path path = Paths.get(filename);
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(objects);
            output.close();
            file.close();
            System.out.println("Objects saved to the file");
        } catch (IOException e) {
            System.out.println("File not found: " + e);
        }
    }


//    public ArrayList<Recipe> readRecipes(String fileName) {
//        return readObjects(fileName);
//    }
//
//    public void writeRecipe(String filename, ArrayList<Recipe> recipe) {
//        writeObjects(filename, recipe);
//    }

//    public ArrayList<UserWeek> readWeeks(String fileName) {
//        return readObjects(fileName);
//    }
//
//    public void writeWeek(String filename, ArrayList<UserWeek> userWeek) {
//        writeObjects(filename, userWeek);
//    }
}






//import project.recipes.records.Ingredient;
//import project.recipes.records.Measurements;
//import project.recipes.records.Recipe;
//
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.List;
//
//public class FileManager {
//
//    public FileManager(String filename) {
//
//    }
//
//    public static void main(String args[]) {
//
//        FileManager objectIO = new FileManager();
//
//        Recipe recipe = new Recipe("Pancakes", List.of(new Ingredient("Flour", Measurements.KILOGRAMS, 0.2), new Ingredient("Egg", Measurements.QUANTITY, 3)),List.of("Step 1", "Step 2"));
//    }
//
//
//    public boolean createFile(String filename) {
//        boolean success = false;
//        try {
//            FileWriter writer = new FileWriter(filename);
//            List<Recipe> recipies =
//        } catch (IOException e) {
//            System.out.println("There was a problem writing to  " + filename);
//        }
//    }
//
//
//
//}
