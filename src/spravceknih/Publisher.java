package spravceknih;

public class Publisher {

    public String name;
    public String nationality;
    public String homepage;

    public Publisher(String name) {
        this.name = name;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }
}