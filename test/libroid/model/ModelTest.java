package libroid.model;

import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ModelTest {

    public ModelTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addBook method, of class Model.
     */
    @Test
    public void testAddBook() {
        System.out.println("addBook");
        Book book = new Book("Vlakna hypercasu", "R. Susta");
        Model model = new Model();
        model.addBook(book);
        assertEquals(book, model.getBook(0));
    }

    @Test
    public void testAddBook2() {
        System.out.println("addBook");
        Model m = new Model();
        m.addBook(new Book("Vlakna hypercasu", "R. Susta"));
        m.addBook(new Book("Kryptonomikon", "N. Stephenson"));
        m.addBook(new Book("Velke U", "Neal Stephenson"));
        m.addBook(new Book("Hordubal", "K. Capek"));
        m.addBook(new Book("A", "1"));
        m.addBook(new Book("B", "2"));
        assertEquals(new Book("A", "1"), m.getBook(4));
    }

    /**
     * Test of bookCount method, of class Model.
     */
    @Test
    public void testBookCount() {
        System.out.println("bookCount");
        Model m = new Model();
        m.addBook(new Book("Vlakna hypercasu", "R. Susta"));
        m.addBook(new Book("Kryptonomikon", "N. Stephenson"));
        m.addBook(new Book("Velke U", "Neal Stephenson"));
        m.addBook(new Book("Hordubal", "K. Capek"));
        m.addBook(new Book("A", "1"));
        m.addBook(new Book("B", "2"));
        assertEquals(6, m.bookCount());
    }

    /**
     * Test of getBook method, of class Model.
     */
    @Test
    public void testGetBook() {
        Model m = new Model();
        m.addBook(new Book("Vlakna hypercasu", "R. Susta"));
        m.addBook(new Book("Kryptonomikon", "N. Stephenson"));
        m.addBook(new Book("Velke U", "Neal Stephenson"));
        m.addBook(new Book("Hordubal", "K. Capek"));
        m.addBook(new Book("A", "1"));
        m.addBook(new Book("B", "2"));
        assertEquals(new Book("Velke U", "Neal Stephenson"), m.getBook(2));
    }

    @Test
    public void testGetBook2() {
        Model m = new Model();
        m.addBook(new Book("Vlakna hypercasu", "R. Susta"));
        m.addBook(new Book("Kryptonomikon", "N. Stephenson"));
        m.addBook(new Book("Velke U", "Neal Stephenson"));
        m.addBook(new Book("Hordubal", "K. Capek"));
        m.addBook(new Book("A", "1"));
        m.addBook(new Book("B", "2"));
        try {
            Book book = m.getBook(234);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("chyba podchycena");
        }
    }

    /**
     * Test of removeBooks method, of class Model.
     */
    @Test
    public void testRemoveBooks() {
        System.out.println("removeBooks");
        List<Book> booksToRemove = new LinkedList<Book>();
        List<Book> sampleList = new LinkedList<Book>();
        booksToRemove.add(new Book("Vlakna hypercasu", "R. Susta"));
        booksToRemove.add(new Book("Velke U", "Neal Stephenson"));
        booksToRemove.add(new Book("A", "1"));
        sampleList.add(new Book("Kryptonomikon", "N. Stephenson"));
        sampleList.add(new Book("Hordubal", "K. Capek"));
        sampleList.add(new Book("B", "2"));

        Model m = new Model();
        m.addBook(new Book("Vlakna hypercasu", "R. Susta"));
        m.addBook(new Book("Kryptonomikon", "N. Stephenson"));
        m.addBook(new Book("Velke U", "Neal Stephenson"));
        m.addBook(new Book("Hordubal", "K. Capek"));
        m.addBook(new Book("A", "1"));
        m.addBook(new Book("B", "2"));
        m.removeBooks(booksToRemove);

        assertEquals(sampleList, m.getAllBooks());
    }

    @Test
    public void testRemoveBooks2() {
        System.out.println("removeBooks from empty library");
        List<Book> booksToRemove = new LinkedList<Book>();
        List<Book> sampleList = new LinkedList<Book>();
        Model m = new Model();
        m.removeBooks(booksToRemove);
        assertEquals(sampleList, m.getAllBooks());
    }
}
