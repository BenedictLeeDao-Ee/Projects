package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Consumables;

/**
 * An action for consuming an item.
 * Created by:
 * @author Benedict Lee Dao-Ee
 * Modified by: Chang Yee Vern, Alyssa Ting Sue-Lyn
 *
 */
public class ConsumeAction extends Action {

    /**
     * The item to be consumed.
     */
    private final Consumables consumable;

    /**
     * Constructor
     *
     * @param consumable The item to be consumed.
     */
    public ConsumeAction(Consumables consumable) {
        this.consumable = consumable;
    }

    /**
     * When the actor consumes a consumable, its effect will be triggered
     * and the amount of the consumable will decrease
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A message that says what the consumable is and how many do they have left
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (consumable.counterCheck()) {
            consumable.consumeEffect(actor);
        } else {
            return consumable + " (" + consumable.getCounter() + "/" + consumable.getMaxCounter() + ") is empty\n" + menuDescription(actor);
        }
        return menuDescription(actor);
    }

    /**
     * Describes which actor consumes which item
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        String counterString = consumable.getMaxCounter() > 1 ? " (" + consumable.getCounter() + "/" + consumable.getMaxCounter() + ")" : "";
        return actor + " consumes " + consumable + counterString;
    }
}
