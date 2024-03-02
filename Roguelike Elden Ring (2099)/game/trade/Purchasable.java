package game.trade;

import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * An interface for weapons that can be purchased
 * Created by:
 * @author Alyssa Ting Sue-Lyn
 */
public interface Purchasable {
    /**
     * Sets the buying price
     * @param buyPrice the buying price
     */
    void setBuyPrice(int buyPrice);

    /**
     * Get the buying price
     * @return buying price
     */
    int getBuyPrice();

    /**
     * Returns a WeaponItem object
     * @return WeaponItem object
     */
    WeaponItem itself();
}
