package game.actors.enemies;

import game.utils.Utils;
import game.weapons.Grossmesser;

/**
 * BEHOLD, SWORDSMAN!
 *
 * Created by:
 * @author Chang Yee Vern
 * Modified by: Alyssa Ting Sue-Lyn
 *
 */
public class HeavySkeletalSwordsman extends Bones {

    /**
     * The hit point of HeavySkeletalSwordsman
     */
    private static final int HIT_POINT = 153;
    /**
     * The display character of HeavySkeletalSwordsman
     */
    private static final char DISPLAY_CHAR = 'q';

    /**
     * Constructor
     */
    public HeavySkeletalSwordsman() {
        super("Heavy Skeletal Swordsman", DISPLAY_CHAR, HIT_POINT, Utils.getRandomInt(35, 892));
        this.maxHitPoints = HIT_POINT;
        this.addWeaponToInventory(new Grossmesser());
    }
}
