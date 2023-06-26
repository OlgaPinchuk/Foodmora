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
                Files.createDirectories(path.getParent());
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
            System.out.println(ignored.getMessage());
        }
        return objectList;
    }


    public <T> void writeObjects(String filename, ArrayList<T> objects) {
        try {
            if (objects.isEmpty()) {
                System.out.println("No objects to write");
                return;
            }

            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(objects);
            output.close();
            file.close();
            System.out.println("Objects saved to the file");
        } catch (IOException e) {
            System.out.println("Error while writing objects to the file: " + e.getMessage());
        }
    }
}
