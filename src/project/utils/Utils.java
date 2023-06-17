package project.utils;

public class Utils {
    public static void printList( String[] listItems, String header) {
        printListHeader(header);
        printListItems(listItems);
    }

    public static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            }
            else {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e) {
            e.printStackTrace();
        }
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
