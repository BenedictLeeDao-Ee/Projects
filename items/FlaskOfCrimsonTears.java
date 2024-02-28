package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;
import game.reset.Resettable;

/**
 * Flask of Crimson Tears is an item that can be consumed by actors to restore health points.
 * It implements the Resettable and Consumables interfaces to provide reset and consume functionality.
 * Created by:
 * @author Chang Yee Vern
 * Modified by: Benedict Lee Dao-Ee, Alyssa Ting Sue-Lyn
 */
public class FlaskOfCrimsonTears extends Item implements Resettable, Consumables {
    private static final String NAME = "Flask of Crimson Tears";
    private static final char DISPLAY_CHAR = 'c';
    /**
     * The amount that the consumable heals for
     */
    private static final int HEAl_AMOUNT = 250;
    /**
     * The amount of charges the player spawns with
     */
    private static final int STARTING_COUNTER = 2;
    /**
     * The current amount of charges
     */
    private int counter;

    /**
     * Constructor.
     */
    public FlaskOfCrimsonTears() {
        super(NAME, DISPLAY_CHAR, false);
        registerInstance();
        setCounter(STARTING_COUNTER);
        this.addAction(new ConsumeAction(this));
    }

    /**
     * Executed when the game resets
     */
    @Override
    public void reset() {
        this.counter = STARTING_COUNTER;
    }

    /**
     * Gets how charges the consumable has left
     * @return The number of times the player can consume the consumable
     */
    @Override
    public int getCounter() {
        return counter;
    }

    /**
     * Sets how charges the consumable has left
     * @param counter The number of times the player can consume the consumable
     */
    @Override
    public void setCounter(int counter) {
        this.counter = counter;
    }

    /**
     * An effect happens when the consumable is consumed
     * @param actor The actor consuming the consumable
     */
    @Override
    public void consumeEffect(Actor actor) {
        actor.heal(HEAl_AMOUNT);
        this.counter -= 1;
    }

    /**
     * Check if there are anymore charges left
     * @return Boolean value to tell you if you have anymore charges left
     */
    @Override
    public boolean counterCheck() {
        return this.counter != 0;
    }

    /**
     * Gets the maximum number of usages the item has
     * @return the maximum amount of usages the item has
     */
    @Override
    public int getMaxCounter() {
        return STARTING_COUNTER;
    }
}
