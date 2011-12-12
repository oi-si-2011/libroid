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
 * Testy LibraryTable.
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
        m.addBook(new Book("Hordubal", "K. Capek"));
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
}
