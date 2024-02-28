package game.reset;

import java.util.ArrayList;
import java.util.List;

/**
 * A reset manager class that manages a list of resttables.
 * @author Alyssa Ting Sue-Lyn
 * Modified by: Benedict Lee Dao-Ee
 */
public class RestManager {
    /**
     * ArrayList of resttables
     */
    private List<Resttable> resttables;

    /**
     * A singleton rest manager instance
     */
    private static RestManager instance;

    /**
     * Get the singleton instance of rest manager
     *
     * @return RestManager singleton instance
     */
    public static RestManager getInstance() {
        if (instance == null) {
            instance = new RestManager();
        }
        return instance;
    }

    /**
     * Constructor
     */
    private RestManager() {
        this.resttables = new ArrayList<>();
    }

    /**
     * Reset the game by traversing through all the list By doing this way, it will avoid using
     * `instanceof` all over the place.
     */
    public void run() {
        resttables.forEach(Resttable::rest);
    }

    /**
     * Add the Resttable instance to the list
     * @param resettable  Resttable instance to be added
     */
    public void registerResttable(Resttable resettable) {
        resttables.add(resettable);
    }
}
