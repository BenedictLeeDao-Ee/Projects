package game.roles;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.weapons.Club;

/**
 * Wretch role that the player can choose
 * Created by:
 * @author Chang Yee Vern
 */
public class Wretch implements Role {
    /**
     * Hit points for this role
     * @return an integer representing the hit points
     */
    @Override
    public int hitPoints() {
        return 414;
    }

    /**
     * Weapon that this role carries
     * @return {@link Club}
     */
    @Override
    public WeaponItem weapon() {
        return new Club();
    }
}
