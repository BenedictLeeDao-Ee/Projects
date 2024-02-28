package game.actors.enemies;

import game.utils.Utils;
import game.weapons.HeavyCrossbow;

/**
 * BEHOLD, GODRICK!
 *
 * Created by:
 * @author Chnag Yee Vern
 * Modified by: Alyssa Ting Sue-Lyn
 *
 */
public class GodrickSoldier extends Castle {

    /**
     * The display character of GodrickSoldier
     */
    private static final char DISPLAY_CHAR = 'p';
    /**
     * The hit point of GodrickSoldier
     */
    private static final int HIT_POINT = 198;

    /**
     * Constructor.
    */
    public GodrickSoldier() {
        super("Godrick Soldier", DISPLAY_CHAR, HIT_POINT, Utils.getRandomInt(38, 70));
        this.addWeaponToInventory(new HeavyCrossbow());
    }
}
