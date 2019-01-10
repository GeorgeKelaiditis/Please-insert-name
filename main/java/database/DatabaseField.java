/**
 * Info about this package doing something for package-info.java file.
 */
package database;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * The DatabaseField simulates database field.
 *
 * @author Please-Insert-Name
 * @version final
 */
class DatabaseField implements Serializable {

    private static final long serialVersionUID = -2321449162590361108L;
    /**
     * Stores the elements that the field has.
     */
    private LinkedList<String> elements = new LinkedList<>();

    /**Gets the elements of the field.
     *
     * Elements getter
     *
     * @return LinkedList<String>
     */
    LinkedList<String> getElements() {
        return elements;
    }

    /**Adds an element to the field.
     *
     * Adds the parameter to the field elements
     *
     * @param element to add
     */
    void addElement(final String element) {

        elements.add(element);

    }

    /**Prints an element of the field.
     *
     * Receives the index of an element of the elements list
     * and prints it
     *
     * @param index of the element to be printed
     */
    void printElements(final int index) {
        System.out.printf("%12s", elements.get(index) + "\t");

    }

    /**Gets the size of the field.
     *
     * Calculates and returns the size of the elements list
     *
     * @return size of elements field
     */
    int getSize() {

        return elements.size();

    }

    /**Updates a specific element.
     *
     * Removes the element at index of the elements list
     * and then adds in its place the new element
     *
     * @param index of the element to be updated
     * @param element to be added
     */
    void updateElement(final int index, final String element) {

        elements.remove(index);
        elements.add(index, element);

    }

    /**Removes a specific element.
     *
     * Removes the element from the elements list
     * that is located at the index given
     *
     * @param index of the element at the elements list
     */
    void removeElement(final int index) {

        elements.remove(index);

    }

    /**Checks if an element exists in the elements list.
     *
     * Using the indexOf() method returns the index of the element if found
     * otherwise returns a negative integer
     *
     * @param element to be searched
     * @return index of element or negative
     */
    int checkElement(final String element) {

        return elements.indexOf(element);

    }
}
