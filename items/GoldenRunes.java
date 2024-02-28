package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.actors.Player;
import game.trade.WalletManager;
import game.utils.Utils;

/**
 * Represent the Golden Runes
 * Created by:
 * @author Alyssa Ting Sue-Lyn
 */
public class GoldenRunes extends Item implements Consumables {
    /**
     * Maximum usage for the GoldenRunes
     */
    private static final int STARTING_COUNTER = 1;
    /**
     * Runes that can be received from the GoldenRunes
     */
    private int runesAmount;
    /**
     * Counter for usages already used
     */
    private int consumeCount;
    /**
     * ConsumeAction that has been assigned to this GoldenRunes
     */
    private ConsumeAction action;
    /**
     * To indicate whether a ConsumeAction has been assigned to this GoldenRunes
     */
    private boolean addedAction = false;


    /**
     * Constructor.
     */
    public GoldenRunes() {
        super("Golden Runes", '*', true);
        this.runesAmount = Utils.getRandomInt(200, 10000);
        setCounter(STARTING_COUNTER);
    }

    /**
     * Adds a ConsumeAction if an Actor is currently holding this GoldenRunes
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (actor != null) {
            if (!addedAction) {
                action = new ConsumeAction(this);
                this.addAction(action);
                addedAction = true;
            }
        }
    }

    /**
     * Removes the ConsumeAction if no Actor is currently holding this GoldenRunes
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        if (addedAction) {
            this.removeAction(action);
            addedAction = false;
        }
    }

    /**
     * Gets how charges the consumable has left
     * @return The number of times the player can consume the consumable
     */
    @Override
    public int getCounter() {
        return consumeCount;
    }

    /**
     * Sets how charges the consumable has left
     * @param counter The number of times the player can consume the consumable
     */
    @Override
    public void setCounter(int counter) {
        this.consumeCount = counter;
    }

    /**
     * Adds runes into the Player's wallet
     * @param actor The actor consuming the consumable
     */
    @Override
    public void consumeEffect(Actor actor) {
        Player player = WalletManager.getInstance().getPlayer();
        if (actor == player) {
            player.getPlayerWallet().addWalletBalance(runesAmount);
            this.removeAction(action);
            player.removeItemFromInventory(this);
        }
        consumeCount -= 1;
    }

    /**
     * Check if there are anymore charges left
     * @return Boolean value to tell you if you have anymore charges left
     */
    @Override
    public boolean counterCheck() {
        return consumeCount != 0;
    }

    /**
     * Gets the maximum number of usages
     * @return a number representing the maximum number of usages
     */
    @Override
    public int getMaxCounter() {
        return STARTING_COUNTER;
    }
}
