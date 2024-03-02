package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import game.actions.SpinningAction;
import game.trade.Purchasable;
import game.status.Status;

/**
 * A curved weapon that can be used to attack the enemy.
 * It deals 115 damage with 85% hit rate
 * This sword allows the user to attack a single enemy within their
 * surroundings or to perform a spinning attack
 * Created by:
 * @author Chang Yee Vern
 * Modified by: Alyssa Ting Sue-Lyn
 *
 */
public class Scimitar extends GameWeapon implements Purchasable {

    /**
     * Name of the weapon
     */
    private static final String NAME = "Scimitar";
    /**
     * Display character of the weapon
     */
    private static final char DISPLAY_CHAR = 's';
    /**
     * Damage dealt by the weapon
     */
    private static final int DAMAGE = 118;
    /**
     * Sound made
     */
    private static final String VERB = "swings";
    /**
     * Hit rate of the weapon
     */
    private static final int HIT_RATE = 88;
    /**
     * Sell price of the weapon
     */
    private static final int SELL_PRICE = 100;
    /**
     * The buy price of this weapon
     */
    private int buyPrice;
    /**
     * Constructor
     */
    public Scimitar() {
        super(NAME, DISPLAY_CHAR, DAMAGE, VERB, HIT_RATE, SELL_PRICE);
        this.addCapability(Status.HAS_AREA_ATTACK);
        setBuyPrice(600);
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

    /**
     * Set the buy price of this weapon
     * @param buyPrice the buying price
     */
    @Override
    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }

    /**
     * Returns the buy price of this weapon
     * @return an integer representing the buy price of this weapon
     */
    @Override
    public int getBuyPrice() {
        return this.buyPrice;
    }

    public Purchasable purchaseItself() {
        return this;
    }
}
