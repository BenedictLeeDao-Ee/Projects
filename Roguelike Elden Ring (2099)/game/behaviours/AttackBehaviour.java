package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actions.AreaAttackAction;
import game.actions.AttackAction;
import game.status.Status;
import game.utils.Utils;
import game.weapons.GameWeapon;
import game.weapons.RangeWeaponManager;

import java.util.List;

/**
 * A behaviour that enables an actor to perform an attack action against a target actor.
 * Created by:
 * @author Chang Yee Vern
 *
 */
public class AttackBehaviour implements Behaviour {

    /**
     * The Actor enacting the behaviour
     */
    private Actor target;

    /**
     * Constructor
     * @param target The Actor that is to be attacked
     */
    public AttackBehaviour(Actor target) {
        this.target = target;
    }

    /**
     *
     * @param actor the Actor who will perform the attack
     * @param map the game map on which the actor is located
     * @return the action that the actor should perform to attack the target, or null if no suitable action is found
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        // if actor's exit contains target
        Location there = map.locationOf(target),
                here = map.locationOf(actor);
        for (Exit exit : here.getExits()) {
            if (exit.getDestination().equals(there)) {
                Weapon weapon = Utils.getSingleWeapon(actor);
                if (actor.hasCapability(Status.HAS_AREA_ATTACK) && Utils.getRandomInt(100) <= 50) {  // may also decide to perform single attack
                    return new AreaAttackAction(target, exit.getName(), weapon);
                }  else {
                    return new AttackAction(target, exit.getName(), weapon);
                }
            }
        }
        if (actor.hasCapability(Status.HAS_RANGE_ATTACK)) {
            for (Weapon weapon: actor.getWeaponInventory()) {
                GameWeapon gameWeapon = RangeWeaponManager.getInstance().getRangeWeapon(weapon);
                if (gameWeapon == null) {
                    continue;
                }
                here = map.locationOf(actor);
                List<int[]> tiles = Utils.getRangeWeaponTiles(gameWeapon, here);

                for (int[] coordinates: tiles) {
                    int x = coordinates[0], y = coordinates[1];
                    Location location = map.at(x, y);
                    if (location.containsAnActor()) {
                        target = map.getActorAt(location);
                        return new AttackAction(target, "(" + x +", " + y + ")", gameWeapon);
                    }
                }
            }
        }
        return null;
    }
}
