package game.actors.guests;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.roles.Role;
import game.roles.RoleManager;
import game.utils.Utils;

/**
 * An action to spawn a guest on the specified location.
 * Created by:
 * @author Chang Yee Vern
 *
 */
public class SpawnAction extends Action {

    /**
     * The location to spawn a guest
     */
    private Location environment;
    /**
     * The chance to spawn a guest
     */
    private static final int CHANCE_TO_SPAWN = 50;

    /**
     * Constructor
     * @param location the location to spawn the guest
     */
    public SpawnAction(Location location) {
        this.environment = location;
    }

    /**
     * Executes the spawn action by adding the guest actor to the map.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string describing the action's execution
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location spawnLocation = getSpawnLocation();
        Actor guest = getSpawnActor();
        map.addActor(guest, spawnLocation);
        return menuDescription(actor) + "\n" + guest + " has been summon!!!";
    }

    /**
     * Selects and returns the actor to be spawned based on a random chance.
     * @return the actor to be spawned
     */
    private Actor getSpawnActor() {
        Role role = selectRole();
        return Utils.getRandomInt(100) <= CHANCE_TO_SPAWN ? new Ally(role) : new Invader(role);
    }

    /**
     * Selects and returns a random combat role for the spawned actor.
     * @return a random combat role
     */
    private Role selectRole() {
        char[] chars = {'s', 'b', 'w', 'a'};
        char randomChar = chars[Utils.getRandomInt(chars.length)];
        return RoleManager.selectCombatArchetypes(randomChar);
    }

    /**
     * Returns the location where the guest should be spawned. If the environment already contains an actor,
     * it searches for an empty adjacent location to spawn the guest.
     * @return the spawn location
     */
    private Location getSpawnLocation() {
        if (environment.containsAnActor()) {
            for (Exit exit : environment.getExits()) {
                Location destination = exit.getDestination();
                if (!destination.containsAnActor()) {
                    return destination;
                }
            }
        }
        return environment;
    }

    /**
     * Describes which guest will be summoned from Another Realm
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " summons a guest from Another Realm";
    }
}