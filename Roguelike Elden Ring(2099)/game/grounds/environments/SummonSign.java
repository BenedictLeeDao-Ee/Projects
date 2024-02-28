package game.grounds.environments;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.guests.SpawnAction;

import java.util.List;

/**
 * An Environment in Elden Ring that can summon a guest form Another Realm
 * Created by:
 * @author Chang Yee Vern
 */
public class SummonSign extends Ground {

    /**
     * The display character of PuddleOfWater
     */
    private static final char DISPLAY_CHAR = '=';

    /**
     * Constructor.
     */
    public SummonSign() {
        super(DISPLAY_CHAR);
    }

    /**
     * Returns the allowable actions for the actor at the specified location and direction.
     * If the location or its neighboring locations contain an actor, it allows the SpawnAction to be performed.
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return the list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = new ActionList();
        List<Exit> exits = location.getExits();
        for(Exit exit:exits) {
            if((location.containsAnActor() || exit.getDestination().containsAnActor())) {
                actionList.add(new SpawnAction(location));
                break;
            }
        }
        return actionList;
    }
}
