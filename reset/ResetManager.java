package game.reset;

import java.util.ArrayList;
import java.util.List;

/**
 * A reset manager class that manages a list of resettables.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Chang Yee Vern
 *
 */
public class ResetManager {
    private List<Resettable> resettables;

    /**
     * A singleton reset manager instance
     */
    private static ResetManager instance;

    /**
     * Get the singleton instance of reset manager
     *
     * @return ResetManager singleton instance
     */
    public static ResetManager getInstance() {
        if (instance == null) {
            instance = new ResetManager();
        }
        return instance;
    }

    private ResetManager() {
        this.resettables = new ArrayList<>();
    }

    /**
     * Reset the game by traversing through all the list By doing this way, it will avoid using
     * `instanceof` all over the place.
     */
    public void run() {
        resettables.forEach(Resettable::reset);
    }

    /**
     * Add the Resettable instance to the list
     */
    public void registerResettable(Resettable resettable) {
        resettables.add(resettable);
    }

    /**
     * Remove a Resettable instance from the list
     *
     * @param resettable resettable object to be removed.
     */
    public void removeResettable(Resettable resettable) {
        resettables.remove(resettable);
    }
}
