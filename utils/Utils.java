package game.utils;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.NumberRange;
import edu.monash.fit2099.engine.weapons.Weapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.weapons.GameWeapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A random number generator
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Chang Yee Vern, Alyssa Ting Sue-Lyn
 *
 */
public class Utils {
    /**
     * Gets a random integer from 0 to the given number
     * @param bound the maximum number
     * @return the random integer
     */
    public static int getRandomInt(int bound) {
        return bound > 0 ? new Random().nextInt(bound) : 0;
    }

    /**
     * Gets a random integer between the lowest given number and highest given number
     * @param lowerBound the lowest number
     * @param upperBound the highest number
     * @return the random number
     */
    public static int getRandomInt(int lowerBound, int upperBound) {
        int range = upperBound - lowerBound + 1;
        return new Random().nextInt(range) + lowerBound;
    }

    /**
     * A method for retrieving all the weapons in the inventory
     * @param actor the actor that is retrieving all the weapons
     * @return a list of weapons from the actor's inventory
     */
    public static List<Weapon> getActorWeapon(Actor actor) {
        List<Weapon> listOfWeapons = new ArrayList<>();
        List<WeaponItem> weapons = actor.getWeaponInventory();
        for (Weapon weapon : weapons) {
            listOfWeapons.add(weapon);
        }
        return listOfWeapons;
    }

    /**
     * If an actor has multiple weapons, they will choose the first weapon in their inventory
     * @param actor the actor that is retrieving all the weapons
     * @return a Weapon object, or null if the Actor has no weapons
     */
    public static Weapon getSingleWeapon(Actor actor) {
        List<WeaponItem> weapons = actor.getWeaponInventory();
        for (Weapon weapon : weapons) {
            return weapon;
        }
        return null;
    }

    /**
     * Retrieves the list of tiles within range of a range weapon.
     * @param gameWeapon the range weapon
     * @param current the current location
     * @return the list of tiles within range
     */
    public static List<int[]> getRangeWeaponTiles(GameWeapon gameWeapon, Location current) {
        List<int[]> result = new ArrayList<>();
        NumberRange xs = new NumberRange(current.x() - gameWeapon.weaponRange(), 2 * gameWeapon.weaponRange()+1);
        NumberRange ys = new NumberRange(current.y() - gameWeapon.weaponRange(), 2 * gameWeapon.weaponRange()+1);
        GameMap map = current.map();
        for (int x: xs) {
            for (int y : ys) {
                try {
                    if (!(Math.abs(current.x() - x) <= 1 && Math.abs(current.y() - y) <= 1) && !(x == current.x() && y == current.y())) {
                        map.at(x, y);
                        result.add(new int[]{x, y});
                    }
                } catch (Exception ignored) {
                }
            }
        }
        return result;
    }
}
