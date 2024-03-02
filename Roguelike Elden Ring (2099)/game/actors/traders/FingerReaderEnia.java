package game.actors.traders;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.ExchangeAction;
import game.items.RemembranceOfTheGrafted;
import game.trade.WalletManager;
import game.weapons.AxeOfGodrick;
import game.weapons.GraftedDragon;

/**
 * A class representing the trader FingerReaderEnia
 * Created by:
 * @author Alyssa Ting Sue-Lyn
 */
public class FingerReaderEnia extends Trader {

    /**
     * Constructor.
     */
    public FingerReaderEnia() {
        super("Finger Reader Enia", 'E', 999999999);
        toggleAcceptRemembrance();
    }

    /**
     * Allows the otherActor to exchange RemembranceOfTheGrafted with an Exchangeable weapon
     * @param otherActor the Actor that might be performing the acton
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a list of allowable actions that an Actor can perform on FingerReaderEnia
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        // Adds a buy action to each weapon in the shop
        for (RemembranceOfTheGrafted remembranceOfTheGrafted : WalletManager.getInstance().getRemembranceOfTheGrafteds()) {
            if (WalletManager.getInstance().getPlayer().getItemInventory().contains(remembranceOfTheGrafted.exchangeItem())) {
                for (WeaponItem e : getExchangeShop()) {
                    actions.add(new ExchangeAction(remembranceOfTheGrafted.exchangeItself(), e));
                }
            }
        }

        replenishShop();
        return actions;
    }

    /**
     * Replenishes the trader's shop with new weapons
     */
    @Override
    public void replenishShop() {
        getExchangeShop().clear();
        addToShop(new AxeOfGodrick());
        addToShop(new GraftedDragon());
    }

}
