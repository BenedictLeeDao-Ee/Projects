package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import game.actions.UnsheatheAction;
import game.trade.Purchasable;

/**
 * A katana type weapon that can be used to attack the enemy.
 * It deals 115 damage with 80% hit rate
 * This weapon allows the user to perform "Unsheathe"
 * Created by:
 * @author Chang Yee Vern
 * Modified by: Alyssa Ting Sue-Lyn
 *
 */
public class Uchigatana extends GameWeapon implements Purchasable {
    /**
     * Name of the weapon
     */
    private static final String NAME = "Uchigatana";
    /**
     * Display character of the weapon
     */
    private static final char DISPLAY_CHAR = ')';
    /**
     * Damage dealt by the weapon
     */
    private static final int DAMAGE = 115;
    /**
     * Sound made
     */
    private static final String VERB = "slashes";
    /**
     * Hit rate of the weapon
     */
    private static final int HIT_RATE = 80;
    /**
     * Sell price of the weapon
     */
    private static final int SELL_PRICE = 500;
    /**
     * The buy price of this weapon
     */
    private int buyPrice;

    /**
     * Constructor
     */
    public Uchigatana() {
        super(NAME, DISPLAY_CHAR, DAMAGE, VERB, HIT_RATE, SELL_PRICE);
        setBuyPrice(5000);
    }

    /**
     * An abstract method that returns the skill of the weapon.
     * @param target target actor
     * @param direction the direction of the attack
     * @return the unique skill of the weapon
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new UnsheatheAction(target, direction, this, 60);
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
