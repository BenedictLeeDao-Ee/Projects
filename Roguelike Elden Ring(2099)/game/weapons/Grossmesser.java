package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import game.actions.SpinningAction;
import game.status.Status;

/**
 * A curved sword that can be used to attack the enemy.
 * It deals 118 damage with 88% hit rate
 * This sword allows the user to attack a single enemy within their
 * surroundings or to perform a spinning attack
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Chang Yee Vern, Alyssa Ting Sue-Lyn
 *
 */
public class Grossmesser extends GameWeapon {
    /**
     * Name of the weapon
     */
    private static final String NAME = "Grossmesser";
    /**
     * Display character of the weapon
     */
    private static final char DISPLAY_CHAR = '?';
    /**
     * Damage dealt by the weapon
     */
    private static final int DAMAGE = 115;
    /**
     * Sound made
     */
    private static final String VERB = "swipes";
    /**
     * Hit rate of the weapon
     */
    private static final int HIT_RATE = 85;
    /**
     * Sell price of the weapon
     */
    private static final int SELL_PRICE = 100;

    /**
     * Constructor
     */
    public Grossmesser() {
        super(NAME, DISPLAY_CHAR, DAMAGE, VERB, HIT_RATE, SELL_PRICE);
        this.addCapability(Status.HAS_AREA_ATTACK);
    }

    /**
     * An abstract method that returns the skill of the weapon.
     * @param target target actor
     * @param direction the direction of the attack
     * @return the unique skill of the weapon
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new SpinningAction(target, direction, this);
    }
}