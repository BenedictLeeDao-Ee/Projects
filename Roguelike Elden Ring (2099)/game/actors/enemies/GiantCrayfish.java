package game.actors.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.utils.Utils;

/**
 * BEHOLD, FISH!
 *
 * Created by:
 * @author Chang Yee Vern
 * Modified by: Alyssa Ting Sue-Lyn
 *
 */
public class GiantCrayfish extends Crustaceans {

    /**
     * The hit point of GiantCrayfish
     */
    private static final int HIT_POINT = 4803;
    /**
     * The display character of GiantCrayfish
     */
    private static final char DISPLAY_CHAR = 'R';
    /**
     * The display character of GiantCrayfish
     */
    private static final int DAMAGE = 527;
    /**
     * The hit rate of GiantCrayfish
     */
    private static final int HIT_RATE = 100;

    /**
     * Constructor
     */
    public GiantCrayfish() {
        super("Giant Crayfish", DISPLAY_CHAR, HIT_POINT, Utils.getRandomInt(500, 2374));
    }

    /**
     * Creates and returns an intrinsic weapon.
     * By default, the GiantCrayfish 'slams' for 100 damage.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(DAMAGE, "slams", HIT_RATE);
    }
}
