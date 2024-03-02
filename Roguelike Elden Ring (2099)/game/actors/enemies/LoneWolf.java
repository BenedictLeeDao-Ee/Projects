package game.actors.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.utils.Utils;

/**
 * BEHOLD, DOG!
 *
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Chang Yee Vern, Alyssa Ting Sue-Lyn
 *
 */
public class LoneWolf extends Canine {

    /**
     * The hit point of LoneWolf
     */
    private static final int HIT_POINT = 102;
    /**
     * The display character of LoneWolf
     */
    private static final char DISPLAY_CHAR = 'h';
    /**
     * The display character of LoneWolf
     */
    private static final int DAMAGE = 97;
    /**
     * The hit rate of LoneWolf
     */
    private static final int HIT_RATE = 95;


    /**
     * Constructor
     */
    public LoneWolf() {
        super("Lone Wolf", DISPLAY_CHAR, HIT_POINT, Utils.getRandomInt(55, 1470));
    }

    /**
     * Creates and returns an intrinsic weapon.
     * By default, the LoneWolf 'bites' for 97 damage.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(DAMAGE, "bites", HIT_RATE);
    }
}
