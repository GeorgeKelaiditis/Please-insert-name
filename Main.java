package databasePackage;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

final class Main implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private ArrayList<DatabaseTable> myElements = new ArrayList<>();
    private static Scanner input = new Scanner(System.in);

    private Main(final String name) {

        this.name = name;

    }

    private Main(final Main DB) {
        this.name = DB.name;
        this.myElements = DB.myElements;
    }

    public static void main(final String[] args) {

        Main DB;
        ArrayList<String> fileNames = profileNames();
        if (fileNames.size() != 0) {

            System.out.println("Θα θέλατε να χρησιμοποιήσετε " +
                    "μία αποθηκευμένη βάση δεδομένων;");
            System.out.println("1. Ναι");
            System.out.println("2. Όχι");
            int answer = input.nextInt();
            if (answer == 1) {

                String profile = loadProfile(fileNames);
                DB = loadDB(profile);
                for (int i = 0; i < DB.myElements.size(); i++) {

                    DB.myElements.get(i).load();

                }

            } else {

                System.out.println("Πώς θα θέλατε να ονομάσετε " +
                        "την βάση δεδομένων σας;");
                String DBName = input.next();
                DB = new Main(DBName);

            }

        } else {

            System.out.println("Πώς θα θέλατε να ονομάσετε " +
                    "την βάση δεδομένων σας;");
            String DBName = input.next();
            DB = new Main(DBName);

        }

        int choice;
        Menu.printMenu();
        boolean quit = false;
        while (!quit) {

            System.out.print("Επιλογή: ");
            choice = input.nextInt();
            switch (choice) {

                case (1):
                    DB.addTable();
                    break;
                case (2):
                    DB.addField();
                    break;
                case (3):
                    DB.addElement();
                    break;
                case (4):
                    DB.printElements();
                    break;
                case (5):
                    DB.updateElement();
                    break;
                case (6):
                    DB.removeElement();
                    break;
                case (7):
                    Menu.printMenu();
                    break;
                case (8):
                    quit = true;
                    break;
            }

        }

        System.out.println("Θα θέλατε να αποθηκεύσετε την βάση δεδομένων;");
        System.out.println("1. Ναι");
        System.out.println("2. Όχι");
        int answer = input.nextInt();
        if (answer == 1) {

            DB.saveDB(DB);
            System.out.println("Η βάση δεδομένων αποθηκεύτηκε επιτυχώς");

        }
        System.out.println("Το πρόγραμμα τερματίστηκε");

    }
//Gets the saved profile names.
    private static ArrayList<String> profileNames() {

        File folder = new File("../");
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
//Loads a saved profile.
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
//Adds a table.
    private void addTable() {

        System.out.print("Εισάγετε το όνομα του πίνακα\n");
        String name = input.next();

        if (DatabaseTable.checkTableName(name) >= 0) {
            System.out.println("Υπάρχει ήδη πίνακας με αυτό το όνομα\n");
            return;
        }

        myElements.add(new DatabaseTable(name));
        System.out.println("Ο πίνακας δημιουργήθηκε\n");

    }
//Adds a field.
    private void addField() {

        System.out.print("Εισάγετε το όνομα του πίνακα που " +
                "θέλετε να προσθέσετε νέο πεδίο: ");
        String name = input.next();
        int index = DatabaseTable.checkTableName(name);
        if (index == -1) {
            System.out.println("Δεν υπάρχει πίνακας με αυτό το όνομα\n");
            return;
        }
        try {

            if (myElements.get(index).fieldSize() == 0) {

                System.out.println("Το πρώτο πεδίο κάθε πίνακα γίνεται " +
                        "αυτόματα το πρωτεύον κλειδί του");

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
//Adds an element to the selected field.
    private void addElement() {

        System.out.print("Εισάγετε το όνομα του πίνακα που θέλετε να " +
                "προσθέσετε νεα στοιχεία: ");
        String name = input.next();
        int index = DatabaseTable.checkTableName(name);
        if (index == -1) {
            System.out.println("Δεν υπάρχει πίνακας με αυτό το όνομα\n");
            return;
        }

        myElements.get(index).addRow();
        System.out.println("Τα στοιχεία προστέθηκαν επιτυχώς\n");

    }
//Prints an element from the selected field.
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

            System.out.print("Εισάγετε το όνομα του πίνακα που " +
                    "θέλετε να εμφανιστεί: ");
            String name = input.next();
            int index = DatabaseTable.checkTableName(name);
            if (index == -1) {
                System.out.println("Δεν υπάρχει πίνακας με αυτό το όνομα\n");
                return;
            }

            myElements.get(index).printElements();

        } else {

            System.out.println("Λάθος επιλογή");

        }

    }
//Updates an element from the selected field.
    private void updateElement() {

        System.out.print("Εισάγετε το όνομα του πίνακα που θέλετε " +
                "να αλλάξετε κάποια σειρά: ");
        String name = input.next();
        int index = DatabaseTable.checkTableName(name);
        if (index == -1) {
            System.out.println("Δεν υπάρχει πίνακας με αυτό το όνομα\n");
            return;
        }

        System.out.print("Εισάγετε το στοιχείο που κατέχει το πρωτεύον " +
                "κλειδί σε εκείνη τη σειρά: ");
        String element = input.next();
        int index2 = myElements.get(index).checkElement(element);

        if (index2 == -1) {
            System.out.println("Το πρωτεύον κλειδί δεν περιέχει " +
                    "αυτό το στοιχείο\n");
            return;
        }

        myElements.get(index).updateRow(index2);
        System.out.println("Το στοιχείο άλλαξε επιτυχώς\n");

    }
//Removes an element from the selected field.
    private void removeElement() {

        System.out.print("Εισάγετε το όνομα του πίνακα που θέλετε " +
                "να αφαιρέσετε κάποια σειρά: ");
        String name = input.next();
        int index = DatabaseTable.checkTableName(name);
        if (index == -1) {
            System.out.println("Δεν υπάρχει πίνακας με αυτό το όνομα\n");
            return;
        }

        System.out.print("Εισάγετε το στοιχείο που κατέχει το πρωτεύον " +
                "κλειδί σε εκείνη τη σειρά: ");
        String element = input.next();
        int index2 = myElements.get(index).checkElement(element);

        if (index2 == -1) {
            System.out.println("Το πρωτεύον κλειδί δεν περιέχει " +
                    "αυτό το στοιχείο\n");
            return;
        }

        myElements.get(index).removeRow(index2);
        System.out.println("Η σειρά διαγράφθηκε επιτυχώς\n");

    }
//Gets the type of a new field.
    private static String getType(final int type) {

        if (type == 1) {

            return "String";

        } else if (type == 2) {

            return "int";

        } else if (type == 3) {

            return "float";

        } else if (type == 4) {

            return "double";

        } else if (type == 5) {

            return "long";

        } else {

            return null;

        }
    }
//Saves the database.
    private void saveDB(final Main DB) {

        if (!name.endsWith("_")) {
            name += "_";
        }
        try (ObjectOutputStream locFile = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream
                                ("../" + this.name)))) {
            locFile.writeObject(DB);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
//Loads the database.
    private static Main loadDB(String name) {


        try (ObjectInputStream locFile = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream
                                ("../" + name)))) {

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
