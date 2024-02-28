package game.weapons;

import game.status.Status;
import game.trade.Purchasable;

/**
 * A range weapon that can be used to attack the enemy.
 * It deals 274 damage with 50% hit rate
 * Created by:
 * @author Chang Yee Vern
 *
 */
public class AstrologersStaff extends GameWeapon implements Purchasable, RangeWeapon {
    /**
     * Name of the weapon
     */
    private static final String NAME = "Astrologer's Staff";
    /**
     * Display character of the weapon
     */
    private static final char DISPLAY_CHAR = 'f';
    /**
     * Damage dealt by the weapon
     */
    private static final int DAMAGE = 274;
    /**
     * Sound made
     */
    private static final String VERB = "hits";
    /**
     * Hit rate of the weapon
     */
    private static final int HIT_RATE = 50;
    /**
     * Sell price of the weapon
     */
    private static final int SELL_PRICE = 100;
    /**
     * The buy price of this weapon
     */
    private int buyPrice;

    /**
     * Constructor.
     */
    public AstrologersStaff() {
        super(NAME, DISPLAY_CHAR, DAMAGE, VERB, HIT_RATE, SELL_PRICE);
        addCapability(Status.HAS_RANGE_ATTACK);
        setBuyPrice(800);
        registerWeaponInstance(this);
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
        return buyPrice;
    }

    /**
     * Returns the range of the weapon.
     * @return the range of the weapon
     */
    @Override
    public int weaponRange() {
        return 3;
    }
}
