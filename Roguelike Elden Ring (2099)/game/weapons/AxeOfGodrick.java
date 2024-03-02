package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.SpinningAction;
import game.status.Status;
import game.trade.Exchangeable;

/**
 * Represents Axe of Godrick that the boss, Godrick the Grafted wields
 * Created by:
 * @author Alyssa Ting Sue-Lyn
 */
public class AxeOfGodrick extends GameWeapon implements Exchangeable {

    /**
     * Name of the weapon
     */
    private static final String NAME = "Axe of Godrick";
    /**
     * Display character of the weapon
     */
    private static final char DISPLAY_CHAR = 'T';
    /**
     * Damage dealt by the weapon
     */
    private static final int DAMAGE = 142;
    /**
     * Sound made
     */
    private static final String VERB = "swipes";
    /**
     * Hit rate of the weapon
     */
    private static final int HIT_RATE = 84;
    /**
     * Sell price of the weapon
     */
    private static final int SELL_PRICE = 100;

    /**
     * Constructor.
     */
    public AxeOfGodrick() {
        super(NAME, DISPLAY_CHAR, DAMAGE, VERB, HIT_RATE, SELL_PRICE);
        this.addCapability(Status.HAS_AREA_ATTACK);
    }

    /**
     * Gets the skill of the weapon which is area attack damage
     * @param target target actor
     * @param direction the direction of the attack
     * @return the unique skill of the weapon
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new SpinningAction(target, direction, this);
    }

    /**
     * Returns null
     * @return null
     */
    @Override
    public Item exchangeItem() {
        return null;
    }

    /**
     * Returns itself of type Exchangeable
     * @return itself of type Exchangeable
     */
    @Override
    public Exchangeable exchangeItself() {
        return this;
    }

}
