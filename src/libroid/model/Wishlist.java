package libroid.model;

/**
 * Zatim si nejsme jisti konkretni podobou teto tridy.
 * Funkcnost by mela byt takova, ze by program nasel zaznamy knih, ke kterym
 * nemame fyzicke knihy, a pripadne dohledal, zda byly vydany.
 */
public class Wishlist {

    public String name;
    public int date_created;
    public int date_last_modified;

    public Wishlist(String name) {
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