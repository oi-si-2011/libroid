package libroid.model;

import java.util.ArrayList;
import java.util.Arrays;
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

    public Book addBook(Book book) {
        if (hasBook(book)) {
            throw new RuntimeException("Book " + book + " is already present in model " + this);
        }
        allBooks.add(book);
        book.setModel(this);
        logger.log(Level.INFO, "Added book {0} to model", book);
        fireChange();
        return book;
    }

    public int bookCount() {
        return allBooks.size();
    }

    public Book getBook(int index) {
        Book b = allBooks.get(index);
        assert b.getModel() == this;
        return b;
    }

    /**
     * Odstranění knih z tohoto modelu.
     */
    public void removeBooks(List<Book> booksToRemove) {
        logger.log(Level.INFO, "Removing {0} books: {1}",
                new Object[]{booksToRemove.size(), booksToRemove});

        // odstraníme knihy ze všech seznamů
        for (BookList bl : allBookLists) {
            bl.removeAllBooks(booksToRemove);
        }

        // samotné odstranění knih
        allBooks.removeAll(booksToRemove);
        // zrušíme mazaným knihám napojení na tento model

        for (Book b : booksToRemove) {
            b.setModel(null);
        }

        fireChange();
    }

    public static Model createSampleModel() {
        Model m = new Model();

        Book book0 = m.addBook(new Book().setName("Vlakna hypercasu").setAuthor("R. Susta"));
        Book book1 = m.addBook(new Book().setName("Kryptonomikon").setAuthor("N. Stephenson"));
        Book book2 = m.addBook(new Book().setName("Velke U").setAuthor("Neal Stephenson"));
        Book book3 = m.addBook(new Book().setName("Hordubal").setAuthor("K. Capek"));

        BookList bl;
        bl = new BookList("Stephenson");
        bl.addBook(book1);
        bl.addBook(book2);
        m.addBookList(bl);
        bl = new BookList("Ceske knihy");
        bl.addBook(book0);
        bl.addBook(book3);
        m.addBookList(bl);

        return m;
    }

    public List<Book> getAllBooks() {
        return allBooks;
    }

    public BookList addBookList(BookList bl) {
        allBookLists.add(bl);
        bl.setModel(this);
        return bl;
    }

    public BookList getBookList(int i) {
        return allBookLists.get(i);
    }

    public List<BookList> getAllBookLists() {
        return allBookLists;
    }

    public void removeList(BookList bookList) {
        removeLists(Arrays.asList(bookList));
    }

    private void removeLists(List<BookList> bookListsToRemove) {
        allBookLists.removeAll(bookListsToRemove);
        for (BookList bl : bookListsToRemove) {
            bl.setModel(null);
        }
        fireChange();
    }

    boolean hasBook(Book b) {
        return allBooks.contains(b);
    }
}
