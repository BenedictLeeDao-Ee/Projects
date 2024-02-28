package game.actors.enemies;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.AttackBehaviour;
import game.status.Status;

/**
 * BEHOLD, CASTLE!
 * Created by:
 * @author Chang Yee Vern
 *
 */
public abstract class Castle extends Enemy {
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     * @param runes       the amount of runes that the actor will drop when killed
     */
    public Castle(String name, char displayChar, int hitPoints, int runes) {
        super(name, displayChar, hitPoints, runes);
        this.addCapability(Status.HOSTILE_TO_CANINE);
        this.addCapability(Status.HOSTILE_TO_BONES);
        this.addCapability(Status.HOSTILE_TO_CRUSTACEAN);
    }

    /**
     * The Castle enemy can be attacked by any actor that has the HOSTILE_TO_CASTLE capability
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a list of allowable actions that an attacking Actor can perform on this Castle object
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        if (otherActor.hasCapability(Status.HOSTILE_TO_CASTLE)) {
            super.behaviours.put(1, new AttackBehaviour(otherActor));
        }
        return actions;
    }
}
