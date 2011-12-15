package libroid.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import libroid.model.exceptions.ModelIntegrityError;

public class BookList {

    private Model model;
    private String name;
    //private int date_created;
    //private int date_last_modified;
    private List<Book> books = new ArrayList<Book>();

    public BookList(String name) {
        this.name = name;
    }

    public void setModel(Model m) {
        this.model = m;
    }

    public Model getModel() {
        return model;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    /*
    public int getDate_created() {
    return date_created;
    }

    public int getDate_last_modified() {
    return date_last_modified;
    }
     */
    public String getName() {
        return name;
    }

    void addBook(Book bookToAdd) {
        addBooks(Arrays.asList(bookToAdd));
    }

    public void addBooks(List<Book> booksToAdd) {
        if (model != null) {
            for (Book b : booksToAdd) {
                if (!model.hasBook(b)) {
                    throw new ModelIntegrityError("Book " + b + " is not present in model " + model);
                }
            }
        }
        this.books.addAll(booksToAdd);
    }

    public Book getBook(int i) {
        return books.get(i);
    }

    public int getBooksCount() {
        return books.size();
    }

    public void setName(String name) {
        this.name = name;
        fireChange();
    }

    public void removeAllBooks(List<Book> booksToRemove) {
        books.removeAll(booksToRemove);
    }

    private void fireChange() {
        if (model != null) {
            model.fireChange();
        }
    }
}
