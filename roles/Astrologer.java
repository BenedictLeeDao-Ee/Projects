package game.roles;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.weapons.AstrologersStaff;

/**
 * Astrologer role that the player can choose
 * Created by:
 * @author Chang Yee Vern
 */
public class Astrologer implements Role {

    /**
     * Hit points for this role
     * @return an integer representing the hit points
     */
    @Override
    public int hitPoints() {
        return 396;
    }

    /**
     * Weapon that this role carries
     * @return {@link AstrologersStaff}
     */
    @Override
    public WeaponItem weapon() {
        return new AstrologersStaff();
    }
}
