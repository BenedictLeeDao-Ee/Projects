package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.grounds.GoldenFogDoor;


/**
 * A class that allows actors to travel through Golden Fog Doors
 */
public class TravelAction extends Action {
    /**
     * The current Golden Fog Door that the actor is at
     */
    private GoldenFogDoor door;

    /**
     * Constructor
     * @param door The current Golden Fog Door that the actor is at
     */
    public TravelAction(GoldenFogDoor door){
        this.door = door;
    }

    /**
     * When the action is executed, the actor will move to the destination of
     * the Golden Fog Door
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A message saying where the actor has travelled to
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.moveActor(actor,this.door.getTeleportLocations(this.door.getLocation()));
        return menuDescription(actor);
    }

    /**
     * Describes the map being travelled to
     * @param actor The actor performing the action.
     * @return A message saying where the actor has travelled to
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " travels to " + this.door.getMapName(this.door.getLocation());
    }
}
