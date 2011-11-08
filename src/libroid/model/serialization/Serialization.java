package libroid.model.serialization;

import libroid.model.Book;
import libroid.model.Model;

public class Serialization {

    static byte[] serialize(Model m) {
        LibraryXMLBuilder builder = new LibraryXMLBuilder();

        for (int i = 0; i < m.bookCount(); i++) {
            Book b = m.getBook(i);
            builder.addBook(b);
        }

        return builder.serialize();
    }
}
