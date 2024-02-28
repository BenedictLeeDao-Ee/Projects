package game.actors.guests;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.*;
import game.behaviours.*;
import game.reset.Resettable;
import game.roles.Role;
import game.status.Status;
import game.utils.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * BEHOLD, ALLY!
 *
 * Created by:
 * @author Chang Yee Vern
 *
 */
public class Ally extends Actor implements Resettable {
    protected Map<Integer, Behaviour> behaviours = new HashMap<>();

    /**
     * The display character of Ally
     */
    private static final char DISPLAY_CHAR = 'A';

    /**
     * Constructor
     * @param role the starting role of Ally
     */
    public Ally(Role role) {
        super("Ally", DISPLAY_CHAR, role.hitPoints());
        this.addWeaponToInventory(role.weapon());
        this.behaviours.put(999, new WanderBehaviour());
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        registerInstance();
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
        if (this.hasCapability(Status.RESETTING)) { // actors despawn when game has been reset
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
     * The Ally can be attacked by any actor that does not have the HOSTILE_TO_ENEMY capability
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a list of allowable actions that an attacking Actor can perform on this Enemy object
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (!otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            int randomBehavior = Utils.getRandomInt(2);
            behaviours.put(randomBehavior, new AttackBehaviour(otherActor));
            behaviours.put(randomBehavior == 1? 0 : 1, new SpecialSkillBehaviour(otherActor));
            this.addCapability(Status.FOLLOWING);
        }
        return actions;
    }

    /**
     * When the game resets, the Ally will gain the capability of resetting
     */
    @Override
    public void reset() {
        this.addCapability(Status.RESETTING);
    }
}
