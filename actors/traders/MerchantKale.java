package game.actors.traders;

import game.weapons.Club;
import game.weapons.GreatKnife;
import game.weapons.Scimitar;
import game.weapons.Uchigatana;

/**
 * A merchant that allows players to purchase and sell certain items or weapons
 * @author Alyssa Ting Sue-Lyn
 * Modified by: Chang Yee Vern
 */
public class MerchantKale extends Trader {
    /**
     * Merchant Kale's display character
     */
    private static final char DISPLAY_CHAR = 'K';

    /**
     * Constructor
     */
    public MerchantKale() {
        super("Merchant Kale", DISPLAY_CHAR, 999999999);
    }

    /**
     * Replenishes the shop with new weapons
     */
    @Override
    public void replenishShop() {
        getShop().clear();
        addToShop(new Uchigatana().purchaseItself());
        addToShop(new GreatKnife().purchaseItself());
        addToShop(new Club().purchaseItself());
        addToShop(new Scimitar().purchaseItself());
    }
}
