package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.utils.Utils;

/**
 * A unique skill action for Great Knife
 * Created by:
 * @author Chang Yee Vern
 *
 */
public class QuickstepAction extends Action {

    /**
     * The Actors that is to be attacked
     */
    private final Actor target;

    /**
     * The direction of incoming attack.
     */
    private final String direction;

    /**
     * Weapon used for the attack
     */
    private final Weapon weapon;

    /**
     * The location of the target
     */
    private Location destination;
    private final int chanceToHit;

    /**
     * Constructor.
     *
     * @param target the Actor to attack
     * @param direction the direction where the attack should be performed (only used for display purposes)
     */
    public QuickstepAction(Actor target, String direction, Weapon weapon, int chanceToHit) {
        this.target = target;
        this.direction = direction;
        this.weapon = weapon;
        this.chanceToHit = chanceToHit;
    }

    /**
     * When executed, it deals normal damage to the weapon to the target Actor and move to any
     * location within the surroundings of this Actor
     *
     * @param actor The actor performing the attack action.
     * @param map The map the actor is on.
     * @return the result of the attack and the new location
     * @see DeathAction
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (!(Utils.getRandomInt(100) <= chanceToHit)) {
            return actor + " misses " + target + ".";
        }
        String result = new AttackAction(target, direction, weapon).execute(actor, map);

        Location here = map.locationOf(actor);
        for (Exit exit : here.getExits()) {
            destination = exit.getDestination();
            if (!destination.containsAnActor()) {
                map.moveActor(actor, destination);
                break;
            }
        }
        return result + "\n" + actor + " moves to (" + destination.x() + ", " + destination.y() + ")";
    }

    /**
     * Describes which target the Actor is attacking with which weapon that performs the skill
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " quicksteps " + target + " at " + direction + " with " + weapon + " and moves away";
    }
}
