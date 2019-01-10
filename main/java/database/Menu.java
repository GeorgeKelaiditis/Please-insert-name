package database;

/**
 * Contains all the menus.
 *
 * @author Please-Insert-Name
 * @version final
 */
final class Menu {

    /**
     * Empty constructor nice checkstyle.
     */
    private Menu() {

    }

    /**
     * Prints the main menu.
     */
    static void printMenu() {

        System.out.println("Μενού επιλογών:");
        System.out.println("1. Προσθήκη καινούριου πίνακα");
        System.out.println("2. Προσθήκη πεδίου σε πίνακα");
        System.out.println("3. Εισαγωγή δεδομένων");
        System.out.println("4. Παρουσίαση δεδομένων");
        System.out.println("5. Αλλαγή δεδομένων");
        System.out.println("6. Διαγραφη δεδομένων");
        System.out.println("7. Επανεμφάνιση μενού επιλογών");
        System.out.println("8. Τερματισμός προγράμματος και αποθήκευση");

    }

    /**
     * Prints the type menu.
     */
    static void printType() {

        System.out.println("Μενού επιλογών τύπου δεδομένων:");
        System.out.println("1. String");
        System.out.println("2. Integer");
        System.out.println("3. Float");
        System.out.println("4. Double");
        System.out.println("5. Long");
        System.out.print("Επιλογή: ");

    }

}
