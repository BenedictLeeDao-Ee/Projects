package game.reset;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.grounds.GroundManager;
import game.grounds.environments.SiteOfLostGrace;

import java.util.ArrayList;

/**
 * An action executed if the actor rests at the Site of Lost Grace or dies
 * Created by:
 * @author Benedict Lee Dao-Ee
 * Modified by: Alyssa Ting Sue-Lyn, Chang Yee Vern
 */
public class RestAction extends Action {

    /**
     * The current Site Of Lost Grace that the actor is at
     */
    SiteOfLostGrace siteOfLostGrace;

    /**
     * Constructor
     * @param siteOfLostGrace The current Site Of Lost Grace that the actor is at
     */

    public RestAction(SiteOfLostGrace siteOfLostGrace) {
        this.siteOfLostGrace = siteOfLostGrace;
    }

    /**
     * When the player rests at the Site of Lost Grace, they will be
     * teleported/respawned to the location of the Site of Lost Grace and
     * the game will reset
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A message that says the player is able to rest
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ArrayList<SiteOfLostGrace> sites = GroundManager.getInstance().getActivatedSite();
        for (SiteOfLostGrace site:sites) {
            site.setLastVisited(false);
        }
        this.siteOfLostGrace.setLastVisited(true);
        if (!this.siteOfLostGrace.getLocation().containsAnActor()) {
            map.moveActor(actor,this.siteOfLostGrace.getTravelLocation(this.siteOfLostGrace.getName()));
        }
        RestManager.getInstance().run();
        return menuDescription(actor);
    }

    /**
     * A description on what the execution of the action does
     * @param actor The actor performing the action.
     * @return A string that says what the action does
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " rests at " + siteOfLostGrace.getName() + " Lost Site of Grace";
    }

}
