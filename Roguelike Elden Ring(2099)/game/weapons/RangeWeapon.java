package game.weapons;

/**
 * An interface for range weapons in the game.
 * Created by:
 * @author Chang Yee Vern
 */
public interface RangeWeapon {

    /**
     * Registers a weapon instance with the RangeWeaponManager.
     * @param weapon the GameWeapon instance to register
     */
    default void registerWeaponInstance(GameWeapon weapon) {
        RangeWeaponManager.getInstance().addRangeWeapon(weapon);
    }
}
