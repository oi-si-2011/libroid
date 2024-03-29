package libroid.gui;

import libroid.model.Book;
import libroid.model.Model;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit testy, které ověřují funkčnost metod ve třídě LibraryTableModel.
 */
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
        Model m = new Model();
        m.addBook(new Book().setName("Vlakna hypercasu").setAuthor("R. Susta"));
        m.addBook(new Book().setName("Kryptonomikon").setAuthor("N. Stephenson"));
        m.addBook(new Book().setName("Hordubal").setAuthor("K. Capek"));
        testLibraryLibraryTableModel = new LibraryTableModel(m);
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
        int expResult = 3;
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
