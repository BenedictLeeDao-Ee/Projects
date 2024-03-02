package game.trade;

import game.actors.Player;
import game.actors.enemies.Enemy;
import game.actors.traders.Trader;
import game.items.RemembranceOfTheGrafted;

import java.util.ArrayList;

/**
 * Used to check if an Actor/Item/Weapon is an instance of a specific class and to return the object of that type without using instanceof or down-casting.
 * Created by:
 * @author Alyssa Ting Sue-Lyn
 * Modified by: Benedict Lee Dao-Ee
 */
public class WalletManager {
    /**
     * Stores the player
     */
    private Player player;

    /**
     * ArrayList containing all enemies on the map
     */
    private final ArrayList<Enemy> enemies;

    /**
     * ArrayList containing all the traders in the game
     */
    private ArrayList<Trader> traders;

    /**
     * ArrayList containing all the RemembranceOfTheGrafted in the game
     */
    private ArrayList<RemembranceOfTheGrafted> remembranceOfTheGrafteds;

    /**
     * A unique instance of InstanceManager
     */
    private static WalletManager instance;

    /**
     * Constructor
     */
    public WalletManager() {
        this.enemies = new ArrayList<>();
        this.traders = new ArrayList<>();
        this.remembranceOfTheGrafteds = new ArrayList<>();
    }

    /**
     * A factory method that creates a single instance of WalletManager
     * @return the unique instance of WalletManager
     */
    public static WalletManager getInstance() {
        if (instance == null) {
            instance = new WalletManager();
        }
        return instance;
    }

    /**
     * Sets the player
     * @param player the player to be set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Gets the player
     * @return the player on the map
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Adds an enemy into the list
     * @param enemy enemy to be added into the list
     */
    public void addEnemies(Enemy enemy) {
        enemies.add(enemy);
    }

    /**
     * Removes an enemy from the list
     * @param enemy enemy to be removed
     */
    public void removeEnemies(Enemy enemy) {
        enemies.remove(enemy);
    }

    /**
     * Gets the list of enemies
     * @return the list of enemies
     */
    public ArrayList<Enemy> getEnemies() { return this.enemies; }

    /**
     * Gets the list of traders
     * @return the list of traders
     */
    public ArrayList<Trader> getTraders() {
        return traders;
    }

    /**
     * Sets the list of traders
     */
    public void setTraders(ArrayList<Trader> traders) {
        this.traders = traders;
    }

    /**
     * Adds a trader to the list of traders
     */
    public void addTrader(Trader trader) {
        traders.add(trader);
    }

    /**
     * Gets the list of RemembranceOfTheGrafted
     * @return the list of RemembranceOfTheGrafted
     */
    public ArrayList<RemembranceOfTheGrafted> getRemembranceOfTheGrafteds() {
        return remembranceOfTheGrafteds;
    }

    /**
     * Adds a RemembranceOfTheGrafted to the list of RemembranceOfTheGrafted
     */
    public void addRemembranceOfTheGrafted(RemembranceOfTheGrafted remembranceOfTheGrafted) {
        remembranceOfTheGrafteds.add(remembranceOfTheGrafted);
    }

}
