package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.grounds.environments.SiteOfLostGrace;


/**
 * A class that allows actors to travel from Site Of Lost Grace to Site Of Lost Grace
 * other Site Of Lost Graces
 * Created by:
 * @author Benedict Lee Dao-Ee
 * Modified by: Chang Yee Vern
 */
public class FastTravelAction extends Action {
    /**
     * The current Site Of Lost Grace that the actor is at
     */
    private SiteOfLostGrace site;

    /**
     * Constructor
     * @param site The current Site Of Lost Grace that the actor is at
     */
    public FastTravelAction(SiteOfLostGrace site){
        this.site = site;
    }

    /**
     * When the action is executed the actor will move to the selected Site Of Lost Grace
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A message saying which Site Of Lost Grace the actor has moved to
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.moveActor(actor,this.site.getTravelLocation(this.site.getName()));;
        return "You have successfully travelled to " + this.site.getName();
    }

    /**
     * Describes the Site Of Lost Grace being travelled to
     * @param actor The actor performing the action.
     * @return A description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " travels to " + this.site.getName() + " Lost Site Of Grace";
    }
}
