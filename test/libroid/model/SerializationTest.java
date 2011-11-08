package libroid.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class SerializationTest {

    @Test
    public void testSerializeEmptyModel() {
        Model m = new Model();
        String result = Serialization.serialize(m);
        assertEquals("??", result);
    }

    @Test
    public void testSerializeSampleModel() {
        Model m = new Model();
        m.addBook(new Book("Vlakna hypercasu", "R. Susta"));
        m.addBook(new Book("Kryptonomikon", "N. Stephenson"));
        String result = Serialization.serialize(m);
        assertEquals("??", result);
    }
}
