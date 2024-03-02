package game.actors.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.status.Status;
import game.utils.Utils;

/**
 * BEHOLD, DOG!
 *
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Chang Yee Vern, Alyssa Ting Sue-Lyn
 *
 */
public class GiantDog extends Canine {

    /**
     * The hit point of GiantDog
     */
    private static final int HIT_POINT = 693;
    /**
     * The display character of GiantDog
     */
    private static final char DISPLAY_CHAR = 'G';
    /**
     * The display character of GiantDog
     */
    private static final int DAMAGE = 314;
    /**
     * The hit rate of GiantDog
     */
    private static final int HIT_RATE = 90;

    /**
     * Constructor
     */
    public GiantDog() {
        super("Giant Dog", DISPLAY_CHAR, HIT_POINT, Utils.getRandomInt(313, 1808));
        this.addCapability(Status.HAS_AREA_ATTACK);
    }

    /**
     * Creates and returns an intrinsic weapon.
     * By default, the GiantDog 'slams' for 314 damage.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(DAMAGE, "slams", HIT_RATE);
    }
}
