package libroid.model.exceptions;

/**
 * Např. je do seznamu knih přidávána kniha, která nepatří do stejného
 * Modelu jako dotyčný seznam knih.
 */
public class ModelIntegrityError extends RuntimeException {

    public ModelIntegrityError(String msg) {
        super(msg);
    }

}
