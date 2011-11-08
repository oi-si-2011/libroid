package libroid.model.serialization;

import java.io.ByteArrayOutputStream;
import libroid.model.Book;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

class LibraryXMLBuilder {

    Document xmldoc;
    Element books;

    public LibraryXMLBuilder() {
        xmldoc = XMLUtil.createDocument();
        Element root = xmldoc.createElement("LibroidLibrary");
        xmldoc.appendChild(root);
        books = xmldoc.createElement("books");
        root.appendChild(books);
    }

    void addBook(Book b) {
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

    byte[] serialize() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        XMLUtil.serialize(xmldoc, os);
        return os.toByteArray();
    }
}
