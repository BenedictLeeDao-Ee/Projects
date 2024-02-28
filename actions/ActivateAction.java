package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.grounds.GroundManager;
import game.grounds.environments.SiteOfLostGrace;
import game.utils.FancyMessage;

import java.util.ArrayList;

/**
 * An action to activate the Site of Lost Grace
 * Created by:
 * @author Benedict Lee Dao-Ee
 * Modified by: Chang Yee Vern, Alyssa Ting Sue-Lyn
 *
 */
public class ActivateAction extends Action {
    /**
     * The current Site of Lost Grace that the actor is at
     */
    private  SiteOfLostGrace site;

    /**
     * Constructor
     * @param site the current Site of Lost Grace that the actor is at
     */
    public ActivateAction(SiteOfLostGrace site){
        this.site = site;
    }

    /**
     * When the action is executed, the Site Of Lost Grace will be set to activated,
     * its location will be stored in all the other activated Site Of Lost Graces and
     * the current Site Of Lost Grace will also store all the location of the already
     * activated Site Of Lost Grace
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A message saying which Site Of Lost Grace has been activated
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        for (String line : FancyMessage.LOST_GRACE_DISCOVERED.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        this.site.setActivated(true);
        GroundManager.getInstance().addActivatedSite(this.site);
        ArrayList<SiteOfLostGrace> sites = GroundManager.getInstance().getActivatedSite();
        for(SiteOfLostGrace siteOfLostGrace: sites){
            this.site.setTravelLocation(siteOfLostGrace.getName(),siteOfLostGrace.getLocation());
            siteOfLostGrace.setTravelLocation(this.site.getName(),this.site.getLocation());
        }
        return this.site.getName() + " is activated";
    }

    /**
     * Describes the Site Of Lost Grace being activated
     * @param actor The actor performing the action.
     * @return A description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " touches " + this.site.getName() + " of lost grace";
    }
}
