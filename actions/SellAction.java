package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.trade.WalletManager;
import game.actors.Player;
import game.trade.Sellable;

/**
 * An action to sell a Sellable item
 * Created by:
 * @author Alyssa Ting Sue-Lyn
 */
public class SellAction extends Action {
    /**
     * Selling price for the current Sellable
      */
    private int sellPrice;
    /**
     * Sellable object
     */
    private Sellable sellItem;
    /**
     * WeaponItem object
      */
    private WeaponItem weapon;
    /**
     * Item object
     */
    private Item item;

    /**
     * Constructor
     * @param item the Sellable item to be sold
     * @param weapon the WeaponItem to be sold that is equivalent to the Sellable item
     */
    public SellAction(Sellable item, WeaponItem weapon) {
        this.setSellPrice(item);
        this.setSellItem(item);
        this.setWeapon(weapon);
    }

    /**
     * Constructor
     * @param item
     * @param sellItem
     */
    public SellAction(Sellable item, Item sellItem) {
        this.setSellPrice(item);
        this.setSellItem(item);
        this.setItem(sellItem);
    }

    /**
     * Removes the WeaponItem from the Actor's weapon inventory, adds the value of the selling price of the weapon to the player's wallet and provides an appropriate message
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a message that says what the Actor sold
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Gets the player
        Player player = WalletManager.getInstance().getPlayer();

        // Adds the selling price into the Player's wallet
        player.getPlayerWallet().addWalletBalance(sellPrice);
        // Remove the sold weapon from the Actor's weapon inventory
        if (weapon != null) {
            player.removeWeaponFromInventory(weapon);
        } else if (item != null) {
            player.removeItemFromInventory(item);
        }
        return menuDescription(actor);
    }

    /**
     * A message that says which actor sells what for how much
     * @param actor The actor performing the action.
     * @return a message that says which actor sells what for how much
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " sells " + sellItem + " for " + sellPrice;
    }

    /**
     * Sets the selling price of the Sellable item
     * @param item the Sellable item to be set
     */
    private void setSellPrice(Sellable item) {
        this.sellPrice = item.getSellPrice();
    }

    /**
     * Sets the current Sellable item
     * @param item the Sellable item to be set
     */
    private void setSellItem(Sellable item) {
        this.sellItem = item;
    }

    /**
     * Sets the current WeaponItem item
     * @param weapon the WeaponItem to be set
     */
    private void setWeapon(WeaponItem weapon) {
        this.weapon = weapon;
    }

    /**
     * Sets the current Item
     * @param item the Item to be set
     */
    public void setItem(Item item) {
        this.item = item;
    }
}
