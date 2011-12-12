package libroid.model.serialization;

import java.io.File;
import libroid.model.Book;
import libroid.model.Model;
import java.io.UnsupportedEncodingException;
import org.junit.Test;
import static org.junit.Assert.*;

public class SerializationTest {

    @Test
    public void testSerializeEmptyModel() throws UnsupportedEncodingException {
        Model m = new Model();
        byte[] result = Serialization.serialize(m);
        String resultStr = new String(result, "latin1");
        //System.out.println("Result: '" + resultStr + "'");
        String expected =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n"
                + "<LibroidLibrary>\n"
                + "    <books/>\n"
                + "</LibroidLibrary>\n";
        assertEquals(expected, resultStr);
    }

    /**
     * Otestování serializace do XML modelu s několika knihami.
     */
    @Test
    public void testSerializeSampleModel() throws UnsupportedEncodingException {
        Model m = new Model();
        m.addBook(new Book("Vlakna hypercasu", "R. Susta").setFile(new File("/some/book.pdf")));
        m.addBook(new Book("Kryptonomikon", "N. Stephenson"));
        byte[] result = Serialization.serialize(m);
        String resultStr = new String(result, "latin1");
        System.out.println("Result: '" + resultStr + "'");
        String expected =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n"
                + "<LibroidLibrary>\n"
                + "    <books>\n"
                + "        <Book>\n"
                + "            <name>Vlakna hypercasu</name>\n"
                + "            <author>R. Susta</author>\n"
                + "            <file>/some/book.pdf</file>\n"
                + "        </Book>\n"
                + "        <Book>\n"
                + "            <name>Kryptonomikon</name>\n"
                + "            <author>N. Stephenson</author>\n"
                + "            <file/>\n"
                + "        </Book>\n"
                + "    </books>\n"
                + "</LibroidLibrary>\n";
        assertEquals(expected, resultStr);
    }
}
