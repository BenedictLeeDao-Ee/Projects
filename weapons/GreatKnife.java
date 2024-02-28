package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import game.actions.QuickstepAction;
import game.trade.Purchasable;

/**
 * A simple weapon that can be used to attack the enemy.
 * It deals 75 damage with 70% hit rate
 * This weapon allows the user to perform "Quickstep"
 * Created by:
 * @author Chang Yee Vern,
 * Modified by: Alyssa Ting Sue-Lyn
 *
 */
public class GreatKnife extends GameWeapon implements Purchasable {
    /**
     * Name of the weapon
     */
    private static final String NAME = "Great Knife";
    /**
     * Display character of the weapon
     */
    private static final char DISPLAY_CHAR = '/';
    /**
     * Damage dealt by the weapon
     */
    private static final int DAMAGE = 75;
    /**
     * Sound made
     */
    private static final String VERB = "stabs";
    /**
     * Hit rate of the weapon
     */
    private static final int HIT_RATE = 70;
    /**
     * Sell price of the weapon
     */
    private static final int SELL_PRICE = 350;
    /**
     * The buy price of this weapon
     */
    private int buyPrice;

    /**
     * Constructor
     */
    public GreatKnife() {
        super(NAME, DISPLAY_CHAR, DAMAGE, VERB, HIT_RATE, SELL_PRICE);
        setBuyPrice(3500);
    }

    /**
     * An abstract method that returns the skill of the weapon.
     * @param target target actor
     * @param direction the direction of the attack
     * @return the unique skill of the weapon
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new QuickstepAction(target, direction, this, this.chanceToHit());
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
