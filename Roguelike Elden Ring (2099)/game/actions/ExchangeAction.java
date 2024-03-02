package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.trade.Exchangeable;
import game.trade.WalletManager;

/**
 * An action to exchange an Exchangeable item
 * @author Alyssa Ting Sue-Lyn
 */
public class ExchangeAction extends Action {

    /**
     * Exchangeable item
     */
    private Exchangeable item;
    /**
     * Exchangeable weapon
     */
    private WeaponItem weapon;

    /**
     * Constructor
     * @param item item to be exchanged
     * @param weapon weapon to be exchanged
     */
    public ExchangeAction(Exchangeable item, WeaponItem weapon) {
        setItem(item);
        setWeapon(weapon);
    }

    /**
     * Performs the exchange by removing the exchangeable item and adding the exchangeable weapon into the Actor's weapon inventory
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a message that says which weapon the Actor exchange the item for
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor == WalletManager.getInstance().getPlayer()) {
            actor.removeItemFromInventory(item.exchangeItem());
            actor.addWeaponToInventory(weapon);
        }
        return menuDescription(actor);
    }

    /**
     * Returns a message that says which weapon the Actor exchanges the item for
     * @param actor The actor performing the action.
     * @return A message that says which weapon the Actor exchanges the item for
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " exchanges " + item + " for " + weapon;
    }

    /**
     * Sets the Exchangeable item
     * @param item Exchangeable item to be set
     */
    public void setItem(Exchangeable item) {
        this.item = item;
    }

    /**
     * Sets the Exchangeable weapon
     * @param weapon Exchangeable weapon to be set
     */
    public void setWeapon(WeaponItem weapon) {
        this.weapon = weapon;
    }
}
