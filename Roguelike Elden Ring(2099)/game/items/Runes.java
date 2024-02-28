package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.Location;
import game.reset.Resettable;
import game.status.Status;

/**
 * Runes is an item that act as "money" and can be used to purchase weapons from Merchant Kale
 * Created by:
 * @author Chang Yee Vern
 * Modified by: Alyssa Ting Sue-Lyn
 */
public class Runes extends Item implements Resettable {
    /**
     * Runes display character when dropped on the map
     */
    private static final char DISPLAY_CHAR = '$';

    /**
     * Runes' value
     */
    private int value;

    /**
     * Counter used to keep track of how many times the player has died since dropping this rune
     */
    private int counter;

    /**
     * Constructor.
     */
    public Runes(int newValue) {
        super("$" + newValue, DISPLAY_CHAR, true);
        this.setValue(newValue);
        this.counter = 1;
        registerInstance();
    }

    /**
     * Gets the rune's value
     * @return rune's value
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Sets a value for the runes
     * @param newValue the new value of the runes
     */
    public void setValue(int newValue) {
        this.value = newValue;
    }

    /**
     * Create and return an action to drop this Item.
     * If this Item is not portable, returns null.
     * @param actor the actor that is performing the action
     * @return a new DropRunesAction if this Item is portable, null otherwise.
     */
    @Override
    public DropAction getDropAction(Actor actor) {
        if(portable)
            return new DropRunesAction(this);
        return null;
    }

    /**
     * Create and return an action to pick this Item up.
     * If this Item is not portable, returns null.
     *
     * @return a new RecoverRunesAction if this Item is portable, null otherwise.
     */
    @Override
    public PickUpAction getPickUpAction(Actor actor) {
        if(portable)
            return new RecoverRunesAction(this);
        return null;
    }

    /**
     * Gets the counter for how many times the player has died since dropping this rune
     * @return a number that represents how many times the player has died
     */
    public int getCounter() { return this.counter; }

    /**
     * Sets the counter for how many times the player has died since dropping this rune
     * @param counter a number to be set
     */
    public void setCounter(int counter) { this.counter = counter; }

    /**
     * Decrement the counter by 1
     */
    public void decreaseCounter() {
        this.setCounter(this.getCounter() - 1);
    }

    /**
     * Checks if the counter has reached 0
     * @return true if counter is 0, false otherwise
     */
    public boolean counterCheck() {
        return getCounter() != 0;
    }

    /**
     * Used to remove the dropped runes from the ground once the player dies a second time since dropping this rune
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        if (this.hasCapability(Status.RESETTING)) {
            this.removeCapability(Status.RESETTING);
            currentLocation.removeItem(this);
        }
    }

    @Override
    public void reset() {
        if (!counterCheck()) {
            this.addCapability(Status.RESETTING);
        } else {
            decreaseCounter();
        }
    }
}
