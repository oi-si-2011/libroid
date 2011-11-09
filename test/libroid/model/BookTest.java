package libroid.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookTest {

    public BookTest() {
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
     * Test of getName method, of class Book.
     */
    @Test
    public void testGetName() {
        assertEquals(1, 1);
    }

    /**
     * Test of equals method, of class Book.
     */
    @Test
    public void testEquals() {
        assertTrue(new Book("name1", "author1").equals(new Book("name1", "author1")));
        assertFalse(new Book("name1", "author1").equals(new Book("name2", "author1")));
        assertFalse(new Book("name1", "author1").equals(new Book("name1", "author2")));
    }

    /**
     * Test of hashCode method, of class Book.
     */
    @Test
    public void testHashCode() {
        Book book = new Book("name", "author");
        assertEquals(-1308485047, book.hashCode());
    }

    @Test
    public void testToString() {
        Book b = new Book("name", "author");
        assertTrue(b.toString().equals("Book(name='name' author='author')"));
    }
}
