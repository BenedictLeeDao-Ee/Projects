package game.reset;

/**
 * A resttable interface
 * Created by:
 * @author Alyssa Ting Sue-Lyn
 */
public interface Resttable {
    /**
     * Allows any classes that use this interface to reset abilities, attributes, and/or items.
     */
    void rest();

    /**
     * a default interface method that register current instance to the Singleton manager. It allows
     * corresponding class uses to be affected by global reset
     */
    default void registerInstanceRest() {
        RestManager.getInstance().registerResttable(this);
    }
}
