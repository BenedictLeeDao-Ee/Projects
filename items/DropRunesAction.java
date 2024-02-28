package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;
import game.trade.WalletManager;

/**
 * An action that allows Actor to drop runes
 * Created by:
 * @author Alyssa Ting Sue-Lyn
 */
public class DropRunesAction extends DropItemAction {
    /**
     * Item to be dropped
     */
    private final Item item;

    /**
     * Constructor.
     *
     * @param item the item to drop
     */
    public DropRunesAction(Item item) {
        super(item);
        this.item = item;
    }

    /**
     * Drop the item
     * @param actor The actor performing the action
     * @param map The map the actor is on
     * @return a message saying which actor dropped runes
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = WalletManager.getInstance().getPlayer();
        if (actor == player) {
            actor.removeItemFromInventory(item);
            map.at(player.getPlayerPrevX(), player.getPlayerPrevY()).addItem(item);
        }
        return menuDescription(actor);
    }
}
