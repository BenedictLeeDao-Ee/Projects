package game.actors.enemies;

import game.utils.Utils;
import game.weapons.Scimitar;

/**
 * BEHOLD, BONES!
 *
 * Created by:
 * @author Chang Yee Vern
 * Modified by: Alyssa Ting Sue-Lyn
 *
 */
public class SkeletalBandit extends Bones {

    /**
     * The hit point of SkeletalBandit
     */
    private static final int HIT_POINT = 184;
    /**
     * The display character of SkeletalBandit
     */
    private static final char DISPLAY_CHAR = 'b';

    /**
     * Constructor
     */
    public SkeletalBandit() {
        super("Skeletal Bandit", DISPLAY_CHAR, HIT_POINT, Utils.getRandomInt(35, 892));
        this.maxHitPoints = HIT_POINT;
        this.addWeaponToInventory(new Scimitar());
    }
}
