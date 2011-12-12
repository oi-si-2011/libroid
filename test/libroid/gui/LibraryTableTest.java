/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package libroid.gui;

import java.util.ArrayList;
import libroid.model.Model;
import java.util.List;

import libroid.model.Book;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Michal
 */
public class LibraryTableTest {

    LibraryTable testLibraryTable;

    public LibraryTableTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        Model m = new Model();

        m.addBook(new Book("Vlakna hypercasu", "R. Susta"));
        m.addBook(new Book("Kryptonomikon", "N. Stephenson"));
        m.addBook(new Book("Velke U", "Neal Stephenson"));
        m.addBook(new Book("Hordubal", "K. Capek"));
        m.addBook(new Book("A", "1"));
        m.addBook(new Book("B", "2"));

        testLibraryTable = new LibraryTable(m);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getSelectedBooks method, of class LibraryTable.
     */
    @Test
    public void testGetSelectedBooks() {
        System.out.println("getSelectedBooks");

        List expResult = new ArrayList();

        List result = testLibraryTable.getSelectedBooks();
        assertEquals(expResult, result);

    }
    /**
     * Test of getSelectedBook method, of class LibraryTable.
     */
}
