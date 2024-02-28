package game.trade;

import edu.monash.fit2099.engine.items.Item;

/**
 * An interface for items that can be purchased
 * Created by:
 * @author Alyssa Ting Sue-Lyn
 */
public interface PurchasableItem {
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
     * Returns a Item object
     * @return Item object
     */
    Item itself();
}
