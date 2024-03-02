package game.trade;

/**
 * Merchant Kale sells to player
 * Created by:
 * @author Alyssa Ting Sue-Lyn
 */
public interface Sellable {
    /**
     * Sets the selling price of the item
     * @param sellPrice selling price
     */
    void setSellPrice(int sellPrice);

    /**
     * Gets the selling price of the item
     * @return selling price
     */
    int getSellPrice();

    /**
     * Returns a Sellable object
     * @return Sellable object
     */
    Sellable sellItself();
}
