package libroid.model;

import java.awt.Image;
import java.io.File;
import java.util.logging.Logger;

/**
 * Kniha.
 */
public class Book {

    private static final Logger logger = Logger.getLogger(Book.class.getName());
    private Model model;
    private String name;
    private String author;
    private File file;
    private Image bookletImage;

    public Book() {
    }

    public void setModel(Model newModel) {
        assert this.model == null || !this.model.hasBook(this);
        assert newModel == null || newModel.hasBook(this);
        this.model = newModel;
    }

    public Model getModel() {
        return this.model;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public File getFile() {
        return file;
    }

    public Image getBookletImage() {
        return bookletImage;
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

    public Book setBookletImage(Image newImage) {
        this.bookletImage = newImage;
        return this;
    }

    private void fireModelChange() {
        if (model != null) {
            model.fireChange();
        }
    }
}
