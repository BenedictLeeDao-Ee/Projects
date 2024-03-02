package game.actors.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.utils.Utils;

/**
 * BEHOLD, DOG!
 *
 * Created by:
 * @author Chang Yee Vern
 *
 */
public class Dog extends Castle {

    /**
     * The display character of Dog
     */
    private static final char DISPLAY_CHAR = 'a';
    /**
     * The hit point of Dog
     */
    private static final int HIT_POINT = 104;
    /**
     * The display character of Dog
     */
    private static final int DAMAGE = 101;
    /**
     * The hit rate of Dog
     */
    private static final int HIT_RATE = 93;
    /**
     * Constructor.
     */
    public Dog() {
        super("Dog", DISPLAY_CHAR, HIT_POINT, Utils.getRandomInt(52, 1390));
    }

    /**
     * Creates and returns an intrinsic weapon.
     * By default, the Dog 'bites' for 101 damage.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(DAMAGE, "bites", HIT_RATE);
    }
}
