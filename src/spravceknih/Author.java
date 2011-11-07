package spravceknih;

public class Author {

    private String name;
    private String surname;
    private String nationality;
    private int date_of_birth;
    private int date_of_death;

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurame() {
        return surname;
    }

    public String getNationality() {
        return nationality;
    }

    public int getDate_of_birth() {
        return date_of_birth;
    }

    public int getDate_of_death() {
        return date_of_death;
    }
}
