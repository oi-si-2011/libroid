package libroid.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Model {

    private static final Logger logger = Logger.getLogger(Model.class.getName());
    private List<Book> allBooks = new ArrayList<Book>();
    private List<BookList> alLBookLists = new ArrayList<BookList>();

    public Model() {
    }

    public void addBook(Book book) {
        allBooks.add(book);
    }

    public int bookCount() {
        return allBooks.size();
    }

    public Book getBook(int index) {
        return allBooks.get(index);
    }

    public void removeBooks(List<Book> booksToRemove) {
        logger.log(Level.INFO, "Removing {0} books: {1}",
                new Object[]{booksToRemove.size(), booksToRemove});
        allBooks.removeAll(booksToRemove);
    }

    public static Model createSampleModel() {
        Model m = new Model();

        m.addBook(new Book("Vlakna hypercasu", "R. Susta"));
        m.addBook(new Book("Kryptonomikon", "N. Stephenson"));
        m.addBook(new Book("Velke U", "Neal Stephenson"));
        m.addBook(new Book("Hordubal", "K. Capek"));
        m.addBook(new Book("A", "1"));
        m.addBook(new Book("B", "2"));

        BookList bl;
        bl = new BookList("Stephenson");
        bl.addBook(m.getBook(1));
        bl.addBook(m.getBook(2));
        m.addBookList(bl);
        bl = new BookList("Ceske knihy");
        bl.addBook(m.getBook(0));
        bl.addBook(m.getBook(3));
        m.addBookList(bl);
        m.addBookList(new BookList("Seznam 3"));

        return m;
    }

    public List<Book> getAllBooks() {
        return allBooks;
    }

    public void addBookList(BookList bl) {
        alLBookLists.add(bl);
    }

    public List<BookList> getAlLBookLists() {
        return alLBookLists;
    }
}
