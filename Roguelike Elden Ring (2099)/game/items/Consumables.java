package game.items;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * A consumable interface
 * Created by:
 * @author Benedict Lee Dao-Ee
 * Modified by: Alyssa Ting Sue-Lyn
 */
public interface Consumables {
    /**
     * Gets how charges the consumable has left
     * @return The number of times the player can consume the consumable
     */
    int getCounter();

    /**
     * Sets how charges the consumable has left
     * @param counter The number of times the player can consume the consumable
     */
    void setCounter(int counter);

    /**
     * An effect happens when the consumable is consumed
     * @param actor The actor consuming the consumable
     */
    void consumeEffect(Actor actor);

    /**
     * Check if there are anymore charges left
     * @return Boolean value to tell you if you have anymore charges left
     */
    boolean counterCheck();

    /**
     * Gets the maximum number of usages
     * @return a number representing the maximum number of usages
     */
    int getMaxCounter();
}
