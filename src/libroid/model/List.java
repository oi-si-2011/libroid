package libroid.model;

public class List {

    private String name;
    private int date_created;
    private int date_last_modified;

    public List(String name) {
        this.name = name;
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
}
