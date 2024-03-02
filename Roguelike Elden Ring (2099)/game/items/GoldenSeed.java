package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;
import game.trade.PurchasableItem;
import game.trade.WalletManager;
import game.weapons.WingedGreathorn;

/**
 * Represents a Golden Seed
 * Created by:
 * @author Alyssa Ting Sue-Lyn
 */
public class GoldenSeed extends Item implements Consumables, PurchasableItem {
    /**
     * Maximum usage for the GoldenSeed
     */
    private static final int STARTING_COUNTER = 1;
    /**
     * The purchasing price
     */
    private int buyPrice;
    /**
     * Counter for usages already used
     */
    private int counter;

    /**
     * Constructor.
     */
    public GoldenSeed() {
        super("Golden Seed", '%', false);
        setCounter(STARTING_COUNTER);
        setBuyPrice(10000);
        this.addAction(new ConsumeAction(this));
    }

    /**
     * Gets how charges the consumable has left
     * @return The number of times the player can consume the consumable
     */
    @Override
    public int getCounter() {
        return counter;
    }

    /**
     * Sets how many charges the consumable has left
     * @param counter The number of times the player can consume the consumable
     */
    @Override
    public void setCounter(int counter) {
        this.counter = counter;
    }

    /**
     * Adds Winded Greathorn into the player's inventory and removes this Golden Seed from the player's inventory
     * @param actor The actor consuming the consumable
     */
    @Override
    public void consumeEffect(Actor actor) {
        WalletManager.getInstance().getPlayer().addWeaponToInventory(new WingedGreathorn());
        actor.removeItemFromInventory(this);
    }

    /**
     * Check if there are anymore charges left
     * @return Boolean value to tell you if you have anymore charges left
     */
    @Override
    public boolean counterCheck() {
        return counter != 0;
    }

    /**
     * Gets the maximum number of usages
     * @return a number representing the maximum number of usages
     */
    @Override
    public int getMaxCounter() {
        return STARTING_COUNTER;
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

    /**
     * Returns itself of type Item
     * @return itself of type Item
     */
    @Override
    public Item itself() {
        return this;
    }
}
