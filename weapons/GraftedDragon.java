package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.SpinningAction;
import game.trade.Exchangeable;

/**
 * Represents Grafted Dragon that the boss, Godrick the Grafted wields
 * Created by:
 * @author Alyssa Ting Sue-Lyn
 */
public class GraftedDragon extends GameWeapon implements Exchangeable {
    /**
     * Name of the weapon
     */
    private static final String NAME = "Grafted Dragon";
    /**
     * Display character of the weapon
     */
    private static final char DISPLAY_CHAR = 'N';
    /**
     * Damage dealt by the weapon
     */
    private static final int DAMAGE = 89;
    /**
     * Sound made
     */
    private static final String VERB = "swipes";
    /**
     * Hit rate of the weapon
     */
    private static final int HIT_RATE = 90;
    /**
     * Sell price of the weapon
     */
    private static final int SELL_PRICE = 200;

    /**
     * Constructor.
     */
    public GraftedDragon() {
        super(NAME, DISPLAY_CHAR, DAMAGE, VERB, HIT_RATE, SELL_PRICE);
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
