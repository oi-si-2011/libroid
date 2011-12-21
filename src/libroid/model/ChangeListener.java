package libroid.model;

/**
 * Rozhraní, pomocí kterého lze do Modelu zaregistrovat objekty - implementace
 * tohoto rozhraní, jejichž metoda changePerformed() bude zavolána při
 * jakékoliv změně dat v modelu. To je využíváno pro automatickou obnovu
 * dat v GUI, aniž by datový model musel o nějakém GUI vůbec přímo vědět.
 * Tím je dosaženo nižšího provázání (low coupling).
 */
public interface ChangeListener {

    void changePerformed();
}
