package libroid.gui;

import libroid.model.Book;
import java.util.List;
import java.util.ArrayList;
import libroid.model.BookList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LibraryTableModelTest {

    LibraryTableModel testLibraryLibraryTableModel;

    public LibraryTableModelTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {

        BookList testList = new BookList("testList");
        List<Book> testBookList = new ArrayList<Book>();

        testBookList.add(new Book("Vlakna hypercasu", "R. Susta"));
        testBookList.add(new Book("Kryptonomikon", "N. Stephenson"));
        testBookList.add(new Book("Velke U", "Neal Stephenson"));
        testBookList.add(new Book("Hordubal", "K. Capek"));
        testBookList.add(new Book("A", "1"));
        testBookList.add(new Book("B", "2"));

        testList.setBooks(testBookList);
        testLibraryLibraryTableModel = new LibraryTableModel(testList);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getRowCount method, of class LibraryTableModel.
     */
    @Test
    public void testGetRowCount() {
        System.out.println("getRowCount");
        int expResult = 6;
        int result = testLibraryLibraryTableModel.getRowCount();
        assertEquals(expResult, result);

    }

    /**
     * Test of getColumnCount method, of class LibraryTableModel.
     */
    @Test
    public void testGetColumnCount() {
        System.out.println("getColumnCount");
        int expResult = 2;
        int result = testLibraryLibraryTableModel.getColumnCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of getValueAt method, of class LibraryTableModel.
     */
    @Test
    public void testGetValueAt() {
        System.out.println("getValueAt");
        int rowIndex = 0;
        int columnIndex = 0;

        Object expResult = "Vlakna hypercasu";
        Object result = testLibraryLibraryTableModel.getValueAt(rowIndex, columnIndex);
        assertEquals(expResult, result);


    }

    /**
     * Test of getColumnName method, of class LibraryTableModel.
     */
    @Test
    public void testGetColumnName() {
        System.out.println("getColumnName");
        int col = 0;
        LibraryTableModel instance = null;
        String expResult = "Name";
        String result = testLibraryLibraryTableModel.getColumnName(col);
        assertEquals(expResult, result);

    }
}
