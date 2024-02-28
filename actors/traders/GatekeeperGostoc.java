package game.actors.traders;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.PurchaseAction;
import game.items.GoldenSeed;
import game.trade.PurchasableItem;

/**
 * Represents the Trader Gatekeeper Gostoc
 * Created by:
 * @author Alyssa Ting Sue-Lyn
 */
public class GatekeeperGostoc extends Trader {

    /**
     * The display character of GatekeeperGostoc
     */
    private static final char DISPLAY_CHAR = 'Q';
    /**
     * The hit point of GatekeeperGostoc
     */
    private static final int HIT_POINT = 999999999;


    /**
     * Constructor
     */
    public GatekeeperGostoc() {
        super("Gatekeeper Gostoc", DISPLAY_CHAR, HIT_POINT);
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
        for (PurchasableItem b : getItemShop()) {
            if (b.itself() != null) {
                actions.add(new PurchaseAction(b, b.itself()));
            }
        }

        replenishShop();
        return actions;
    }

    /**
     * Replenishes the shop with new items
     */
    @Override
    public void replenishShop() {
        getItemShop().clear();
        addToShop(new GoldenSeed());
    }
}
