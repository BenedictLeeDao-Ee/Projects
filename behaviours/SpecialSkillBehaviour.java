package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.utils.Utils;

/**
 * A behaviour that enables an actor to perform a special skill action on a target actor.
 * Created by: Chang Yee Vern
 */
public class SpecialSkillBehaviour implements Behaviour {

    /**
     * The target actor on which the special skill action will be performed.
     */
    Actor target;

    /**
     * Constructor
     * @param target the target actor on which the special skill action will be performed
     */
    public  SpecialSkillBehaviour(Actor target) {
        this.target = target;
    }

    /**
     * Retrieves the action for the actor to perform the special skill on the target.
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return the action to perform for the special skill, or null if no suitable action is found
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location there = map.locationOf(target),
                here = map.locationOf(actor);
        for (Exit exit : here.getExits()) {
            if (exit.getDestination().equals(there)) {
                Weapon weapon = Utils.getSingleWeapon(actor);
                return weapon.getSkill(target, exit.getName());
            }
        }
        return null;
    }
}
