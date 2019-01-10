package database;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Simulates a database.
 *
 * @author Please-Insert-Name
 * @version final
 */
final class Main implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Database name.
     */
    private String name;
    /**
     * Stores the tables of the database.
     */
    private ArrayList<DatabaseTable> myElements = new ArrayList<>();
    /**
     * Used for inputs.
     */
    private static Scanner input = new Scanner(System.in, "UTF-8");
    /**
     * Cause of checkstyle.
     */
    private static final int A2 = 2;
    /**
     * Cause of checkstyle.
     */
    private static final int A3 = 3;
    /**
     * Cause of checkstyle.
     */
    private static final int A4 = 4;
    /**
     * Cause of checkstyle.
     */
    private static final int A5 = 5;
    /**
     * Cause of checkstyle.
     */
    private static final int A6 = 6;
    /**
     * Cause of checkstyle.
     */
    private static final int A7 = 7;
    /**
     * Cause of checkstyle.
     */
    private static final int A8 = 8;

    /**
     * Creates Main object from name.
     *
     * @param name1 name of the database
     */
    private Main(final String name1) {

        this.name = name1;

    }

    /**
     * Creates Main object from a Main object.
     *
     * @param dataBase the object of Main
     */
    private Main(final Main dataBase) {
        this.name = dataBase.name;
        this.myElements = dataBase.myElements;
    }

    /**
     * Executes the program.
     *
     * @param args no
     */
    public static void main(final String[] args) {

        Main dataBase;
        ArrayList<String> fileNames = profileNames();
        if (fileNames.size() != 0) {

            System.out.println("Θα θέλατε να χρησιμοποιήσετε "
                    + "μία αποθηκευμένη βάση δεδομένων;");
            System.out.println("1. Ναι");
            System.out.println("2. Όχι");
            int answer = input.nextInt();
            if (answer == 1) {

                String profile = loadProfile(fileNames);
                dataBase = loadDB(profile);
                for (int i = 0; i < dataBase.myElements.size(); i++) {

                    dataBase.myElements.get(i).load();

                }

            } else {

                System.out.println("Πώς θα θέλατε να ονομάσετε "
                        + "την βάση δεδομένων σας;");
                String dataBaseName = input.next();
                dataBase = new Main(dataBaseName);

            }

        } else {

            System.out.println("Πώς θα θέλατε να ονομάσετε "
                    + "την βάση δεδομένων σας;");
            String databaseName = input.next();
            dataBase = new Main(databaseName);

        }

        int choice;
        Menu.printMenu();
        boolean quit = false;
        while (!quit) {

            System.out.print("Επιλογή: ");
            choice = input.nextInt();
            switch (choice) {

                case (1):
                    dataBase.addTable();
                    break;
                case (A2):
                    dataBase.addField();
                    break;
                case (A3):
                    dataBase.addElement();
                    break;
                case (A4):
                    dataBase.printElements();
                    break;
                case (A5):
                    dataBase.updateElement();
                    break;
                case (A6):
                    dataBase.removeElement();
                    break;
                case (A7):
                    Menu.printMenu();
                    break;
                case (A8):
                    quit = true;
                    break;
                default:
                    break;
            }

        }

        System.out.println("Θα θέλατε να αποθηκεύσετε την βάση δεδομένων;");
        System.out.println("1. Ναι");
        System.out.println("2. Όχι");
        int answer = input.nextInt();
        if (answer == 1) {

            dataBase.saveDB(dataBase);
            System.out.println("Η βάση δεδομένων αποθηκεύτηκε επιτυχώς");

        }
        System.out.println("Το πρόγραμμα τερματίστηκε");

    }

    /**
     * Gets the saved profile names.
     *
     * Gets all the files that and with _ from the path
     * and puts them to an array, then their names are added
     * to a list that is returned
     *
     * @return ArrayList of the files' names
     */
    private static ArrayList<String> profileNames() {

        File folder = new File("./");
        File[] listOfFiles = folder.listFiles();
        ArrayList<String> fileNames = new ArrayList<>();

        if (listOfFiles != null) {
            for (File listOfFile : listOfFiles) {

                if (listOfFile.getName().endsWith("_")) {

                    fileNames.add(listOfFile.getName());

                }

            }
        }

        return fileNames;

    }

    /**
     * User chooses which saved database to use.
     *
     * Prints all the available profiles from fileNames
     * and then the user chooses through a menu which he wants
     * to load. Then this file's name is returned
     *
     * @param fileNames a list of the available profiles
     * @return name of the profile to be loaded
     */
    @org.jetbrains.annotations.Nullable
    private static String loadProfile(final ArrayList<String> fileNames) {

        System.out.println("  Profiles");
        for (int i = 0; i < fileNames.size(); i++) {

            System.out.println((i + 1) + ". " + fileNames.get(i)
                    .substring(0, fileNames.get(i).length() - 1));

        }

        int choice = input.nextInt();

        if (choice <= fileNames.size() && choice > 0) {

            return fileNames.get(choice - 1);

        }

        return null;

    }

    /**
     * Adds a new table to the database.
     *
     * The user inserts a name for the table which is checked if it
     * already exists by the checkTableName method of DatabaseTable class.
     * If it exists a message tells him so and sends him to the main menu,
     * if not then the table is created
     *
     */
    private void addTable() {

        System.out.print("Εισάγετε το όνομα του πίνακα\n");
        String name1 = input.next();

        if (DatabaseTable.checkTableName(name1) >= 0) {
            System.out.println("Υπάρχει ήδη πίνακας με αυτό το όνομα\n");
            return;
        }

        myElements.add(new DatabaseTable(name1));
        System.out.println("Ο πίνακας δημιουργήθηκε\n");

    }

    /**
     * Adds a new field to an existing table of the database.
     *
     * The user inserts the name for the table that he wants to add a new
     * field if it already exists by the checkTableName method of
     * DatabaseTable class. If it does not exists a message tells him so and
     * sends him to the main menu, if it exists then he is asked to insert the
     * name of the field that he wants to create the same check is being
     * performed by the checkFieldName of the DatabaseField class and if
     * valid he then inserts the type of data he wants to store in that field
     * through a menu
     *
     */
    private void addField() {

        System.out.print("Εισάγετε το όνομα του πίνακα που "
                + "θέλετε να προσθέσετε νέο πεδίο: ");
        String name1 = input.next();
        int index = DatabaseTable.checkTableName(name1);
        if (index == -1) {
            System.out.println("Δεν υπάρχει πίνακας με αυτό το όνομα\n");
            return;
        }
        try {

            if (myElements.get(index).fieldSize() == 0) {

                System.out.println("Το πρώτο πεδίο κάθε πίνακα γίνεται "
                        + "αυτόματα το πρωτεύον κλειδί του");

            }

        } catch (IndexOutOfBoundsException e) {
            System.out.println("LOL");
        }
        System.out.print("Εισάγετε το όνομα του νέου πεδίου: ");
        String field = input.next();
        int index1 = myElements.get(index).checkFieldName(field);
        if (index1 != -1) {
            System.out.println("Υπάρχει ήδη πεδίο με αυτό το όνομα\n");
            return;
        }

        Menu.printType();
        int type = input.nextInt();
        String type1 = getType(type);
        if (type1 == null) {

            System.out.println("Λάθος τύπος\n");
            return;

        }
        myElements.get(index).addField(field, type1);
        System.out.println("Το πεδίο δημιουργήθηκε\n");

    }


    /**
     * Adds a row to an existing table of the database.
     *
     * The user inserts a name for the table which is checked if it
     * already exists by the checkTableName method of DatabaseTable class.
     * If it exists then addRow method is called,
     * if not then a message tells him so and sends him to the main menu
     *
     */
    private void addElement() {

        System.out.print("Εισάγετε το όνομα του πίνακα που θέλετε να "
                + "προσθέσετε νεα στοιχεία: ");
        String name1 = input.next();
        int index = DatabaseTable.checkTableName(name1);
        if (index == -1) {
            System.out.println("Δεν υπάρχει πίνακας με αυτό το όνομα\n");
            return;
        }

        myElements.get(index).addRow();
        System.out.println("Τα στοιχεία προστέθηκαν επιτυχώς\n");

    }

    /**
     * Prints all tables or a specific one.
     * The user is asked to insert 1 or 2:
     * 1) All the tables are printed through the printElements method of the
     * DatabaseTable method
     * 2) The user inserts a name for the table which is checked if it
     * already exists by the checkTableName method of DatabaseTable class.
     * If it does not exist a message tells him so and sends him to the
     * main menu, if not then the table is printed
     *
     */
    private void printElements() {

        System.out.println("  Επιλογές");
        System.out.println("1.Εμφάνιση όλων των πινάκων");
        System.out.println("2.Εμφάνιση ενός συγκεκριμένου πίνακα");
        int choice = input.nextInt();

        if (choice == 1) {

            for (DatabaseTable myElement : myElements) {

                myElement.printElements();

            }

        } else if (choice == 2) {

            System.out.print("Εισάγετε το όνομα του πίνακα που "
                    + "θέλετε να εμφανιστεί: ");
            String name1 = input.next();
            int index = DatabaseTable.checkTableName(name1);
            if (index == -1) {
                System.out.println("Δεν υπάρχει πίνακας με αυτό το όνομα\n");
                return;
            }

            myElements.get(index).printElements();

        } else {

            System.out.println("Λάθος επιλογή");

        }

    }

    //Updates a row from the selected table.
    /**
     * Updates a row from the selected table of the database.
     *
     * The user inserts a name for the table which is checked if it
     * already exists by the checkTableName method of DatabaseTable class.
     * If it does not exist then a message tells him so and sends him to
     * the main menu, if not then he is asked to insert the value
     * that the primary key possesses in that row. The inserted value is
     * checked by the checkElement method and if it does not exist a message
     * tells him so and sends him to the main menu, if it exists than the row
     * is updated by the updateRow method
     *
     */
    private void updateElement() {

        System.out.print("Εισάγετε το όνομα του πίνακα που θέλετε "
                + "να αλλάξετε κάποια σειρά: ");
        String name1 = input.next();
        int index = DatabaseTable.checkTableName(name1);
        if (index == -1) {
            System.out.println("Δεν υπάρχει πίνακας με αυτό το όνομα\n");
            return;
        }

        System.out.print("Εισάγετε το στοιχείο που κατέχει το πρωτεύον "
                + "κλειδί σε εκείνη τη σειρά: ");
        String element = input.next();
        int index2 = myElements.get(index).checkElement(element);

        if (index2 == -1) {
            System.out.println("Το πρωτεύον κλειδί δεν περιέχει "
                    + "αυτό το στοιχείο\n");
            return;
        }

        myElements.get(index).updateRow(index2);
        System.out.println("Το στοιχείο άλλαξε επιτυχώς\n");

    }

    /**
     * Removes a row from the selected table of the database.
     *
     * The user inserts a name for the table which is checked if it
     * already exists by the checkTableName method of DatabaseTable class.
     * If it does not exist then a message tells him so and sends him to
     * the main menu, if not then he is asked to insert the value
     * that the primary key possesses in that row. The inserted value is
     * checked by the checkElement method and if it does not exist a message
     * tells him so and sends him to the main menu, if it exists than the row
     * is removed by the removeRow method
     *
     */
    private void removeElement() {

        System.out.print("Εισάγετε το όνομα του πίνακα που θέλετε "
                + "να αφαιρέσετε κάποια σειρά: ");
        String name1 = input.next();
        int index = DatabaseTable.checkTableName(name1);
        if (index == -1) {
            System.out.println("Δεν υπάρχει πίνακας με αυτό το όνομα\n");
            return;
        }

        System.out.print("Εισάγετε το στοιχείο που κατέχει το πρωτεύον "
                + "κλειδί σε εκείνη τη σειρά: ");
        String element = input.next();
        int index2 = myElements.get(index).checkElement(element);

        if (index2 == -1) {
            System.out.println("Το πρωτεύον κλειδί δεν περιέχει "
                    + "αυτό το στοιχείο\n");
            return;
        }

        myElements.get(index).removeRow(index2);
        System.out.println("Η σειρά διαγράφθηκε επιτυχώς\n");

    }

    /**
     * Gets the data type of the new field.
     *
     * Switches the parameter to the correct type of String
     *
     * @param type type of the field
     * @return the type in String if the type parameter is valid
     */
    private static String getType(final int type) {

        if (type == 1) {

            return "String";

        } else if (type == 2) {

            return "int";

        } else if (type == A3) {

            return "float";

        } else if (type == A4) {

            return "double";

        } else if (type == A5) {

            return "long";

        } else {

            return null;

        }
    }

    /**
     *
     * Saves the database at an external storage.
     *
     * Changes the name if needed and then saves the database
     * at the appropriate file
     *
     * @param dataBase the object to be saved
     */
    private void saveDB(final Main dataBase) {

        if (!name.endsWith("_")) {
            name += "_";
        }
        try (ObjectOutputStream locFile = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(this.name)))) {
            locFile.writeObject(dataBase);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Problem");
        }

    }

    /**
     *
     * Loads a saved database.
     *
     * Loads the profile which is located at the name
     * parameter
     *
     * @param name name of the database
     * @return the saved database as a Main object
     */
    private static Main loadDB(final String name) {


        try (ObjectInputStream locFile = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(name)))) {

            try {
                return new Main((Main) locFile.readObject());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Main("a");

    }

}
