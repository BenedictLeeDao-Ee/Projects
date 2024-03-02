package game.actors.enemies;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.AttackBehaviour;
import game.status.Status;

/**
 * BEHOLD, CANINE!
 * Created by:
 * @author Chang Yee Vern
 * Modified by: Alyssa Ting Sue-Lyn
 *
 */
public abstract class Canine extends Enemy {
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     * @param runes       the amount of runes that the actor will drop when killed
     */
    public Canine(String name, char displayChar, int hitPoints, int runes) {
        super(name, displayChar, hitPoints, runes);
        this.addCapability(Status.HOSTILE_TO_BONES);
        this.addCapability(Status.HOSTILE_TO_CRUSTACEAN);
        this.addCapability(Status.HOSTILE_TO_CASTLE);
        this.addCapability(Status.HOSTILE_TO_INVADER);
    }

    /**
     * The Dog can be attacked by any actor that has the HOSTILE_TO_CANINE capability
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a list of allowable actions that an attacking Actor can perform on this Canine object
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        if (otherActor.hasCapability(Status.HOSTILE_TO_CANINE)) {
            super.behaviours.put(1, new AttackBehaviour(otherActor));
        }
        return actions;
    }
}
