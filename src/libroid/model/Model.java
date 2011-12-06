package libroid.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Model {

    private static final Logger logger = Logger.getLogger(Model.class.getName());
    private List<Book> allBooks = new ArrayList<Book>();
    private List<BookList> allBookLists = new ArrayList<BookList>();
    private List<ChangeListener> changeListeners = new ArrayList<ChangeListener>();

    public Model() {
    }

    public void addChangeListener(ChangeListener chl) {
        changeListeners.add(chl);
    }

    public void fireChange() {
        logger.info("fired");
        for (ChangeListener chl : changeListeners) {
            chl.changePerformed();
        }
    }

    public void addBook(Book book) {
        allBooks.add(book);
        logger.log(Level.INFO, "Added book{0}", book);
        fireChange();
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

        return m;
    }

    public List<Book> getAllBooks() {
        return allBooks;
    }

    public void addBookList(BookList bl) {
        allBookLists.add(bl);
    }

    public BookList getBookList(int i) {
        return allBookLists.get(i);
    }

    public List<BookList> getAllBookLists() {
        return allBookLists;
    }

    public void removeList(int selectedIndex) {
        allBookLists.remove(selectedIndex);
    }

    public void renameList(int selectedIndex) {

    }
}
