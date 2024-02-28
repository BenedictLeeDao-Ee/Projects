package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.SellAction;
import game.actors.traders.Trader;
import game.trade.Exchangeable;
import game.trade.Sellable;
import game.trade.WalletManager;

/**
 * Represents Remembrance of the Grafted
 * Created by:
 * @author Alyssa Ting Sue-Lyn
 */
public class RemembranceOfTheGrafted extends Item implements Sellable, Exchangeable {
    /**
     * Selling price
     */
    private int sellPrice;
    /**
     * SellAction assigned to this RemembranceOfTheGrafted
     */
    SellAction sellAction;
    /**
     * Boolean that represents if a SellAction has been assigned to this RemembranceOfTheGrafted
     */
    private boolean addedSellAction = false;


    /**
     * Constructor.
     */
    public RemembranceOfTheGrafted() {
        super("Remembrance of the Grafted", 'O', true);
        setSellPrice(20000);
        WalletManager.getInstance().addRemembranceOfTheGrafted(this);
    }

    /**
     * Assigns a SellAction if there is a trader nearby that accepts RemembranceOfTheGrafted
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (actor == WalletManager.getInstance().getPlayer()) {
            boolean checker = false;
            for (Exit exit : currentLocation.getExits()) {
                if (exit.getDestination().containsAnActor()) {
                    Actor currentActor = exit.getDestination().getActor();
                    for (Trader trader : WalletManager.getInstance().getTraders()) {
                        if (currentActor == trader.itself() && trader.getAcceptRemembrance()) {
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
     * Returns an Exchangeable object
     * @return Exchangeable object
     */
    @Override
    public Exchangeable exchangeItself() {
        return this;
    }

    /**
     * Returns an Item object
     * @return Item object
     */
    @Override
    public Item exchangeItem() {
        return this;
    }

    /**
     * Returns an Item object
     * @return Item object
     */
    public Item itself() {
        return this;
    }
}
