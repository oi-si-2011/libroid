package libroid.gui;

import libroid.model.Book;
import java.util.List;
import java.util.ArrayList;
import libroid.model.BookList;
import libroid.model.Model;
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
public class ListsInventoryModelTest {

    /**
     * 
     */
    public ListsInventoryModelTest() {
    }

    /**
     * 
     * @throws Exception
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    /**
     * 
     * @throws Exception
     */
    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * 
     */
    @Before
    public void setUp() {
    }

    /**
     * 
     */
    @After
    public void tearDown() {
    }

    /**
     * Test of getSize method, of class ListsInventoryModel.
     */
    @Test
    public void testGetSize() {
        System.out.println("getSize");
        ListsInventoryModel instance = new ListsInventoryModel(null);
        int expResult = 1;

        try {
            int result = instance.getSize();
            assertEquals(expResult, result);
        } catch (NullPointerException exc) {
            System.out.println("NullpointerException");
        }
    }

    /**
     * 
     */
    @Test
    public void testGetSize2() {
        System.out.println("getSize2");
        Model m = new Model();
        ListsInventoryModel instance = new ListsInventoryModel(m);
        int expResult = 1;
        int result = instance.getSize();
        assertEquals(expResult, result);
    }

    /**
     * 
     */
    @Test
    public void testGetSize3() {
        System.out.println("getSize3");
        BookList testList = new BookList("testList");
        List<Book> testBookList = new ArrayList<Book>();

        testBookList.add(new Book("Vlakna hypercasu", "R. Susta"));
        testBookList.add(new Book("Kryptonomikon", "N. Stephenson"));
        testBookList.add(new Book("Velke U", "Neal Stephenson"));
        testBookList.add(new Book("Hordubal", "K. Capek"));
        testBookList.add(new Book("A", "1"));
        testBookList.add(new Book("B", "2"));

        testList.setBooks(testBookList);
        Model m = new Model();
        m.addBookList(testList);


        ListsInventoryModel instance = new ListsInventoryModel(m);

        int expResult = 2;
        int result = instance.getSize();

        assertEquals(expResult, result);

    }

    /**
     * 
     */
    @Test
    public void testGetSize4() {
        System.out.println("getSize4");

        BookList testList1 = new BookList("testList1");
        BookList testList2 = new BookList("testList2");
        BookList testList3 = new BookList("testList3");

        List<Book> testBookList1 = new ArrayList<Book>();
        List<Book> testBookList2 = new ArrayList<Book>();
        List<Book> testBookList3 = new ArrayList<Book>();

        testBookList1.add(new Book("Vlakna hypercasu", "R. Susta"));
        testBookList1.add(new Book("Kryptonomikon", "N. Stephenson"));
        testBookList2.add(new Book("Velke U", "Neal Stephenson"));
        testBookList2.add(new Book("Hordubal", "K. Capek"));
        testBookList3.add(new Book("A", "1"));
        testBookList3.add(new Book("B", "2"));

        testList1.setBooks(testBookList1);
        testList2.setBooks(testBookList2);
        testList3.setBooks(testBookList3);

        Model m = new Model();
        m.addBookList(testList1);
        m.addBookList(testList2);
        m.addBookList(testList3);

        ListsInventoryModel instance = new ListsInventoryModel(m);

        int expResult = 4;
        int result = instance.getSize();

        assertEquals(expResult, result);

    }

    /**
     * 
     */
    @Test
    public void testGetSize5() {
        System.out.println("getSize2");

        BookList testList1 = new BookList("testList1");
        BookList testList2 = new BookList("testList2");
        BookList testList3 = new BookList("testList3");
        BookList testList4 = new BookList("testList4");

        List<Book> testBookList1 = new ArrayList<Book>();
        List<Book> testBookList2 = new ArrayList<Book>();
        List<Book> testBookList3 = new ArrayList<Book>();

        testBookList1.add(new Book("Vlakna hypercasu", "R. Susta"));
        testBookList1.add(new Book("Kryptonomikon", "N. Stephenson"));
        testBookList2.add(new Book("Velke U", "Neal Stephenson"));
        testBookList2.add(new Book("Hordubal", "K. Capek"));
        testBookList3.add(new Book("A", "1"));
        testBookList3.add(new Book("B", "2"));

        testList1.setBooks(testBookList1);
        testList2.setBooks(testBookList2);
        testList3.setBooks(testBookList3);

        Model m = new Model();
        m.addBookList(testList1);
        m.addBookList(testList2);
        m.addBookList(testList3);
        m.addBookList(testList4);

        ListsInventoryModel instance = new ListsInventoryModel(m);

        int expResult = 5;
        int result = instance.getSize();

        assertEquals(expResult, result);

    }

    /**
     * Test of getElementAt method, of class ListsInventoryModel.
     */
    @Test
    public void testGetElementAt() {
        System.out.println("getElementAt");

        BookList testList1 = new BookList("testList1");
        BookList testList2 = new BookList("testList2");
        BookList testList3 = new BookList("testList3");

        List<Book> testBookList1 = new ArrayList<Book>();
        List<Book> testBookList2 = new ArrayList<Book>();
        List<Book> testBookList3 = new ArrayList<Book>();

        testBookList1.add(new Book("Vlakna hypercasu", "R. Susta"));
        testBookList1.add(new Book("Kryptonomikon", "N. Stephenson"));
        testBookList2.add(new Book("Velke U", "Neal Stephenson"));
        testBookList2.add(new Book("Hordubal", "K. Capek"));
        testBookList3.add(new Book("A", "1"));
        testBookList3.add(new Book("B", "2"));

        testList1.setBooks(testBookList1);
        testList2.setBooks(testBookList2);
        testList3.setBooks(testBookList3);

        Model m = new Model();
        m.addBookList(testList1);
        m.addBookList(testList2);
        m.addBookList(testList3);

        ListsInventoryModel instance = new ListsInventoryModel(m);

        String expResult = testList2.getName();
        Object result = instance.getElementAt(2);

        assertTrue(expResult.equals(result));

    }
}
