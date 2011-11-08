package libroid.model;

import java.io.ByteArrayOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;

public class Serialization {

    static byte[] serialize(Model m) {
        XMLBuilder builder = new XMLBuilder();

        for (int i = 0; i < m.bookCount(); i++) {
            Book b = m.getBook(i);
            builder.addBook(b);
        }

        return builder.serialize();
    }

    private static class XMLBuilder {

        Document xmldoc;

        public XMLBuilder() {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder;
            try {
                builder = factory.newDocumentBuilder();
            } catch (ParserConfigurationException ex) {
                throw new RuntimeException(ex);
            }
            xmldoc = builder.newDocument();
        }

        public byte[] serialize() {
            ByteArrayOutputStream os = new ByteArrayOutputStream();

            TransformerFactory tranFactory = TransformerFactory.newInstance();
            Transformer aTransformer;
            try {
                aTransformer = tranFactory.newTransformer();
            } catch (TransformerConfigurationException ex) {
                throw new RuntimeException(ex);
            }
            aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
            Source src = new DOMSource(xmldoc);
            Result dest = new StreamResult(os);
            try {
                aTransformer.transform(src, dest);
            } catch (TransformerException ex) {
                throw new RuntimeException(ex);
            }

            return os.toByteArray();
        }

        private void addBook(Book b) {
        }
    }
}
