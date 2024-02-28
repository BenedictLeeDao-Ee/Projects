package game.actors.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.utils.Utils;

/**
 * BEHOLD, CRAB!
 *
 * Created by:
 * @author Chang Yee Vern
 * Modified by: Alyssa Ting Sue-Lyn
 *
 */
public class GiantCrab extends Crustaceans {

    /**
     * The hit point of GiantCrab
     */
    private static final int HIT_POINT = 407;
    /**
     * The display character of GiantCrab
     */
    private static final char DISPLAY_CHAR = 'C';
    /**
     * The display character of GiantCrab
     */
    private static final int DAMAGE = 208;
    /**
     * The hit rate of GiantCrab
     */
    private static final int HIT_RATE = 90;

    /**
     * Constructor.
     */
    public GiantCrab() {
        super("Giant Crab", DISPLAY_CHAR, HIT_POINT, Utils.getRandomInt(318, 4961));
    }

    /**
     * Creates and returns an intrinsic weapon.
     * By default, the GiantCrab 'slams' for 90 damage.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(DAMAGE, "slams", HIT_RATE);
    }
}
