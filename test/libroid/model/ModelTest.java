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
        m.addBook(new Book("Hordubal", "K. Capek"));
        assertEquals(new Book("Kryptonomikon", "N. Stephenson"), m.getBook(1));
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
        m.addBook(new Book("Hordubal", "K. Capek"));
        assertEquals(3, m.bookCount());
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
        assertEquals(new Book("Velke U", "Neal Stephenson"), m.getBook(2));
    }

    /**
     * 
     */
    @Test
    public void testGetBook2() {
        Model m = new Model();
        m.addBook(new Book("Vlakna hypercasu", "R. Susta"));
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
        sampleList.add(new Book("Kryptonomikon", "N. Stephenson"));
        sampleList.add(new Book("Hordubal", "K. Capek"));

        Model m = new Model();
        m.addBook(new Book("Vlakna hypercasu", "R. Susta"));
        m.addBook(new Book("Kryptonomikon", "N. Stephenson"));
        m.addBook(new Book("Velke U", "Neal Stephenson"));
        m.addBook(new Book("Hordubal", "K. Capek"));
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
    /* XXX TODO: zkontrolovat (tedy otestovat kontrolu) treba, ze do booklistu
     * se neda pridat kniha, ktera neni v modelu, apod. - proste
     * kontrola integrity
     */
}
