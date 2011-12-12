package libroid.model.serialization;

import java.io.ByteArrayOutputStream;
import libroid.model.Book;
import libroid.model.Model;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Serialization {

    Document xmldoc;
    Element books;

    private Serialization() {
        xmldoc = XMLUtil.createDocument();
        Element root = xmldoc.createElement("LibroidLibrary");
        xmldoc.appendChild(root);
        books = xmldoc.createElement("books");
        root.appendChild(books);
    }

    private void serializeModel(Model m) {
        for (Book b : m.getAllBooks()) {
            addBook(b);
        }
    }

    private byte[] getBytes() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        XMLUtil.serialize(xmldoc, os);
        return os.toByteArray();
    }

    static byte[] serialize(Model m) {
        Serialization s = new Serialization();
        s.serializeModel(m);
        return s.getBytes();
    }

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
}
