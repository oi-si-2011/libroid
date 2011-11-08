package libroid.model;

public class Book{
    private String name;
    private String author;

    // private String separator = ":;:";

    public Book(String name, String author){
        this.name = name;
        this.author = author;
    }

    /*
    public Book(String bookInString) {
        String [] data = bookInString.split(separator);
        this.name = data[0];
        this.author = data[1];
    }
     */

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    /**
     * Get a string description of a book.
     * Only for debugging purposes.
     */
    @Override
    public String toString(){
        return String.format("Book(name='%s' author='%s')", this.name, this.author);
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

}