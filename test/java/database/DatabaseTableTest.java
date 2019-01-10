package database;

import database.DatabaseField;
import database.DatabaseTable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTableTest {

    private DatabaseTable table;

    @BeforeEach
    void setUp() {
        table = new DatabaseTable("TestTable");
    }

    @Test
    void fieldSize() {
        table.addField("field", "int");
        int size = table.fieldSize();
        Assertions.assertEquals(1, size);
    }

    @Test
    void checkFieldName_True() {
        table.addField("field", "int");
        Assertions.assertTrue(table.checkFieldName("field") == 0);
    }

    @Test
    void checkFieldName_False() {
        table.addField("field", "int");
        Assertions.assertTrue(table.checkFieldName("notField") != 0);
    }

    @Test
    void checkTableName_True() {
        Assertions.assertTrue(DatabaseTable.checkTableName("TestTable") == 0);
    }

    @Test
    void checkTableName_False() {
        Assertions.assertTrue(DatabaseTable.checkTableName("notTestTable") < 0);
    }

    @Test
    void addField() {
        table.addField("testField", "int");
        Assertions.assertTrue(table.getFields().get(0) instanceof DatabaseField);
    }

    @Test
    void checkElement_True() {
        table.addField("testName", "int");
        table.addElement("Test", 0);
        assertEquals(0, table.checkElement("Test"));
    }

    @Test
    void checkElement_False() {
        table.addField("testName", "int");
        table.addElement("Test", 0);
        assertTrue(table.checkElement("notTest") < 0);
    }

    @Test
    void removeRow() {
        table.addField("testName", "int");
        table.addElement("Test", 0);
        table.addElement("Tested", 0);
        table.removeRow(1);
        assertTrue(table.checkElement("Tested") < 0);
    }
}