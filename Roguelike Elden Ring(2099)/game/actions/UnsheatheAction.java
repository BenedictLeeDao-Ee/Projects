package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.utils.Utils;

/**
 * A unique skill action for Great Knife
 * Created by:
 * @author Chang Yee Vern
 *
 */
public class UnsheatheAction extends Action {

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
     * Chance to hit the target Actor
     */
    private final int chanceToHit;

    /**
     * Constructor.
     *
     * @param target the Actor to attack
     * @param direction the direction where the attack should be performed (only used for display purposes)
     * @param chanceToHit the chance to hit the target Actor
     */
    public UnsheatheAction(Actor target, String direction, Weapon weapon, int chanceToHit) {
        this.target = target;
        this.direction = direction;
        this.weapon = weapon;
        this.chanceToHit = chanceToHit;
    }

    /**
     * When executed, the chance to hit of the weapon that the Actor used is computed to determine whether
     * the actor will hit the target. If so, deal 2x damage to the target.
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
        int damage = weapon.damage() * 2;
        return menuDescription(actor) + "\n" + actor + " " + weapon.verb() + " " + target + " for " + damage + " damage";
    }

    /**
     * Describes which target the Actor is attacking with which weapon that performs the skill
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " unsheathes " + weapon + " at " + direction + " on " + target;
    }
}
