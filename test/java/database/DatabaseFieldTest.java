package database;

import database.DatabaseField;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

class DatabaseFieldTest {

    private DatabaseField field;

    @BeforeEach
    void setUp() {
        field = new DatabaseField();
    }

    @Test
    void addElement() {
        field.addElement("Test");
        String[] expected = {"Test"};
        Assert.assertTrue(Arrays.equals(expected, field.getElements().toArray()));
    }

    @Test
    void printElements() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        field.addElement("Test");
        field.printElements(0);
        Assert.assertEquals("       Test\t", outContent.toString());
    }

    @Test
    void getSize() {
        int size = field.getSize();
        Assert.assertEquals(0, size);
    }

    @Test
    void updateElement() {
        field.addElement("Test");
        field.updateElement(0, "Tested");
        String[] expected = {"Tested"};
        Assert.assertTrue(Arrays.equals(expected, field.getElements().toArray()));
    }

    @Test
    void removeElement() {
        field.addElement("Test");
        field.removeElement(0);
        String[] expected = {};
        Assert.assertTrue(Arrays.equals(expected, field.getElements().toArray()));
    }

    @Test
    void checkElement_True() {
        field.addElement("Test");
        Assert.assertEquals(0, field.checkElement("Test"));
    }

    @Test
    void checkElement_False() {
        field.addElement("Test");
        Assert.assertTrue(field.checkElement("Tested") < 0);
    }
}