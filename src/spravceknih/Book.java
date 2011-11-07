package spravceknih;

public class Book{
    private int index;
    private String name;
    private String author;

    public Book(String name, String author){
        this.name = name;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}