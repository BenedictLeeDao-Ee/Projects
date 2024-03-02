package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.SellAction;
import game.actors.traders.Trader;
import game.trade.Sellable;
import game.trade.WalletManager;

/**
 * A class that represents a type of weapon that can be sold and reset
 * Created by:
 * @author Chang Yee Vern
 * Modified by: Alyssa Ting Sue-Lyn
 */
public abstract class GameWeapon extends WeaponItem implements Sellable {

    /**
     * Selling price
     */
    private int sellPrice;

    /**
     * SellAction
     */
    SellAction sellAction;

    /**
     * To indicate if a SellAction has been assigned
     */
    private boolean addedSellAction = false;


    /**
     * Constructor.
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     * @param sellPrice   the selling price of this weapon
     */
    public GameWeapon(String name, char displayChar, int damage, String verb, int hitRate, int sellPrice) {
        super(name, displayChar, damage, verb, hitRate);
        this.setSellPrice(sellPrice);
    }

    /**
     * Assigns a {@link SellAction} to itself if there is a merchant nearby
     * @param currentLocation the location of the weapon
     * @param actor the actor that picks up or drops the weapon
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (actor == WalletManager.getInstance().getPlayer()) {
            boolean checker = false;
            // Checks if there is a trader nearby
            for (Exit exit : currentLocation.getExits()) {
                if (exit.getDestination().containsAnActor()) {
                    Actor currentActor = exit.getDestination().getActor();
                    for (Trader trader : WalletManager.getInstance().getTraders()) {
                        if (currentActor == trader.itself()) {
                            if (!addedSellAction) {
                                sellAction = new SellAction(this.sellItself(), this.itself());
                                this.addAction(sellAction);
                            }
                            checker = true;
                            addedSellAction = true;
                        }
                    }
                }
            }
            if (!checker && addedSellAction) {
                this.removeAction(sellAction);
                addedSellAction = false;
            }
        }
    }

    /**
     * Sets the selling price
     * @param sellPrice selling price
     */
    @Override
    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    /**
     * Gets the selling price
     * @return selling price
     */
    @Override
    public int getSellPrice() {
        return this.sellPrice;
    }

    /**
     * Returns a Sellable object
     * @return Sellable object
     */
    @Override
    public Sellable sellItself() {
        return this;
    }

    /**
     * Returns a WeaponItem object
     * @return WeaponItem object
     */
    public WeaponItem itself() {
        return this;
    }

    /**
     * Returns the range of the weapon
     * @return the range of the weapon
     */
    public int weaponRange() {
        return 1;
    }
}
