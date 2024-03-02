package game.actors.traders;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.PurchaseAction;
import game.status.Status;
import game.trade.*;

import java.util.ArrayList;

/**
 * Represents a Trader in the game
 * Created by:
 * @author Alyssa Ting Sue-Lyn
 */
public abstract class Trader extends Actor {

    /**
     * Trader's shop that stores a list of Purchasable that can be sold
     */
    private ArrayList<Purchasable> shop;
    /**
     * Trader's shop that stores a list of PurchasableItem that can be sold
     */
    private ArrayList<PurchasableItem> itemShop;
    /**
     * Trader's shop that stores a list of WeaponItem that can be sold
     */
    private ArrayList<WeaponItem> exchangeShop;
    /**
     * Indicates whether a trader accepts {@link game.items.RemembranceOfTheGrafted}
     */
    private boolean acceptRemembrance = false;

    /**
     * Constructor
     */
    public Trader(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        shop = new ArrayList<>();
        exchangeShop = new ArrayList<>();
        itemShop = new ArrayList<>();
        replenishShop();
        WalletManager.getInstance().addTrader(this);
        addCapability(Status.HOSTILE_TO_ENEMY);
    }

    /**
     * Allows the other Actor to purchase items from the trader's shop
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a list of allowable actions that an Actor can perform on this trader
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        // Adds a purchase action to each weapon in the shop
        for (Purchasable b : getShop()) {
            if (b.itself() != null) {
                actions.add(new PurchaseAction(b, b.itself()));
            }
        }

        replenishShop();
        return actions;
    }

    /**
     * At each turn, select a valid action to perform.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return DoNothingAction {@link DoNothingAction}
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * Gets the trader's shop
     * @return trader's shop
     */
    public ArrayList<Purchasable> getShop() {
        return this.shop;
    }

    /**
     * Gets the trader's item shop
     * @return trader's item shop
     */
    public ArrayList<PurchasableItem> getItemShop() {
        return this.itemShop;
    }

    /**
     * Gets the trader's exchange shop
     * @return trader's exchange shop
     */
    public ArrayList<WeaponItem> getExchangeShop() {
        return this.exchangeShop;
    }

    /**
     * Adds the weapon that the trader can sell to his shop
     * @param itemToSell weapon that can be sold by the trader
     */
    public void addToShop(Purchasable itemToSell) {
        this.shop.add(itemToSell);
    }

    /**
     * Adds the item that the trader can sell to his shop
     * @param itemToSell item that can be sold by the trader
     */
    public void addToShop(PurchasableItem itemToSell) {
        this.itemShop.add(itemToSell);
    }

    /**
     * Adds the weapons that the trader can exchange with the Player
     * @param weapons weapon that can be exchanged
     */
    public void addToShop(WeaponItem weapons) {
        this.exchangeShop.add(weapons);
    }

    /**
     * Replenishes the shop with new weapons
     */
    public abstract void replenishShop();

    /**
     * Checks if the trader accepts Remembrance of the Grafted
     * @return whether the trader accept Remembrance of the Grafted
     */
    public boolean getAcceptRemembrance() {
        return this.acceptRemembrance;
    }

    /**
     * Changes whether the trader can accept Remembrance of the Grafted or not
     */
    public void toggleAcceptRemembrance() {
        this.acceptRemembrance = !this.acceptRemembrance;
    }

    /**
     * Returns itself of type Actor
     * @return itself of type Actor
     */
    public Actor itself() {
        return this;
    }
}
