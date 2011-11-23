package libroid.model;

import java.util.ArrayList;
import java.util.List;

public class BookList {

    private String name;
    private int date_created;
    private int date_last_modified;
    private List<Book> books = new ArrayList<Book>();

    public BookList(String name) {
        this.name = name;
    }

    public void setBooks(List<Book> books){
        this.books = books;
    }

    public int getDate_created() {
        return date_created;
    }

    public int getDate_last_modified() {
        return date_last_modified;
    }

    public String getName() {
        return name;
    }

    void addBook(Book book) {
        books.add(book);
    }

    public Book getBook(int i) {
        return books.get(i);
    }

    public int getBooksCount(){
        return books.size();
    }
}
