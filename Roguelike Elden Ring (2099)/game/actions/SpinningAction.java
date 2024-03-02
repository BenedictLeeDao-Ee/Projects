package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.Weapon;

/**
 * A unique Action that performs Area Attack to attack other actor.
 * Created by:
 * @author Chang Yee Vern
 *
 */
public class SpinningAction extends AreaAttackAction {

    /**
     * Constructor.
     *
     * @param target    the Actor to attack
     * @param direction the direction where the attack should be performed (only used for display purposes)
     */
    public SpinningAction(Actor target, String direction, Weapon weapon) {
        super(target, direction, weapon);
    }

    /**
     * Describes which target the Actor is attacking with which weapon that performs area attack action
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks anything in the surrounding with " + super.getWeapon();
    }
}
