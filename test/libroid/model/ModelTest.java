package libroid.model;

import org.junit.rules.ExpectedException;
import org.junit.Rule;
import java.util.ArrayList;
import java.util.Arrays;
import libroid.model.exceptions.ModelIntegrityError;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ModelTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

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
        Book book = new Book().setName("Vlakna hypercasu").setAuthor("R. Susta");
        Model model = new Model();
        model.addBook(book);
        assertEquals(book, model.getBook(0));
    }

    /**
     * Test of bookCount method, of class Model.
     */
    @Test
    public void testBookCount() {
        System.out.println("bookCount");
        Model m = new Model();
        assertEquals(0, m.bookCount());
        m.addBook(new Book().setName("Vlakna hypercasu").setAuthor("R. Susta"));
        assertEquals(1, m.bookCount());
    }

    /**
     * Test of getBook method, of class Model.
     */
    @Test
    public void testGetBook() {
        Model m = new Model();
        Book b0 = m.addBook(new Book().setName("Vlakna hypercasu").setAuthor("R. Susta"));
        assertEquals(b0, m.getBook(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetBookIndexOutOfBounds() {
        Model m = new Model();
        Book book = m.getBook(234);
    }

    /**
     * Test of removeBooks method, of class Model.
     */
    @Test
    public void testRemoveBooks() {
        System.out.println("removeBooks");
        Model m = new Model();
        Book b0 = m.addBook(new Book().setName("Vlakna hypercasu").setAuthor("R. Susta"));
        Book b1 = m.addBook(new Book().setName("Kryptonomikon").setAuthor("N. Stephenson"));
        Book b2 = m.addBook(new Book().setName("Velke U").setAuthor("Neal Stephenson"));
        Book b3 = m.addBook(new Book().setName("Hordubal").setAuthor("K. Capek"));
        m.removeBooks(Arrays.asList(b0, b2));
        assertEquals(Arrays.asList(b1, b3), m.getAllBooks());
    }

    @Test
    public void testRemoveZeroBooksFromEmptyLibrary() {
        System.out.println("removeBooks from empty library");
        Model m = new Model();
        m.removeBooks(new ArrayList<Book>());
        assertEquals(0, m.getAllBooks().size());
    }

    @Test
    public void testBookListAddedToModelHasModelSet() {
        Model m = new Model();
        BookList bookList = m.addBookList(new BookList("Test BookList"));
        assertEquals(m, bookList.getModel());
    }

    /**
     * Kontrola, že se do BookListu nedá přidat kniha, která není v modelu.
     */
    @Test
    public void testForeignBookCannotBeAddedToAList() {
        Model m = new Model();
        BookList bookList = m.addBookList(new BookList("Test BookList"));
        Book b = new Book().setName("Some Book");
        exception.expect(ModelIntegrityError.class);
        bookList.addBook(b);
    }
}
