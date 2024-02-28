package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.trade.Purchasable;
import game.trade.PurchasableItem;
import game.trade.Wallet;
import game.trade.WalletManager;
import game.actors.Player;

/**
 * An action to purchase a Buyable item
 * Created by:
 * @author Alyssa Ting Sue-Lyn
 * Modified by: Chang Yee Vern
 *
 */
public class PurchaseAction extends Action {
    /**
     * Purchase price of the weapon
     */
    private int buyPrice;
    /**
     * Weapon item
     */
    private Purchasable buyWeapon;
    /**
     * Item of type PurchasableItem
     */
    private PurchasableItem buyItem;
    /**
     * Weapon of type WeaponItem
     */
    private WeaponItem weapon;
    /**
     * Item
     */
    private Item item;

    /**
     * Constructor
     * @param item Purchasable item to be set
     * @param weapon Purchasable weapon to be set
     */
    public PurchaseAction(Purchasable item, WeaponItem weapon) {
        this.setBuyItem(item);
        this.setBuyPrice(item);
        this.setWeapon(weapon);
    }

    /**
     * Constructor
     * @param item Purchasable item to be set
     * @param purchaseItem Purchasable item to be set
     */
    public PurchaseAction(PurchasableItem item, Item purchaseItem) {
        this.setBuyItem(item);
        this.setBuyPrice(item);
        this.setItem(purchaseItem);
    }

    /**
     * Adds the weapon into the Player's inventory and deducts runes from the player's wallet if the player has enough money
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a message that says what the Player bought
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = WalletManager.getInstance().getPlayer();
        if (actor == player) {
            Wallet wallet = player.getPlayerWallet();
            int balance = wallet.getBalance();

            // Checks if the player has enough runes
            if (balance < buyPrice) {
                return actor + " does not have enough runes to purchase " + (buyWeapon != null ? buyWeapon : buyItem) + "!";
            } else {
                wallet.deductWalletBalance(buyPrice);
                if (weapon != null) {
                    actor.addWeaponToInventory(weapon);
                } else if (item != null) {
                    actor.addItemToInventory(item);
                }
            }
            return actor + " bought " + buyItem;
        }
        return actor + " is a NPC. Unable to buy";
    }

    /**
     * A message that describes who bought what for how much
     * @param actor The actor performing the action.
     * @return A message that describes who bought what for how much
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " purchases " + (buyWeapon != null ? buyWeapon : buyItem) + " for " + buyPrice;
    }

    /**
     * Sets the Purchasable item
     * @param item the Purchasable item to be set
     */
    private void setBuyItem(Purchasable item) {
        this.buyWeapon = item;
    }

    /**
     * Sets the PurchasableItem item
     * @param item the PurchasableItem item to be set
     */
    private void setBuyItem(PurchasableItem item) {
        this.buyItem = item;
    }

    /**
     * Sets the buying price of the Purchasable item
     * @param item the Purchasable item to be set
     */
    private void setBuyPrice(Purchasable item) {
        this.buyPrice = item.getBuyPrice();
    }

    /**
     * Sets the buying price of the PurchasableItem
     * @param item the PurchasableItem to be set
     */
    private void setBuyPrice(PurchasableItem item) {
        this.buyPrice = item.getBuyPrice();
    }

    /**
     * Sets the weapon
     * @param weapon weapon to be set
     */
    public void setWeapon(WeaponItem weapon) {
        this.weapon = weapon;
    }

    /**
     * Sets the item
     * @param item item to be set
     */
    public void setItem(Item item) {
        this.item = item;
    }
}
