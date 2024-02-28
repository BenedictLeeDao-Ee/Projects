package game.reset;

/**
 * A resettable interface
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Chang Yee Vern
 *
 */
public interface Resettable {

    /**
     * Allows any classes that use this interface to reset abilities, attributes, and/or items.
     */
    void reset();

    /**
     * a default interface method that register current instance to the Singleton manager. It allows
     * corresponding class uses to be affected by global reset
     */
    default void registerInstance() {
        ResetManager.getInstance().registerResettable(this);
    }

    /**
     * Removes this instance from ResetManager
     */
    default void removeInstance() {
        ResetManager.getInstance().removeResettable(this);
    }
}
