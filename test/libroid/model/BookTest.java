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
     * Test of equals method, of class Book.
     */
    @Test
    public void testEquals() {
        Book b0 = new Book().setName("Vlakna hypercasu").setAuthor("R. Susta");
        Book b1 = new Book().setName("Kryptonomikon").setAuthor("N. Stephenson");
        assertTrue(b0.equals(b0));
        assertFalse(b0.equals(b1));
        assertFalse(b0.equals(null));
    }

    @Test
    public void testToString() {
        Book b = new Book().setName("name").setAuthor("author");
        assertTrue(b.toString().equals("Book(name='name' author='author' file='null')"));
    }
}
