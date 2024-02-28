package game.actors;

/**
 * To store the player's current and previous location
 * Created by:
 * @author Chang Yee Vern
 * Modified by: Alyssa Ting Sue-Lyn
 */
public class PlayerLocation {
    /**
     * Player's current x coordinate
     */
    private int x;
    /**
     * Player's current y coordinate
     */
    private int y;
    /**
     * Player's previous x coordinate
     */
    private int prevX;
    /**
     * Player's previous y coordinate
     */
    private int prevY;
    /**
     * A unique instance of PlayerLocation
     */
    private static PlayerLocation instance = null;

    /**
     * Constructor
     * @param x current x coordinate
     * @param y current y coordinate
     */
     private PlayerLocation(int x, int y) {
         this.x = x;
         this.y = y;
         this.prevX = x;
         this.prevY = y;
     }

    /**
     * Factory method that creates a single instance of PlayerLocation
     * @param x current x coordinate
     * @param y current y coordinate
     * @return instance of PlayerLocation
     */
     public static PlayerLocation getInstance(int x, int y) {
        if (instance == null) {
            instance = new PlayerLocation(x, y);
        }
        return instance;
     }

    /**
     * Updates the location of the player
     * @param newX current x coordinate
     * @param newY current y coordinate
     */
    public void updateLocation(int newX, int newY) {
        setPrevX(x);
        setPrevY(y);
        this.x = newX;
        this.y = newY;
    }

    /**
     * Sets the previous x coordinate
     * @param prevX previous x coordinate
     */
    public void setPrevX(int prevX) {
        this.prevX = prevX;
    }

    /**
     * Sets the previous y coordinate
     * @param prevY previous y coordinate
     */
    public void setPrevY(int prevY) {
        this.prevY = prevY;
    }

    /**
     * Gets the previous x coordinate
     * @return The previous x coordinate
     */
    public int getPrevX() {
        return prevX;
    }

    /**
     * Gets the previous y coordinate
     * @return The previous x coordinate
     */
    public int getPrevY() {
        return prevY;
    }
}
