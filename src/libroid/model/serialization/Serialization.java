package libroid.model.serialization;

import java.io.ByteArrayOutputStream;
import libroid.model.Book;
import libroid.model.Model;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Serializace.
 */
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
        books = addElement(root, "books");
    }

    /**
     * Přidá element do XML.
     */
    private Element addElement(Element parentElement, String elementName) {
        Element e = xmldoc.createElement(elementName);
        parentElement.appendChild(e);
        return e;
    }

    /**
     * Přidá element do XML a vloží do něj daný text.
     */
    private void addElement(Element parentElement, String elementName, String contents) {
        Element e = addElement(parentElement, elementName);
        e.appendChild(xmldoc.createTextNode(contents));
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
        Element book = addElement(books, "Book");
        addElement(book, "name", b.getName());
        addElement(book, "author", b.getAuthor());
        if (b.getFile() == null) {
            addElement(book, "file", "");
        } else {
            addElement(book, "file", b.getFile().getAbsolutePath());
        }
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
