package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;

import java.util.Random;

/**
 * An action to perform an attack that affects a certain area or group of targets, rather than a single target
 * Created by:
 * @author Chang Yee Vern
 * Modified by: Alyssa Ting Sue-Lyn
 *
 */
public class AreaAttackAction extends Action {

    /**
     * The Actors that is to be attacked
     */
    private Actor target;

    /**
     * The direction of incoming attack.
     */
    private String direction;

    /**
     * Random number generator
     */
    private Random rand = new Random();

    /**
     * Weapon used for the attack
     */
    private Weapon weapon;

    /**
     * Constructor.
     *
     * @param target the Actor to attack
     * @param direction the direction where the attack should be performed (only used for display purposes)
     */
    public AreaAttackAction(Actor target, String direction, Weapon weapon) {
        this.target = target;
        this.direction = direction;
        this.weapon = weapon;
    }

    /**
     * Gets the weapon
     * @return the weapon
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * When executed, check the surrounding if there is an actor exist. For each exit that contains an actor, the
     * chance to hit of the weapon that the Actor used is computed to determine whether the actor will hit the
     * target. If so, deal damage to the target and determine whether the target is killed.
     *
     * @param actor The actor performing the area attack action.
     * @param map The map the actor is on.
     * @return the result of the attack, e.g. whether the target is killed, etc.
     * @see DeathAction
     */
    public String execute(Actor actor, GameMap map) {
        String result = actor + " attacks their surrounding!\n";
        Location here = map.locationOf(actor);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor()) {
                if (weapon == null) {
                    weapon = actor.getIntrinsicWeapon();
                }
                if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
                    result += actor + " misses " + target + ".\n";
                }

                target = destination.getActor();  // reset to new target
                int damage = weapon.damage();
                result += actor + " " + weapon.verb() + " " + target + " for " + damage + " area damage.\n";
                target.hurt(damage);
                if (!target.isConscious()) {
                    result += new DeathAction(actor).execute(target, map);
                }
            }
        }
        return result;
    }

    /**
     * Describes which target the actor is attacking with which weapon
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " at " + direction + " with " + (weapon != null ? weapon : "Intrinsic Weapon");
    }
}
