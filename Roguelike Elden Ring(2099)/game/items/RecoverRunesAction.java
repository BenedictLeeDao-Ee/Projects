package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.trade.WalletManager;
import game.actors.Player;

/**
 * An action to pick up runes that goes into the player's wallet instead of inventory
 * Created by:
 * @author Alyssa Ting Sue-Lyn
 */
public class RecoverRunesAction extends PickUpItemAction {

    /**
     * Runes object to be picked up
     * */
    private final Runes rune;

    /**
     * Constructor
     */
    public RecoverRunesAction(Runes rune) {
        super(rune);
        this.rune = rune;
    }

    /**
     * Adds the Runes into the players wallet and displays an appropriate message
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a message if the Actor picks up Runes of a certain value or if the Actor cannot pick up the Runes
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = WalletManager.getInstance().getPlayer();
        if (actor == player) {
            player.getPlayerWallet().addWalletBalance(rune.getValue());
            map.locationOf(player).removeItem(rune);
            rune.togglePortability();
            return player + " has picked up Runes of value: " + rune.getValue() + " Current wallet balance is: " + player.getPlayerWallet().getBalance();
        }
        return actor + " cannot pick up Runes";
    }

    /**
     * Returns a message to pick up the Runes
     * @param actor The actor performing the action.
     * @return a message to pick up the Runes
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " retrieves Runes (value: " + rune.getValue() + ")";
    }
}
