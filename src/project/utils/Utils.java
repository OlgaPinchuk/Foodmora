package project.utils;

import java.util.Calendar;

public class Utils {
    public static void printList( String[] listItems, String header) {
        printListHeader(header);
        printListItems(listItems);
    }


    public static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static int getCurrentWeekNumber() {
        Calendar calendar = Calendar.getInstance();
        int weekNumber = calendar.get(Calendar.WEEK_OF_YEAR);
        return weekNumber;
    }


    public static void printList( String[] listItems) {
        printListItems(listItems);
    }

    private static void printListHeader(String header) {
        System.out.println(header + ":");
        System.out.println();
    }

    private static void printListItems(String[] listItems) {
        for(int i = 0; i < listItems.length; i++) {
            System.out.println("\t[" + (i+1) + "] " + listItems[i]);
        }
    }

}
