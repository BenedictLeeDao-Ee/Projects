package game.roles;

import edu.monash.fit2099.engine.displays.Display;

/**
 * A utility class for managing player roles and combat archetypes.
 * It represents a role that a player can select for combat.
 * Created by:
 * @author Chang Yee Vern
 * Modified by:
 */
public class RoleManager {

    /**
     * Displays a menu of combat archetypes for the player to select from, using the provided display.
     * @param display use to do printing
     * @return the player's selected archetype as an integer
     */
    public static char menuItem(Display display) {

        display.println("Select your role: ");
        display.println("s) Samurai");
        display.println("b) Bandit");
        display.println("w) Wretch");
        display.println("a) Astrologer");
        char choice = display.readChar();
        display.println("Your choice: " + choice);
        return choice;
    }

    /**
     * Displays a menu of combat archetypes for the player to select from, and creates a
     * new player with the selected archetype.
     * @return the new player with the selected archetype.
     */
    public static Role selectCombatArchetypes(char selection) {
        switch (selection) {
            case 's':
                return new Samurai();
            case 'b':
                return new Bandit();
            case 'w':
                return new Wretch();
            case 'a':
                return new Astrologer();
            default:
                return null;
        }
    }
}
