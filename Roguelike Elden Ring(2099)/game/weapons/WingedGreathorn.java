package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.SpinningAction;
import game.reset.Resettable;
import game.status.Status;

/**
 * Represents Winged Greathorn that the player can obtain after consuming a Golden Seed
 * Created by:
 * @author Alyssa Ting Sue-Lyn
 */
public class WingedGreathorn extends GameWeapon implements Resettable {

    /**
     * Name of the weapon
     */
    private static final String NAME = "Winged Greathorn";
    /**
     * Display character of the weapon
     */
    private static final char DISPLAY_CHAR = 'v';
    /**
     * Damage dealt by the weapon
     */
    private static final int DAMAGE = 2000;
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
    private static final int SELL_PRICE = 0;
    /**
     * Number of usages this weapon has
     */
    private int usage = 5;

    /**
     * Constructor
     */
    public WingedGreathorn() {
        super(NAME, DISPLAY_CHAR, DAMAGE, VERB, HIT_RATE, SELL_PRICE);
        this.addCapability(Status.HAS_AREA_ATTACK);
        setSellPrice(0);
    }

    /**
     * Displays the number of usages left and removes the weapon from the player's inventory if the usages are used up
     * @param currentLocation the location of the weapon
     * @param actor the actor that picks up or drops the weapon
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
        new Display().println("Winged Greathorn has (" + getUsage() + "/5) usages left!");
        if (!checkUsage() || this.hasCapability(Status.RESETTING)) {
            actor.removeWeaponFromInventory(this);
            new Display().println("Winged Greathorn has disappeared!");
        }

        if (checkUsage()) {
            decreaseUsage();
        }
    }

    /**
     * An abstract method that returns the skill of the weapon.
     * @param target target actor
     * @param direction the direction of the attack
     * @return the unique skill of the weapon
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new SpinningAction(target, direction, this);
    }

    /**
     * Decrements the number of usages
     */
    public void decreaseUsage() {
        this.usage -= 1;
    }

    /**
     * Gets the usage
     * @return number that represents the usage left
     */
    public int getUsage() {
        return this.usage;
    }

    /**
     * Checks if the usages have been used up
     * @return boolean true if used up, false otherwise
     */
    public boolean checkUsage() {
        return getUsage() != 0;
    }

    /**
     * Adds the capability of resetting when the game resets
     */
    @Override
    public void reset() {
        this.addCapability(Status.RESETTING);
    }
}
