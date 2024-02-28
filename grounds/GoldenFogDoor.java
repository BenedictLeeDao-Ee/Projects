package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TravelAction;
import game.status.Status;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class that represents a ground that actors can use to travel to other maps
 * Created by:
 * @author Benedict Lee Dao-Ee
 * Modified by: Chang Yee Vern
 */
public class GoldenFogDoor extends Ground {

    /**
     * The display character of Golden Fog Door
     */
    private static final char DISPLAY_CHAR = 'D';

    /**
     * The location of the Golden Fog Door
     */
    private Location location;;
    /**
     * Maps the location of the Golden Fog Door to its destination
     */
    private final Map<Location,Location> teleportLocations;
    /**
     * Maps the location of the Golden Fog Door to the name of the destination's map
     */
    private final Map<Location,String> mapName;

    /**
     * Constructor.
     *
     */
    public GoldenFogDoor() {
        super(DISPLAY_CHAR);
        teleportLocations = new HashMap<>();
        mapName = new HashMap<>();
        addCapability(Status.TRAVELLABLE);
    }

    /**
     * Shows the actor actions that it can perform
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = new ActionList();
        List<Exit> exits = location.getExits();
        for(Exit exit : exits) {
            if ((location.containsAnActor() || exit.getDestination().containsAnActor())) {
                actionList.add(new TravelAction(this));
                break;
            }
        }
        return actionList;
    }

    /**
     * Sets the location of the Golden Fog Door
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        setLocation(location);
    }

    /**
     * Gets the location of the Golden Fog Door
     * @return the location of the Golden Fog Door
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Sets the location of the Golden Fog Door
     * @param location location of the Golden Fog Door
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Gets the destination of the Golden Fog Door
     * @param from the location that the actor is at
     * @return the destination of the door that the actor is at
     */
    public Location getTeleportLocations(Location from) {
        return teleportLocations.get(from);
    }

    /**
     * Loops through the whole map and checks for Golden Fog Doors
     * then stores their locations
     * @param map the map being checked
     * @param destination the destination of the door
     */
    public void setTeleportLocations(GameMap map, Location destination) {
        for (int x: map.getXRange()) {
            for (int y: map.getYRange()) {
                if (map.at(x,y).getGround().hasCapability(Status.TRAVELLABLE)) {
                    teleportLocations.put(map.at(x,y),destination);
                }
            }
        }
    }

    /**
     * Sets the name of the destination's map based on the Golden Fog Door's location
     * @param location location of the Golden Fog Door
     * @param name name of the destination's map
     */
    public void setMapName(Location location,String name) {
        mapName.put(location,name);
    }

    /**
     * Gets the name of the destination's map
     * @param location location of the Golden Fog Door
     * @return the name of the destination's map
     */
    public String getMapName(Location location) {
        return mapName.get(location);
    }
}
