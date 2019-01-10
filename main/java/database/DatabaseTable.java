package database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The DatabaseField simulates database table.
 *
 * @author Please-Insert-Name
 * @version final
 */
class DatabaseTable implements Serializable {

    private static final long serialVersionUID = -7133407087056243435L;
    /**
     * Stores the type of each field.
     */
    private ArrayList<String> types = new ArrayList<>();
    /**
     * Stores the name of each field.
     */
    private ArrayList<String> fieldNames = new ArrayList<>();
    /**
     * Stores each field.
     */
    private ArrayList<DatabaseField> fields = new ArrayList<>();
    /**
     * Stores the name of each table.
     */
    private static ArrayList<String> tableNames = new ArrayList<>();
    /**
     * Stores the name of the table.
     */
    private String name;
    /**
     * Used for input.
     */
    private static Scanner input = new Scanner(System.in, "UTF-8");

    /**
     * Constructor.
     *
     * @param name1 name of the table
     */
    DatabaseTable(final String name1) {

        tableNames.add(name1);
        this.name = name1;

    }

    /**Gets the fields of the table.
     *
     * Fields getter
     *
     * @return ArrayList<DatabaseField>
     */
    public ArrayList<DatabaseField> getFields() {
        return fields;
    }

    /**Gets the amount of fields of the table.
     *
     * Finds how many fields the table has
     * by using the size() method
     *
     * @return amount of fields
     */
    int fieldSize() {

        return fields.size();

    }

    /**Gets the index of the field with the specific name.
     *
     * Finds the index of the field by using the parameter
     * and the method indexOf() if it exists returns positive or 0
     * else negative
     *
     * @param name1 name of the field
     * @return index of the field
     */
    int checkFieldName(final String name1) {

        return fieldNames.indexOf(name1);

    }

    /**Checks if the table with that name exists.
     *
     * Finds the index of the table by using the parameter
     * and the method indexOf() if it exists returns positive or 0
     * else negative
     *
     * @param name1 name of the table
     * @return index of the table
     */
    static int checkTableName(final String name1) {

        return tableNames.indexOf(name1);

    }

    /**Adds a new field to the table.
     *
     * Adds a new DatabaseField on fields and adds the type of it
     * at the list types and its name at fieldNames list
     *
     * @param name1 name of the field
     * @param type type of the field
     */
    void addField(final String name1, final String type) {

        fields.add(new DatabaseField());
        types.add(type);
        fieldNames.add(name1);
        if (fields.get(0).getSize() > 0) {

            for (int i = 0; i < fields.get(0).getSize(); i++) {

                fields.get(fields.size() - 1).addElement(null);

            }

        }

    }

    /**Adds a row to the table.
     *
     * Gets an input by the user for each field and checks
     * if it's valid through readInfo method and than adds
     * it to the field through the addElement method
     *
     */
    void addRow() {

        for (int i = 0; i < fields.size(); i++) {

            System.out.print("Εισάγετε το νέο στοιχείο για το πεδίο "
                    + fieldNames.get(i) + ": ");
            String element = input.next();
            boolean flag = readInfo(i, element);
            if (!flag) {

                System.out.println("Προσπαθήστε ξανά");
                i--;
                continue;

            }

            if (i == 0 && fields.get(i).checkElement(element) != -1) {

                System.out.println("Το στοιχείο αυτό ήδη υπάρχει στο "
                        + "πρωτεύον κλειδί");
                i--;
                continue;

            }

            addElement(element, i);

        }

    }

    /**Adds an element to a field of the table.
     *
     * Adds element to the field at the index index
     * of the fields list using the addElement method
     * of the DatabaseField class
     *
     * @param element new element
     * @param index of the field
     */
    void addElement(final String element, final int index) {

        fields.get(index).addElement(element);

    }

    /**Prints each field of the table.
     *
     * Prints all the fields of the table through a loop and by using
     * the printElement method of the DatabaseField class
     *
     */
    void printElements() {

        try {
            System.out.println("Πίνακας " + name);
            int size = fields.get(0).getSize();
            System.out.print("   ");
            for (String fieldName : fieldNames) {

                System.out.printf("%12s", fieldName + "\t");

            }

            System.out.println();

            for (int i = 0; i < size; i++) {

                System.out.print((i + 1) + ". ");
                for (DatabaseField field : fields) {

                    field.printElements(i);

                }

                System.out.println();

            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Ο πίνακας δεν περιέχει στοιχεία");
        }

    }

    /**Checks if there is a specific element at the primary key field.
     *
     * Finds the index of the element by using the parameter
     * and the method checkElement of the DatabaseField class
     *
     * @param element value of the element
     * @return index of the element
     */
    int checkElement(final String element) {

        return fields.get(0).checkElement(element);

    }

    /**Updates a row of the table.
     *
     * Gets the new element of each field from the user and tests if it's valid
     * and calls the updateElement method from the DatabaseField class
     * to update the element
     *
     * @param index index of the row to be updated
     */
    void updateRow(final int index) {

        for (int i = 0; i < fields.size(); i++) {

            System.out.print("Εισάγετε το νέο στοιχείο για το πεδίο "
                    + fieldNames.get(i) + ": ");
            String element = input.next();
            boolean flag = readInfo(i, element);
            if (!flag) {

                i--;
                continue;

            }
            fields.get(i).updateElement(index, element);

        }

    }

    /**Removes a row of the table.
     *
     * Gets the index of the row to be removed and
     * calls the removeElement
     * to remove that index from every field
     *
     * @param index index of the row to be removed
     */
    void removeRow(final int index) {

        for (int i = 0; i < fields.size(); i++) {

            removeElement(i, index);

        }

    }

    /**Removes an element of a field.
     *
     * Calls the removeElement method of DatabaseField class
     * to remove the element at the index of the index1 field
     *
     * @param index1 index of the field
     * @param index index of the element
     */
    private void removeElement(final int index1, final int index) {

        fields.get(index1).removeElement(index);

    }

    /**Fills the static list tableNames when loaded.
     *
     * Adds the table name to the tableNames list
     *
     */
    void load() {

        tableNames.add(this.name);

    }

    /**Checks if the user inserts a valid data type.
     *
     * Checks if the data input from the user matches the data type
     * of the field by using Exceptions
     *
     * @param index index of the type of the field
     * @param a the input of the user
     * @return if the input was correct or not
     */
    private boolean readInfo(final int index, final String a) {

        String type = types.get(index);
        switch (type) {

            case ("String"):

                return true;

            case ("int"):

                try {

                    Integer.parseInt(a);
                    return true;

                } catch (NumberFormatException e) {

                    System.out.println("Εισάχθηκε λάθος τύπος δεδομένου\n");
                    return false;

                }

            case ("float"):

                try {

                    Float.parseFloat(a);
                    return true;

                } catch (NumberFormatException e) {

                    System.out.println("Εισάχθηκε λάθος τύπος δεδομένου\n");
                    return false;

                }

            case ("double"):
                try {

                    Double.parseDouble(a);
                    return true;

                } catch (NumberFormatException e) {

                    System.out.println("Εισάχθηκε λάθος τύπος δεδομένου\n");
                    return false;

                }

            case ("long"):
                try {

                    Long.parseLong(a);
                    return true;

                } catch (NumberFormatException e) {

                    System.out.println("Εισάχθηκε λάθος τύπος δεδομένου\n");
                    return false;

                }

            default:
                return false;

        }

    }
}
