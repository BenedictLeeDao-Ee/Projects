package game.roles;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.weapons.Uchigatana;

/**
 * Samurai role that the player can choose
 * Created by:
 * @author Chang Yee Vern
 */
public class Samurai implements Role {

    /**
     * Hit points for this role
     * @return an integer representing the hit points
     */
    @Override
    public int hitPoints() {
        return 455;
    }

    /**
     * Weapon that this role carries
     * @return {@link Uchigatana}
     */
    @Override
    public WeaponItem weapon() {
        return new Uchigatana();
    }
}
