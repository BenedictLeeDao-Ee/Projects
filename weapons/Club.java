package game.weapons;

import game.trade.Purchasable;

/**
 * A hammer type weapon that can be used to attack the enemy.
 * It deals 103 damage with 80% hit rate
 * Created by:
 * @author Chang Yee Vern
 * Modified by: Alyssa Ting Sue-Lyn
 *
 */
public class Club extends GameWeapon implements Purchasable {

    /**
     * Name of the weapon
     */
    private static final String NAME = "Club";
    /**
     * Display character of the weapon
     */
    private static final char DISPLAY_CHAR = '!';
    /**
     * Damage dealt by the weapon
     */
    private static final int DAMAGE = 103;
    /**
     * Sound made
     */
    private static final String VERB = "bonks";
    /**
     * Hit rate of the weapon
     */
    private static final int HIT_RATE = 80;
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
    public Club() {
        super(NAME, DISPLAY_CHAR, DAMAGE, VERB, HIT_RATE, SELL_PRICE);
        setBuyPrice(600);
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
