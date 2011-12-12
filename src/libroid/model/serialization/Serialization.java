package libroid.model.serialization;

import java.io.ByteArrayOutputStream;
import libroid.model.Book;
import libroid.model.Model;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Serialization {

    Document xmldoc;
    Element books;

    /**
     * Konstruktor. Je privátní, pro serializaci modelu použijte statickou
     * funkci serialize.
     */
    private Serialization() {
        xmldoc = XMLUtil.createDocument();
        Element root = xmldoc.createElement("LibroidLibrary");
        xmldoc.appendChild(root);
        books = xmldoc.createElement("books");
        root.appendChild(books);
    }

    /**
     * Projde model a vše z něj přidá do XML.
     */
    private void serializeModel(Model m) {
        for (Book b : m.getAllBooks()) {
            addBook(b);
        }
    }

    /**
     * Přidá knihu do XML.
     */
    private void addBook(Book b) {
        Element book = xmldoc.createElement("Book");
        books.appendChild(book);
        Element e;
        e = xmldoc.createElement("name");
        e.appendChild(xmldoc.createTextNode(b.getName()));
        book.appendChild(e);
        e = xmldoc.createElement("author");
        e.appendChild(xmldoc.createTextNode(b.getAuthor()));
        book.appendChild(e);
    }

    /**
     * Vrátí samotná XML data.
     */
    private byte[] getBytes() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        XMLUtil.serialize(xmldoc, os);
        return os.toByteArray();
    }

    /**
     * Serializuje model do XML.
     */
    public static byte[] serialize(Model m) {
        Serialization s = new Serialization();
        s.serializeModel(m);
        return s.getBytes();
    }
}
