package libroid.model;

import java.io.File;
import java.util.logging.Logger;

public class Book {

    private static final Logger logger = Logger.getLogger(Book.class.getName());
    private Model model;
    private String name;
    private String author;
    private File file;
    //private int isbn;
    //private String genre;

    public Book() {
    }

    public void setModel(Model newModel) {
        assert this.model == null || !this.model.hasBook(this);
        assert newModel == null || newModel.hasBook(this);
        this.model = newModel;
    }

    Model getModel() {
        return this.model;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    /*
    public String getGenre() {
    return genre;
    }
    
    public int getIsbn() {
    return isbn;
    }
     */
    public File getFile() {
        return file;
    }

    /**
     * Get a string description of a book.
     * Only for debugging purposes.
     */
    @Override
    public String toString() {
        return String.format("Book(name='%s' author='%s' file='%s')",
                this.name, this.author, this.file);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.author == null) ? (other.author != null) : !this.author.equals(other.author)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 29 * hash + (this.author != null ? this.author.hashCode() : 0);
        return hash;
    }

    public Book setName(String name) {
        this.name = name;
        fireModelChange();
        return this;
        // Měl by setter vracet this? Myslím, že zde je to OK.
        // http://stackoverflow.com/questions/1345001/is-it-bad-practice-to-make-a-setter-return-this
    }

    public Book setAuthor(String author) {
        this.author = author;
        fireModelChange();
        return this;
    }

    public Book setFile(File file) {
        this.file = file;
        return this;
    }

    private void fireModelChange() {
        if (model != null) {
            model.fireChange();
        }
    }
}
