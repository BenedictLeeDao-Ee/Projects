package game.actors.guests;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.DespawnAction;
import game.actors.enemies.Enemy;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.SpecialSkillBehaviour;
import game.roles.Role;
import game.status.Status;
import game.utils.Utils;

/**
 * BEHOLD, INVADERS!
 *
 * Created by:
 * @author Chang Yee Vern
 *
 */
public class Invader extends Enemy {

    /**
     * The display character of Invader
     */
    private static final char DISPLAY_CHAR = 'ඞ';

    /**
     * Constructor
     * @param role the starting role of Invader
     */
    public Invader(Role role) {
        super("Invader", DISPLAY_CHAR, role.hitPoints(), Utils.getRandomInt(1358, 5578));
        this.addWeaponToInventory(role.weapon());
        this.hasCapability(Status.HOSTILE_TO_BONES);
        this.hasCapability(Status.HOSTILE_TO_CANINE);
        this.hasCapability(Status.HOSTILE_TO_CRUSTACEAN);
        this.hasCapability(Status.HOSTILE_TO_CASTLE);
    }

    /**
     * At each turn, select a valid action to perform.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (this.hasCapability(Status.RESETTING)) {
            this.removeCapability(Status.RESETTING);
            return new DespawnAction();
        }

        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * The Invader can be attacked by any actor that has the HOSTILE_TO_INVADER capability
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a list of allowable actions that an attacking Actor can perform on this Crustaceans object
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        if (otherActor.hasCapability(Status.HOSTILE_TO_INVADER)) {
            int randomBehavior = Utils.getRandomInt(2);
            behaviours.put(randomBehavior, new AttackBehaviour(otherActor));
            behaviours.put(randomBehavior == 1? 0 : 1, new SpecialSkillBehaviour(otherActor));
        }
        return actions;
    }
}