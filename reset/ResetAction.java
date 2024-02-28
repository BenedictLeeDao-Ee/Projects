package game.reset;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.grounds.GroundManager;
import game.grounds.environments.SiteOfLostGrace;

import java.util.ArrayList;

/**
 * An action that is executed when the game resets
 * @author Benedict Lee Dao-Ee
 * Modified by: Alyssa Ting Sue-Lyn
 */
public class ResetAction extends Action {

    /**
     * When the game resets, the actor will respawn at the last Site Of Lost Grace
     * visited and all reset methods for Resettable classes will run
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A message that says the game will reset
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ArrayList<SiteOfLostGrace> sites = GroundManager.getInstance().getActivatedSite();
        for (SiteOfLostGrace site:sites) {
            if (site.isLastVisited()) {
                map.moveActor(actor,site.getLocation());
            }
        }
        ResetManager.getInstance().run();
        return menuDescription(actor);
    }

    /**
     * A description on what the execution of the action does
     * @param actor The actor performing the action.
     * @return A string that says what the action does
     */
    @Override
    public String menuDescription(Actor actor) {
        return "The game resets";
    }
}
