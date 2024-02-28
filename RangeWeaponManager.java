package game.weapons;

import edu.monash.fit2099.engine.weapons.Weapon;

import java.util.ArrayList;
import java.util.List;

/**
 * A manager class for range weapons in the game.
 *  @author Chang Yee Vern
 */
public class RangeWeaponManager {

    /**
     * One single instance of RangeWeaponManager
     */
    private static RangeWeaponManager instance;
    /**
     * A list of GameWeapon
     */
    private List<GameWeapon> weaponList;

    /**
     * A private constructor
     */
    private RangeWeaponManager() {
        weaponList = new ArrayList<>();
    }

    /**
     * Returns the singleton instance of the RangeWeaponManager.
     * @return the RangeWeaponManager instance
     */
    public static RangeWeaponManager getInstance() {
        if (instance == null) {
            instance = new RangeWeaponManager();
        }
        return instance;
    }

    /**
     * Adds a range weapon to the manager.
     * @param weapon the range weapon to add
     */
    public void addRangeWeapon(GameWeapon weapon) {
        weaponList.add(weapon);
    }

    /**
     * Retrieves the GameWeapon instance associated with the specified Weapon.
     * @param weapon the Weapon to retrieve the GameWeapon for
     * @return the GameWeapon instance, or null if not found
     */
    public GameWeapon getRangeWeapon(Weapon weapon) {
        for (GameWeapon gameWeapon : weaponList) {
            if (weapon == gameWeapon) {
                return gameWeapon;
            }
        }
        return null;
    }
}
